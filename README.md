# 🍊 Gyul-Box
><b>제주 지역의 주거공간 검색, 커뮤니티 서비스</b>
>
>제주 한달살이를 준비하는 사용자들에게 특화된 서비스입니다.   
>주거공간에 대한 리뷰를 참고하고, 양도 게시판을 통해 자유로운 소통이 가능합니다.

</br>

## 1. 제작 기간 & 참여 인원
- 2023년 5월 2일 ~ 2023년 6월 24일
- 팀 프로젝트(프론트엔드 1명, 백엔드 2명)

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 11
  - Spring Boot 2.7.10
  - Gradle
  - Spring Data JPA
  - QueryDSL 5.0.0
  - H2
  - MySQL 8.0.32
  - JUnit5
  - Mockito
  - WebSocket
  - Redis
  - WebClient
  - AWS EC2
  - AWS RDS

</br>

## 3. ERD 설계
<img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/71ec04c6-2c24-414f-99a1-a4dacb6de443" width=600 height=600>

</br>
</br>

이미지를 클릭하시면 확대해 볼 수 있습니다.

</br>

## 4. 전체적인 흐름
<img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/72a29c5c-dba1-46e0-8411-5c9544181cb6">

</br>
</br>
</br>

## 5. 맡았던 핵심 기능
### 5.1. 게시판, 게시판 댓글, 게시판 좋아요 도메인 기능 구현
 
<details>
<summary>상세 설명</summary>
<div markdown="1">

#### 5.1.1. 연관 관계 그림

<img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/0e7d6ac5-a7e2-4cf6-8911-8abd2bfb2a4a">

</br>

#### 5.1.2. 내용

