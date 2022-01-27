# 우리동네 북클럽 API
"우리동네 북클럽" 웹서비스를 위한 API 서버입니다.  
Frontend는 https://github.com/bookclubstory/bookclub-front 를 참고해주세요!

## 기능
- 메인 페이지 : 통합검색, 이달의 인기 도서, 추천 모임
- 북클럽 : 모임 개설, 북클럽 목록조회, 북클럽 상세보기
- 독서로그 : 개인 독서 활동 게시물 (예시: 사진 + 글 + 태그)
- 마이 페이지 : 개인 프로필, 참여중인 북클럽 정보, 작성한 독서로그, 북마크한 도서 정보

## 기술 스택
- Frontend : React.js
- Backend : Spring boot, JPA
- DB : PostgreSQL
- Session : Redis

## 프로젝트 구조
DDD을 참고하여 도메인 단위로 sub-package를 구성합니다.
- /libs : 외부 라이브러리
- /src : source 디렉토리
- /src/docs : api documents
- /src/main : 메인 패키지
- /src/main/java : 프로젝트 소스
- /src/main/java/.../domain : 도메인 패키지 (비즈니스 로직 구현)
- /src/main/java/.../domain/model : 도메인 엔티티 객체들이 공통적으로 사용할 객체들 (Enum, Embeddable)
- /src/main/java/.../global : 공통 기능 구현
- /src/main/resources : 정적 리소스 디렉토리
- /src/test : api 테스트 코드 작성

```
project
└───libs
└───src
│   └───docs
│   │   └───asciidoc
│   │       │   api-doc.adoc
│   └───main
│   │   └───java
│   │   │   └───com.emmette.bookclub
│   │   │       └───domain
│   │   │       │   └───bookclub
│   │   │       │   └───bookpost
│   │   │       │   └───mainpage
│   │   │       │   └───model
│   │   │       │   └───system
│   │   │       └───global
│   │   │       │   └───config
│   │   │       │   └───mail
│   │   │       │   └───schedule
│   │   │       │   └───security
│   │   │       │   └───util
│   │   │       │   BookclubApplication.java
│   │   └───resources
│   │       │   application.properties
│   │       │   application-dev.properties
│   │       │   application-production.properties
│   │       │   banner.txt
│   │       │   logback.xml
│   └───test
│       └───java
│           └───com.emmete.bookclub
│               └───domain
│   
│   .gitignore
│   build.gradle
│   gradlew
│   gradlew.bat
│   README.md
│   settings.gradle
```

## Request/Response Flow
1. request by Controller
2. Controller call Service interface
3. ServiceImpl access Repository
4. parameters transfer by Dto (Controller-Service)

## Crew
- 권용희 : 기획 & 개발 @[konsent](https://github.com/konsent)
- 손소라 : 개발 @[ssr03](https://github.com/ssr03)
- 윤종현 : PM & 개발 @[starrything](https://github.com/starrything)
- 정지용 : 풀스택 개발 @[jylvxx](https://github.com/jylvxx)
