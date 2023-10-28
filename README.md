# 커피가게 백엔드 API 구현

시스템정보
- Java 17
- Spring Boot 3.1.2

* UI 제외, API만 개발. (restful Tool을 이용한 데이터 확인)
* Swagger 적용을 통한 API 문서 노출

## 주문관리

- 주문 프로세스: 메뉴 선택 -> 고객 전화번호를 통한 멤버십 조회 -> 멤버십 포인트를 통한 결제.
- 고객 메뉴 조회 API
- 고객 상품 선택 API
- 고객 멤버십 조회 API
- 결제 API

## 상품관리

- 커피관련 상품을 판매할 수 있도록 상품관리.
- 메뉴관련 구성 참고: https://www.theventi.co.kr/new2022/menu/all.html?mode=2

## 고객관리

- 고객정보는 휴대폰 번호를 통해서 조회하며, 해당 번호를 통한 멤버십 포인트로 주문이 가능.

---
* 라이브러리 사용 자유.



## 배운 것들

- 배운 기술
  - 빌더 패턴에 대한 생각
  - 스웨거
  - rest api
  - 특정 값에 대한 입력은 명시 : 최초 가입 포인트 명시와 같은것 


- 협업에 대한 고민과 배운점
  - enum 에 대한 표시 (명시적)
  - 코드 리펙토링 복잡한 코드에 대한 명시와 리펙토링 : 협업에 명시적으로 표시

- DB
  - MySql 성능 개선을 위한 인덱스 추가 
- 리펙토링 시작
  - 구조에 대한 변화(추상화)
  - 서비스가 도메인을 가져와서 일을 시키는 구조로 변경(서비스의 부담을 줄이자. )
  - 레이어드 아키텍처 -> 헥사고날 아키텍처 변환중
  - 의존성 역전을 할때 추상화에 의존하는 구조를 만들자
  - 아키텍처를 변환할때는 저수준에서 -> 고수준의 모듈을 순서로 변화를 줘보자
  
- test 추가 작업
  - coverage 체크
  
- 작업 하면서 생각난것
  - 모든 부분의 테스트를 만들어야 할까?(infra 와 같은 부분은 Jpa 에서 테스트를 잘하지 않았을까? 시간이라는 비용이 많이 든다.)
  - 빌더 패턴 -> 생산성이 향상되고 깔끔하게 코드가 작성 가능하다. 하지만, 중간중간 실수로 주입해야할 필드를 빼먹는 것이 생긴다. -> 테스트로 극복이 가능할까?
  - 현재 진행하는 아키텍처 도메인 별로 많은 분리를 해두었지만 협업을 하게되었을 때 어떤 부분은 어떤 조직이 하는지 이런거를 생각해봐야겠다.(회색지대)
  - 생각보다 각 클래스에 대한 생성을 하게 되면서 더 규모있는 서비스에서는 비용이 많이 들수 있겠다는 생각을 하게되었다. 이런점을 고려해 프로젝트를 나아가야겠다.
  - 테스트를 넣어보니 정말 놓치고 있는 것을 찾을 수 있다. (계속 저장 후 널포인트가 나와서 왜그런지 찾아보니 정작 저장을 안한 문제..)
  - 테스트를 짤때는 서비스의 흐름을 따라 짜게 되면 서비스의 전반적인 구조를 파악하기 쉬운거 같다.
  - 테스트를 짜다보니 꼼꼼하게 짜는 것도 능력이 될 수 있다는 것을 생각하게 되었다.
  

- 현재 test 문제 
  - 비결정 테스트로 동시성 문제가 있다. 같은 이름의 회원 만들고 진행했을때 -> 순수 자바로 만들게 되면서 h2에서의 문제를 해결
  - h2 와 모키토 없는 테스트를 넣어야 빨라진다 -> 순수 자바 객체들만 이용한 객체를 활용한 결과 속도가 빨라졌다.

- 해야할것 
  - find 메소드 optional 처리
  - order uuid
  
- 특이사항
  - mysql 비번 까먹고 재설정할때 조건에 맞춰서 비밀번호를 설정해줘야한다.(너무 간단한 비밀번호는 변경이 안된다.현재 디비 root 비번 1q2w3e4r)
  - 인덱스 유니크 JPA 로 잘 들어가나 확인했는데, 초기화하고 재설정하다 2시간 날렸다. 