# 개요
- 프로젝트명: shortenurl
- 무신사 입사 지원 테스트 과제 프로젝트

# 문제
```
과제 내용: 
URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
예) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro

- URL 입력폼 제공 및 결과 출력
- URL Shortening Key는 8 Character 이내로 생성되어야 합니다
- 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다
- 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
- Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다
- Database 사용은 필수 아님

다음의 경우 가산점이 부과됩니다
- Unit test 및 Integration test 작성

제출물:
- 소스코드가 담긴 github
- github의 readme에는 해당 웹서버를 리눅스 기준으로 실행하기 위해 필요한 설치/빌드 방법이 작성되어 있어야 합니다
```

## 주요 요구사항
- shortenkey 는 8캐릭터 이하 => 단순 인코딩 불가, 맵핑 필요함
- 동일 url 의 shortenkey 는 동일해야됨 ! => 유니크 + 양방향

# 빌드방법
- maven install

# 실행방법
```
java -jar shortenurl-1.0.jar
```

# 서비스 설명
- URL 입력폼 제공 및 결과 출력 화면: http://localhost/

```
# url 입력 후, saveAndSearch 버튼 클릭시 shortenKey 발급
# 예) http://localhost/
```

- 리다이렉트 서비스: http://localhost/{shortenKey}

```
# 예) http://localhost/B
```

- 요청수 조회: http://localhost/{shortenKey}/+

```
# 예) http://localhost/B/+
```

# 개발 환경
- java11
- maven (빌드)
- spring boot 2.3.1 (프레임워크)
- embeded tomcat (WAS)
- embeded redis (로컬 캐시)
- mybatis (ORM)
- mariadb (DB)
- jsp, jquery (입력폼 화면)

# 테이블 스키마
- create 테이블 쿼리 참고: [쿼리 파일 링크](docs/dbschema/create_shortenurl.sql)

# 특이사항 - shortenKey 에 대한 사전 조사
```
아스키코드 a-zA-Z0-9 를 십진수 0-61에 매핑하고 
62진법을 사용해서 시퀀셜 숫자 id 와 문자열을 맵핑함

8자리 단축키가 
9,9,9,9,9,9,9,9
라고 한다면...?

62진법으로 표현하면 길이가 충분한가 ???
62진법 8자리 최대값: base62_8digit_MAX

LONG_MAX        9,223,372,036,854,775,807
INT_MAX                     2,147,483,647
base62_8digit_MAX     218,340,105,584,895 // 100 조 단위

mysql 테이블, auto_increase int 부호없음을 사용하고
java bean, long 을 사용
```

# 특이사항
- redirect 서비스가 상대적으로 요청이 급격히 많을 것으로 예상
- viewcnt 조회가 필요한 부분은 캐시를 하지 않음
- 따라서, shortenKey 로 redirect 서비스를 실행하는 부분만 캐시 사용
- 단축url 생성시, 중복 url 관리를 위해 base64 인코딩 된 값을 테이블 고유키로 관리
- 단축url 생성시, url-Scheme, url-parameter 등 예측하기 힘든 부분으로 http 를 제거 한다거나, www 를 제거하는 등의 원본url 을 변경하지 않음
- shortenurl 테이블의 '원본url' 컬럼은 길이 제한에 자유롭도록 TEXT로 설정, 문제시 변경 필요

# TODO (생략하지만 성능 개선을 위해 추가할 부분)
- 분산 캐시
- viewcnt update 비동기 로직 부분 그룹-배치 처리
