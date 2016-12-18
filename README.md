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

## MySQL MyBatis 연동하기
1. pom.xml에 적절한 디펜던시를 넣는다
2. java/resources 하위에 적절한 폴더(나는 config 라고 했다.)를 생성해서, xml 설정 문서를 추가한다(DB 접속, 세션 획득 등)
3. web.xml에서 위에 추가한 설정 파일들을 읽을 수 있도록 context-param을 추가한다.
4. 역시 java/resources 하위에 적절한 폴더(보통 mapper 폴더를 만들더라)를 만들어서, xml 문서에 SQL 명령어를 넣는다.
5. 적절한 패키지에 적절한 이름으로 테이블을 대표할 수 있는 DTO 클래스를 생성한다. POJO인가 뭔가, 여하간 캡슐화 잘 되어 있어야 함.
6. DAO 인터페이스를 만든다. 내 생각엔 인터페이스는 안 만들어도 될 것 같은데, 남들 다 만드니까 나도 만들어봤다.
7. 위 인터페이스를 구현한 DAO(적절한 이름) 클래스를 생성한다. 여기에서 위에서 생성한 SQL 문법을 사용한다.
8. 잘 구현했으면 다시 컨트롤러로 돌아와서, 당신이 생각하는 그 방식대로 쓰면 됩니다!
9. 잘 모르겠으면 소스를 보시오.