- 특정 게시물이 삭제될 경우, 해당 게시물의 댓글, 좋아요 데이터도 삭제되도록 구현했습니다. [코드](https://github.com/bangjaeyoung/gyul-box/blob/c6befefb8a51988d3e18a90d1e32dfbba89a22e5/server/src/main/java/jeju/oneroom/post/entity/Post.java#L49C5-L55C58)
- 게시물과 댓글이 수정, 삭제할 경우 작성한 본인만 가능하도록 처리했습니다. [코드](https://github.com/bangjaeyoung/gyul-box/blob/c6befefb8a51988d3e18a90d1e32dfbba89a22e5/server/src/main/java/jeju/oneroom/post/service/PostService.java#L38C5-L50C6)
- 게시물 조회 시, 조회 수가 1씩 증가되도록 Post 엔티티 내에 필드값 변경 메서드를 만들었습니다. [코드](https://github.com/bangjaeyoung/gyul-box/blob/c6befefb8a51988d3e18a90d1e32dfbba89a22e5/server/src/main/java/jeju/oneroom/post/service/PostService.java#L52C5-L59C6)  
- 게시글에 대한 좋아요를 2번 누를 경우, 취소되도록 기능을 구현했습니다. [코드](https://github.com/bangjaeyoung/gyul-box/blob/c6befefb8a51988d3e18a90d1e32dfbba89a22e5/server/src/main/java/jeju/oneroom/postlike/service/PostLikeService.java#L20C5-L32C6)

#### 5.1.3. 각 도메인 Service Layer 코드

- [게시판](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/post/service/PostService.java)   
- [게시판 댓글](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/postcomment/service/PostCommentService.java)   
- [게시판 좋아요](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/postlike/service/PostLikeService.java)

</div>
</details>

</br>

### 5.2. 외부 Open API를 활용하여 필요한 데이터 저장

<details>
<summary>상세 설명</summary>
<div markdown="1">
  
#### 5.2.1. 사용 목적

- 지역에 따른 주거공간 데이터 필요
- 프론트단의 지도 인터페이스에 활용될 주거공간의 위도, 경도 데이터 필요

#### 5.2.2. 호출 흐름

1. 지역 코드를 파라미터로 외부 Open API를 호출합니다.   
2. 응답된 데이터는 서비스단으로 이동하여 가공됩니다.   
    - 주거공간의 타입(다가구주택, 다중주택, 공동주택, 다세대주택, 오피스텔, 단독주택)을 선별   
    - 주거공간의 위도, 경도 데이터를 위해 또 다른 외부 Open API를 호출   
    - HouseInfo 엔티티 필드에 맞는 데이터들을 뽑아내 DB에 저장
  
(외부 Open API의 호출은 모두 WebClient 라이브러리를 이용했습니다.)

#### 5.2.3. 코드
:pushpin: [OpenApiController.Java](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/controller/OpenApiController.java)   
:pushpin: [OpenApiService.Java](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/service/OpenApiService.java)   
:pushpin: [GeoPointService.Java](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/service/GeoPointService.java)   

</div>
</details>

</br>

### 5.3. 코드 리팩토링 및 N+1 문제 해결

<details>
<summary>상세 설명</summary>
<div markdown="1">

#### 5.3.1 문제 상황

N+1 문제가 발생하는 여러 메서드 중 `findPostById()`의 상황입니다.   

</br>

하나의 게시글을 조회하는 과정은 다음과 같습니다. 

1. Post Id에 맞는 게시글을 DB에서 조회
2. 해당 API의 응답 데이터 중 `List<PostCommentDto.Response>`를 위해 연관된 PostComment 조회
3. `PostCommentDto.Response`의 필드 중 `UserDto.SimpleResponse`를 위해 연관된 User들 조회

</br>

여기서, 조회되는 PostComment 개수 만큼의 User를 조회하는 쿼리문이 발생하는 N+1 문제가 발생했습니다.

응답 데이터 DTO는 [코드](https://github.com/bangjaeyoung/gyul-box/blob/fcd60ab32b86c605d9d309b8b6ff413ba407a16c/server/src/main/java/jeju/oneroom/post/dto/PostDto.java#L80C5-L96C6)를 참고해주세요.

</br>

[기존 쿼리문 출력 사진]
<img src = "https://github.com/bangjaeyoung/gyul-box/assets/80241053/7cb8fe8f-2d6f-4b03-a386-ca70459e8240">

</br>

#### 5.3.2 해결

Querydsl의 Fetch Join을 활용하여, N+1이 발생하는 문제를 해결했습니다.

```Java
@Override
public Optional<Post> findPostById(long postId) {
    Post post1 = jpaQueryFactory.selectFrom(post)
        .leftJoin(post.user, user).fetchJoin()
        .leftJoin(post.houseInfo, houseInfo).fetchJoin()
        .leftJoin(post.postComments, postComment).fetchJoin()
        .leftJoin(postComment.user, user).fetchJoin()
        .where(post.id.eq(postId))
        .fetchOne();

    return Optional.ofNullable(post1);
}
```

[원본 코드](https://github.com/bangjaeyoung/gyul-box/blob/fcd60ab32b86c605d9d309b8b6ff413ba407a16c/server/src/main/java/jeju/oneroom/post/repository/PostCustomRepositoryImpl.java#L24C5-L35C6)

</br>

[개선 후 쿼리문 출력 사진]

<img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/71b3bf1f-b84c-4c37-8dcf-a3b333b6c2b3">

</br>
</br>

<b>총 쿼리문이 4+N개 호출되는 것을 1개의 쿼리문으로 줄여, DB로의 요청 부하를 줄일 수 있었습니다.</b>

</div>
</details>

</br>

### 5.4. RDS 마이그레이션 및 AWS EC2 배포 작업

<details>
<summary>상세 설명</summary>
<div markdown="1">

</br>

팀원과의 상의 후에 지역, 주거정보에 대한 데이터를 로컬 MySQL DB에 직접 넣어주었습니다.   
[openapi 디렉토리](https://github.com/bangjaeyoung/gyul-box/tree/main/server/src/main/java/jeju/oneroom/openapi)에 있는 서비스 로직들이 모두 이와 관련된 로직들입니다.   

</br>

이를 AWS RDS의 MySQL DB로 마이그레이션 작업을 거친 후, AWS EC2 서버에서 백엔드 서버를 배포했습니다.   
MySQL DB 마이그레이션 작업 과정은 다음 [블로깅](https://jaeyoungb.tistory.com/283)을 통해 확인하실 수 있습니다.   

</div>
</details>

</br>

## 6. 핵심 트러블 슈팅

### 6.1. 문제 상황

하나의 게시글을 조회하면, 자동으로 조회 수가 1 증가하는 로직을 Querydsl을 활용해서 다음과 같이 JPQL을 작성했었습니다.

```Java
    @Override
    public long updateViewCount(Long postId) {
        return jpaQueryFactory.update(post)
                .set(post.views, post.views.add(1))
                .where(post.id.eq(postId))
                .execute();
    }
```

위와 같이 구현한 로직으로는 1이 증가된 조회 수가 DB에 반영이 되었지만, 응답으로는 반영되기 전의 데이터가 응답되는 것을 확인했습니다.   

### 6.2. 문제 원인

하나의 게시글을 조회하는 흐름 순서는 다음과 같습니다.
1. 먼저 유효한 게시글인지 조회한다.
2. 이 과정에서 Post 엔티티가 영속성 컨텍스트 내에 로드되고, 1차 캐시에 저장된다.
3. 위 Querydsl의 JPQL을 통한 벌크 연산이 수행된다.
4. DB의 조회 수는 1 증가된다.
5. 벌크 연산은 영속성 컨텍스트를 우회하는 작업이기 때문에, 1차 캐시에 있는 조회 수가 증가되기 전의 Post 엔티티가 반환된다.

[관련 내용 PR](https://github.com/bangjaeyoung/gyul-box/pull/3)

### 6.3. 해결 과정
1. Querydsl의 벌크 연산 처리
2. 엔티티 메서드 내에서 처리하도록 변경(JPA 변경감지 이용)
3. 서비스 레이어에서 처리하도록 변경(단일 책임 원칙) [관련 내용 PR](https://github.com/bangjaeyoung/gyul-box/pull/6) / [관련 내용 블로깅](https://jaeyoungb.tistory.com/292)

```Java
    // Service Layer 게시글 조회 코드
    @Transactional
    public PostDto.Response findPostByPostId(long postId) {
        Post verifiedPost = findVerifiedPost(postId);
        verifiedPost.increaseViews();

        return postMapper.postToResponseDto(verifiedPost);
    }

    // Post 엔티티 내 조회 수 1 증가 메서드
    public void increaseViews() {
        this.views++;
    }
```

</br>

## 7. 그 외 트러블 슈팅

- querydsl 이용 시, pagination 사용 불가
- ddl-auto vs hbm2ddl
- querydsl를 활용했던 로직에서 Service Layer에서 처리하는 로직으로 변경 후, 필요없는 컬럼까지 업데이트 되는 문제 -> @DynamicUpdate로 해결

</br>

## 8. 아쉬운 점 및 회고

- 조회 수 1 증가시키는 로직으로 인해, JPA Auditing을 받는 modifiedAt 필드도 함께 변경됨 / 게시판 수정일자에 대한 데이터 필드는 따로 만드는 방식으로 수정 필요함.
- WebSocket을 활용한 실시간 채팅 구현 / 통신 테스트 문제
- 여러 상황에서 기본 fetch 전략이 다 다른 것을 확인했고 [블로깅]()을 통해 정리
