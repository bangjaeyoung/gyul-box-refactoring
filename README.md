# 🍊 Gyul-Box
><b>제주 지역의 주거공간 검색, 커뮤니티 서비스</b>
>
>제주 한달살이를 준비하는 사용자들을 위해 구상한 서비스입니다.   
>주거공간에 대한 리뷰를 찾아보고, 게시판 서비스를 통해 자유롭게 소통할 수 있습니다.   

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
### 게시판, 댓글, 좋아요 도메인 구현
 
<details>
<summary>상세 설명</summary>
<div markdown="1">

#### 1) 연관 관계 그림

<img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/0bfba8d3-7c24-46a4-8c31-3ff6a5f60f1e" width=500 height=300>

#### 2) 내용

- 게시물, 댓글, 좋아요에 대한 수정 및 삭제는 동일 유저만 가능하도록 처리했습니다. 📌 [해당 코드](https://github.com/bangjaeyoung/gyul-box/blob/c6befefb8a51988d3e18a90d1e32dfbba89a22e5/server/src/main/java/jeju/oneroom/post/service/PostService.java#L38C5-L50C6)
- 게시물 조회 시, 조회 수가 1씩 증가되도록 구현했습니다. 📌 [해당 코드](https://github.com/bangjaeyoung/gyul-box/blob/c6befefb8a51988d3e18a90d1e32dfbba89a22e5/server/src/main/java/jeju/oneroom/post/service/PostService.java#L52C5-L59C6)
- 게시글 좋아요를 2번 누르면 좋아요가 취소되도록 구현했습니다. 📌 [해당 코드](https://github.com/bangjaeyoung/gyul-box/blob/c6befefb8a51988d3e18a90d1e32dfbba89a22e5/server/src/main/java/jeju/oneroom/postlike/service/PostLikeService.java#L20C5-L32C6)
- 게시물이 삭제되면 해당 게시물의 댓글, 좋아요도 함께 삭제되도록 구현했습니다. 📌 [해당 코드](https://github.com/bangjaeyoung/gyul-box/blob/c6befefb8a51988d3e18a90d1e32dfbba89a22e5/server/src/main/java/jeju/oneroom/post/entity/Post.java#L49C5-L55C58)

#### 3) 각 도메인 Service Layer 코드
📌 [게시판](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/post/service/PostService.java)   
📌 [게시판 댓글](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/postcomment/service/PostCommentService.java)   
📌 [게시판 좋아요](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/postlike/service/PostLikeService.java)

</div>
</details>

### 외부 Open API를 활용한 필요한 데이터 저장

<details>
<summary>상세 설명</summary>
<div markdown="1">
  
#### 1) 사용 목적

- 지역에 따른 주거공간 데이터 필요
- 프론트단의 지도 인터페이스에 활용될 주거공간의 위도, 경도 데이터 필요

#### 2) 호출 흐름

1. 지역 코드를 파라미터로 외부 Open API를 호출합니다.   
2. 응답된 데이터는 서비스단으로 이동하여 가공됩니다.   
    - 주거공간의 타입(다가구주택, 다중주택, 공동주택, 다세대주택, 오피스텔, 단독주택) 선별   
    - 주거공간의 위도, 경도 데이터를 위해 위도, 경도 관련 Open API 호출   
    - 응답된 데이터들과 HouseInfo 엔티티 필드를 매핑하여 DB에 저장
   
(외부 Open API의 호출은 모두 WebClient 라이브러리를 이용했습니다.)

#### 3) 코드
:pushpin: [Open API 컨트롤러 코드](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/controller/OpenApiController.java)   
:pushpin: [Open API 전체 서비스 코드](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/service/OpenApiService.java)   
:pushpin: [위도, 경도 Open API 호출 코드](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/service/GeoPointService.java)   

</div>
</details>

### 코드 리팩토링 및 N+1 문제 해결

<details>
<summary>상세 설명</summary>
<div markdown="1">

#### 1) 문제 상황

N+1 문제가 발생하는 여러 메서드 중 `findPostById()`의 상황입니다.   

하나의 게시글을 조회하는 과정은 다음과 같습니다. 

1. DB에서 게시글 id에 해당하는 게시글(Post) 조회   
2. 조회 API의 응답 dto 필드 중 관련 댓글들이 필요하므로 연관된 댓글(PostComment)들 조회   
3. 댓글의 응답 dto 필드 중 댓글 작성자의 정보가 필요하므로 연관된 작성자(User) 조회   

(응답 dto는 📌 [코드](https://github.com/bangjaeyoung/gyul-box/blob/fcd60ab32b86c605d9d309b8b6ff413ba407a16c/server/src/main/java/jeju/oneroom/post/dto/PostDto.java#L80C5-L96C6)를 참고해주세요.)

하나의 게시글을 조회하는 API를 호출하게 되면, PostComment 개수만큼의 User를 조회하는 쿼리문이 호출되는 문제가 발생했습니다. (N+1 문제)


[기존 쿼리문 출력 사진]
<img src = "https://github.com/bangjaeyoung/gyul-box/assets/80241053/7cb8fe8f-2d6f-4b03-a386-ca70459e8240">

#### 2) 문제 해결

Querydsl을 사용하여 작성한 JPQL 쿼리에서 연관 엔티티를 Fetch Join으로 결합하는 방식으로 N+1이 발생하는 문제를 해결했습니다.

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

📌 [원본 코드](https://github.com/bangjaeyoung/gyul-box/blob/fcd60ab32b86c605d9d309b8b6ff413ba407a16c/server/src/main/java/jeju/oneroom/post/repository/PostCustomRepositoryImpl.java#L24C5-L35C6)

</br>

[개선 후 쿼리문 출력 사진]

<img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/71b3bf1f-b84c-4c37-8dcf-a3b333b6c2b3">

</br>
</br>

총 쿼리문이 4+N개 호출되는 것을 1개의 쿼리문으로 줄여, DB로의 요청 부하를 줄일 수 있었습니다.

</div>
</details>

### RDS 마이그레이션 및 AWS EC2 배포 작업

<details>
<summary>상세 설명</summary>
<div markdown="1">

</br>

로컬에서 지역(Area), 주거정보(HouseInfo)의 데이터들을 MySQL DB에 직접 넣어주었습니다.   
📌 [관련 Open API 폴더](https://github.com/bangjaeyoung/gyul-box/tree/main/server/src/main/java/jeju/oneroom/openapi)에 있는 서비스 로직들로 호출하여 저장했습니다.   

이 로컬 DB를 AWS RDS의 MySQL DB로 마이그레이션 작업을 거친 후, AWS EC2을 이용해 백엔드 서버를 배포했습니다.   
MySQL DB 마이그레이션 작업 배경 및 과정은 다음 📌 [블로깅](https://jaeyoungb.tistory.com/283)을 통해 확인하실 수 있습니다.   

</div>
</details>

</br>

## 6. 핵심 트러블 슈팅

### 1) 문제 상황

하나의 게시글을 조회하면, 조회 수가 1 증가하는 로직을 Querydsl을 사용하여 다음과 같은 JPQL 쿼리를 작성했었습니다.   

```Java
@Override
public long updateViewCount(Long postId) {
    return jpaQueryFactory.update(post)
            .set(post.views, post.views.add(1))
            .where(post.id.eq(postId))
            .execute();
}
```

이렇게 구현한 로직은 DB에는 증가된 조회 수가 저장이 되었지만, 응답 데이터는 반영되기 이전의 데이터가 응답되는 문제가 있었습니다. (데이터 불일치 문제)   

### 2) 문제 원인
하나의 게시글을 조회하는 흐름 순서는 다음과 같습니다.
1. 해당 게시글이 유효한 게시글인지 조회 / 이 과정에서 조회된 게시글 객체가 영속성 컨텍스트 내에 로드되고, 1차 캐시에 저장됨   
2. Querydsl의 JPQL 쿼리를 통한 조회 수 증가 로직(벌크 연산)이 수행 / DB의 해당 게시글 조회 수는 1 증가됨
3. 벌크 연산은 영속성 컨텍스트를 우회하는 작업이기에, 1차 캐시에 저장된 조회 수가 증가되기 전의 Post 엔티티가 반환

📌 [관련 내용 PR](https://github.com/bangjaeyoung/gyul-box/pull/3)

### 3) 문제 해결
대용량 처리를 위한 벌크 연산을 통해 조회 수 증가를 구현할 필요는 굳이 없다고 판단했습니다.   
Post 엔티티 내에 조회 수 필드 증가 메서드를 만들어, Spring Data JPA의 변경 감지 기능을 활용하도록 해결했습니다.   
📌 [관련 내용 블로깅](https://jaeyoungb.tistory.com/292)

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

<details>
<summary><b>ddl-auto 옵션에서 hbm2ddl 옵션으로 변경</b></summary>
<div markdown="1">
  Area 엔티티를 설계한 후, 애플리케이션을 실행하면 다음과 같은 에러가 발생했습니다.
  
  <img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/4d4a07fc-cced-4096-b29b-0b49a8800253">

  ddl-auto: update 옵션을 hbm2ddl.auto: update로 바꿔 해결했습니다.   
  Area 도메인은 기본키 생성 전략이 따로 있지 않고, 5011010100과 같은 값으로 직접 넣어줍니다.   

  ```Java
  @Id
  @Column(name = "area_id")
  private Long areaCode;
  ```

  Area 엔티티는 기본키 생성전략인 `@GeneratedValue`를 사용하지 않았고, 그렇기에 ddl-auto 옵션으로는 스키마가 자동 생성되지 않는다고 파악했습니다.   
  📌 [참고 레퍼런스](https://velog.io/@soluinoon/H2-Column-startvalue-not-found-%EC%98%A4%EB%A5%98)

  하지만, 공식 문서의 내용에는 ddl-auto은 hbm2ddl.auto의 shortcut이고 둘은 다르지 않다고 나와있습니다.   
  애플리케이션 실행이 우선이였기에, 별다른 문제가 없는 hbm2ddl.auto를 사용하여 개발을 진행했었습니다.   
  📌 [참고 레퍼런스](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#spring.jpa.hibernate.ddl-auto)
  
</div>
</details>

<details>
<summary><b>조회 수 증가 과정에서 변경되지 않는 컬럼까지 업데이트 되는 문제</b></summary>
<div markdown="1">
  기존 Querydsl을 통해 JPQL 쿼리로 작성했던 조회 수 증가 로직이 실행될 때, update 쿼리문은 다음과 같았습니다.
  
  ```SQL
  update
      post 
    set
      post_id=?,
      created_at=?,
      modified_at=?,
      content=?,
      title=?,
      views=?,
      house_info_id=?,
      user_id=? 
    where
      post_id=?
  ```

</br>

  `@DynamicUpdate` 어노테이션을 Post 엔티티에 붙여줌으로써 변경된 컬럼만 업데이트 되도록 개선했습니다.   

  ```SQL
  update
      post 
    set
      modified_at=?,
      views=?,
    where
      post_id=?
  ```

  📌 [참고 블로그](https://velog.io/@freddiey/JPA%EC%9D%98-DynamicUpdate)

</div>
</details>

</br>

## 8. 아쉬운 점 및 회고

<details>
<summary><b>JPA Auditing 기능을 활용한 modifiedAt 필드</b></summary>
<div markdown="1">

조회 수가 증가될 때, JPA Auditing 기능을 통해 트랙킹되는 테이블 수정 시간(modifiedAt) 필드도 함께 업데이트됩니다.   
이 modifiedAt 필드는 유저들에게 게시글에 표현될 데이터들로 사용하여 게시글이 수정되었을 때만 변경되도록 하고 싶었습니다.   

앞으로는 JPA Auditing으로 받는 시간 필드들은 테이블 관리 용도로 사용하고, 게시글을 수정했을 때 기록될 시간 필드는 따로 만드는 것으로 설계할 예정입니다.
  
</div>
</details>

<details>
<summary><b>다양한 상황의 default 속성</b></summary>
<div markdown="1">

`@ManyToOne`, `@OneToMany`, `@EntityGraph`, .. 등 상황마다 기본 Fetch 전략이 다른 것을 확인했습니다.   
또, JPQL의 Fetch Join과 `@EntityGraph`에서의 Join 방식도 다르다는 것이 헷갈리는 부분이었습니다.   

해당 📌 [블로깅](https://jaeyoungb.tistory.com/285) 정리를 통해 확실하게 정리해둘 수 있었습니다.   
  
</div>
</details>

<details>
<summary><b>WebSocket을 활용한 실시간 채팅 구현</b></summary>
<div markdown="1">

WebSocket과 Redis Pub/Sub 기능을 활용한 실시간 채팅을 구현하려 했습니다.   
끝내 프론트와의 통신 테스트에서 예상한 결과를 얻지 못했고, 백엔드 코드를 구현하긴 했지만 온전한 실시간 채팅 기능을 구현하지 못했습니다.   

결국 기간 내에 온전하게 구현할 수 없겠다 판단했고, 해당 기능을 포기해야 했습니다.   
다음에 적용해볼 기회가 있다면 WebSocket, Redis Pub/Sub 기술과 전체적인 통신 흐름에 대해 깊이 있게 학습하고 완벽하게 구현해내는 것이 목표입니다.   
  
</div>
</details>

<details>
<summary><b>프로젝트 마무리</b></summary>
<div markdown="1">

저는 초기 설계부터 참여한 것이 아닌, 프로젝트 진행 중에 합류하게 되었습니다.   
기능을 구현할 때마다 '초기부터 참여했다면 좀 더 깊이있는 이해를 바탕으로 기능을 구현하기 수월하지 않았을까'하는 아쉬움이 남았습니다.   
처음부터 이미 진행 중인 서비스를 100% 이해한다는 것은 어려웠고, 필요할 때마다 그때그때 이해하려 노력했습니다.   

프로젝트 막바지에는 팀원들의 취업 등 여러 상황으로 인해, 테스트 및 배포가 잘 이루어지지 않았습니다.   
현재는 개인 저장소로 fork해와서 아쉬운 부분이나 리팩토링이 필요한 부분을 개선하고 있습니다.   

</div>
</details>

