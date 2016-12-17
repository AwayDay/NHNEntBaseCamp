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