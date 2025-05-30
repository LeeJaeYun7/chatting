
## 1. 웹소켓 서버
- 클라이언트와 서버 간에 양방향 통신을 위해 웹소켓 서버 사용
- 채팅 메시지 혹은 사용자 접속 상태 변경은 웹소켓 서버에서 웹소켓 클라이언트로 푸시

### 1-1. In-Memory 세션 정보 관리
- 사용자 ID - 세션 ID를 In-Memory에 관리함으로써 빠른 조회 성능 보장 및 Redis 부하 최소화 

## 2. JWT 인증
- 사용자 로그인 시 JWT 토큰을 사용하여 인증
  
## 3. RDB(MySQL) vs NoSQL(MongoDB)
- MySQL: 사용자 정보, 채팅방 등 구조적인 데이터 저장
- MongoDB: 채팅 메시지와 같은 대용량/비정형 데이터 저장

### 3-1. MySQL 트랜잭션 관리
- 사용자 정보, 친구 관계 생성 시 @Transactinal을 통해 트랜잭션을 관리

## 4. 메시지 큐(Kafka) 
- 웹소켓 서버 -> 스프링 서버 통신에 메시지 큐인 Kafka 사용

### 4-1. Consumer Group 관리
- 스프링 서버의 Consumer는 Consumer Group 관리를 통해 하나의 파티션이 하나의 Consumer에 할당되도록 구현

### 4-2. Dead Letter Queue(DLQ) 관리
- 웹소켓 서버에서 메시지 발행에 실패한 경우, 해당 메시지는 DLQ 토픽에 저장
- 이후, 스케줄러를 통해 DLQ에 쌓인 메시지를 일정 주기로 재발송 처리

## 5. 레디스(Redis)

### 5-1. 사용자 접속 정보 캐싱 
- 레디스를 사용하여 사용자ID-세션 정보를 글로벌하게 관리

### 5-2. Redis Pub/Sub 브로드 캐스팅 
- 스프링 서버 -> 웹소켓 서버 통신에 Redis Pub/Sub 사용해 채팅 메시지, 온라인 상태 변경 등을 브로드 캐스팅

