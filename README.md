# NHNEntBaseCamp
NHN 엔터테인먼트 입사 전 사전 과제

----

# 우선 해야 할 일
* STS 만져보기
* 스프링 MVC는 무엇인가
* 돌려보기(!) - 사실상 모든 에러의 근원

## 프로젝트를 생성하고
* 메이븐 pom 파일 수정 - 라이브러리 버전(전부 릴리즈로 하니 괜찮더라.), 자바 버전 수정
* 프로젝트의 JRE 시스템 라이브러리(기본값 : 1.6) 최신화
* 서버 생성(톰캣 9)
* 실행 해보고 안 되면 .m2를 예쁘게 날리고 프로젝트 업데이트 해서 라이브러리를 다시 받아본다
* 실행하면, 어머나 예뻐라
* 한글이 깨져 나오니 인코딩 부분만 수정해주자 >> 완료!

# 실행 문제 해결하기

## Tomcat 작동 불가 문제
* https://slipp.net/questions/208 참조
* http://kindlybugs.com/279 이런 해결 방법도 있다. 무엇이건 간에 참 골치가 아픈 녀석이다.
* 서블릿 버전 문제였던 것 같다.

## 404 Error 문제 
* 문제는 https://github.com/AwayDay/NHNEntBaseCamp/issues/7 참조
* 해결함! >> 라이브러리 버전 문제였음, .m2 폴더 날려서 새로 받는게 마음 편할거임.

# 프로젝트를 만들고 실행도 해 봤으면 무엇을 해야 하느냐?
1. 서버에서 어떻게 요청을 접수하는지 알 필요가 있다. - get / post
2. 스프링 - MySQL 연동법을 알 필요가 있다.
3. 마이바티스는 뭐 하는 친구래요?
4. 설마 부트스트랩을 못 쓰진 않겠지?
5. 오랜만에 자바스트립트를 만져봐야 한다.... 아니, 이런, 제이쿼리네.
6. 그러고 보니 JSP 사용법도 익혀야 함.
7. 테스트 케이스도 생각해보자.
8. 트레비스도 연동해야지

# 글 쓰기(#1)를 해결해봅시다.
* 사용자는 폼에 아이디, 비밀번호, 글 내용을 입력한 후 전송 버튼을 누른다
* 위 폼 중 어떤 것도 비어 있을 수 없다(js 사용)
* 적절한 입력이 있었을 경우, 폼 내용을 (/newarticle, POST)로, (email, password, content)형식으로 전송한다.
* 서버는 해당 입력을 받아서, 연동한 MySQL 테이블에 인서트 해야 한다.
* 성공/실패에 따라 적절한 메시지를 클라이언트에 전송한다(200, 500 등)
* 메인 페이지를 새로고침 한다.

## POST 요청 처리하기
* 컨트롤러 하나 더 만들어야 하나 하고 쫄았는데
* 다행히도 리퀘스트 매핑 메서드 하나 더 만들면 되더라.
```
@RequestMapping(value = "/aaa", method = RequestMethod.POST)
```
* 포스트 요청 하면서 같이 넘긴 인자(json)도 소스단에서 인자로 잘 넘어가짐
```
@RequestParam("name") String name, ...
```
* 그런데 지금 내 설계가 설계라, 클라이언트한테 뭔가.... 그, 답변을 보내고 싶은데 말이지.
* 그것을 좀 생각해 볼 필요가 있겠다. 정상처리면 200, 서버 에러면 500, 하는 느낌으로.
* 에러 코드 넣는 김에 REST 문서들 다시 한 번 찾아볼 필요도 있다
* 그러고보니, 스프링엔 REST 지원 따로 없음?

## MySQL MyBatis 연동하기
* pom.xml에 적절한 디펜던시를 넣는다
```
<dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>${org.springframework-version}</version>
</dependency>
```
```
<!-- MyBatis -->
<dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.2.7</version>
</dependency>
<dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.2.2</version>
</dependency>
<dependency>
        <groupId>commons-dbcp</groupId>
        <artifactId>commons-dbcp</artifactId>
        <version>1.4</version>
</dependency>
```
```
<!-- MySQL -->
<dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.31</version>
</dependency>
```
* java/resources 하위에 적절한 폴더를 생성해서, xml 설정 문서를 추가한다(DB 접속, 세션 획득 등)
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                        http://www.springframework.org/schema/jdbc  http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd">
     
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://주소/스키마"/>
        <property name="username" value="이름"/>
        <property name="password" value="비밀번호"/>
    </bean>
</beans>
```
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
 
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="mapperLocations" value="classpath:적절한 경로를 넣는다" />
    </bean>
     
    <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSession"/>
    </bean>  
</beans>
```
* web.xml에서 위에 추가한 설정 파일들을 읽을 수 있도록 context-param을 추가한다.
```
<!-- 해당 경로의 컨텍스트-*.xml 파일을 모두 읽어들인다. -->
<context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath*:config/알아서 경로를 넣는다.</param-value>
</context-param>
```
* 역시 java/resources 하위에 적절한 폴더를 만들어서, xml 문서에 SQL 명령어를 넣는다.
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
 
<mapper namespace="함수는 네임스페이스 기준으로 호출되니 적절한 이름을 선정하자">.
	<insert></insert>
	<select></select>
        <!-- 뭐 이런 식으로 -->
</mapper>
```
* 적절한 패키지에 적절한 이름으로 테이블을 대표할 수 있는 DTO 클래스를 생성한다. POJO인가 뭔가, 여하간 캡슐화 잘 되어 있어야 함.
* DAO 인터페이스를 만든다. 내 생각엔 인터페이스는 안 만들어도 될 것 같은데, 남들 다 만드니까 나도 만들어봤다.
* 위 인터페이스를 구현한 DAO(적절한 이름) 클래스를 생성한다. 여기에서 위에서 생성한 SQL 문법을 사용한다.
* 잘 구현했으면 다시 컨트롤러로 돌아와서, 당신이 생각하는 그 방식대로 쓰면 됩니다!
* 잘 모르겠으면 소스를 보시오.
