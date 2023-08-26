# :pushpin: Gyul-Box
><b>제주 지역의 주거공간 검색, 커뮤니티 서비스</b>
>
>제주 한달살이를 준비하는 사용자들에게 특화된 서비스입니다.   
>주거공간(다가구주택, 다중주택, 공동주택, 다세대주택, 오피스텔, 단독주택)

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
### 1. 게시판, 게시판 댓글, 게시판 좋아요 도메인 기능 구현
 
<details>
<summary>상세 설명</summary>
<div markdown="1">

#### 1-1. 연관 관계 그림

<img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/0e7d6ac5-a7e2-4cf6-8911-8abd2bfb2a4a">

#### 1-2. 각 도메인 Service Layer 코드

- [게시판](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/post/service/PostService.java)   
- [게시판 댓글](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/postcomment/service/PostCommentService.java)   
- [게시판 좋아요](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/postlike/service/PostLikeService.java)

#### 1-3. 내용

- 특정 게시물이 삭제될 경우, 해당 게시물의 댓글, 좋아요 데이터도 삭제되도록 구현하였습니다.   
```Java
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<PostComment> postComments = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<PostLike> postLikes = new ArrayList<>();
```

</br>

- 게시물과 댓글이 수정, 삭제할 경우 작성한 본인만 가능하도록 처리하였습니다.   
```Java
    @Transactional
    public Post updatePost(User user, PostDto.Patch patchDto) {
        Post verifiedPost = findVerifiedPost(patchDto.getPostId());

        if (verifiedPost.isAuthor(user)) {
            verifiedPost.update(patchDto.getTitle(), patchDto.getContent());
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_EDIT);
        }

        return verifiedPost;
    }
```

</br>

- 게시물 조회 시, 조회 수가 1씩 증가되도록 Post 엔티티 내에 필드값 변경 메서드를 만들었습니다.   
  - 서비스단의 로직에서 처리하지 않은 이유는 JPA의 변경감지를 이용하고, 코드 재사용성을 높이기 위함입니다.   
```Java
    @Transactional
    public PostDto.Response findPostByPostId(long postId) {
        Post verifiedPost = findVerifiedPost(postId);
        verifiedPost.updateViews(); // 단일 게시글 조회 시, 조회 수 1씩 증가

        return postMapper.postToResponseDto(verifiedPost);
    }
```

</br>

- 게시글에 대한 좋아요를 2번 누를 경우, 취소되도록 기능을 구현했습니다.   
```Java
    @Transactional
    public void pushLike(Post post, User user) {
        checkSameUser(post, user);
        postLikeRepository.findByPostAndUser(post, user)
                .ifPresentOrElse(
                        postLike -> postLikeRepository.deleteById(postLike.getId()),
                        () -> {
                            PostLike postLike = PostLike.builder().post(post).user(user).build();
                            postLikeRepository.save(postLike);
                        });
    }
```

</div>
</details>

</br>

### 2. 외부 Open API를 활용하여 필요한 데이터 저장



<details>
<summary>상세 설명</summary>
<div markdown="1">
  
#### 2-1. 사용 목적

- 지역에 따른 주거공간 데이터 필요
- 프론트단의 지도 인터페이스에 활용될 주거공간의 위도, 경도 데이터 필요

#### 2-2. 호출 흐름

1. 지역 코드(시군구, 법정동 코드)를 파라미터로 외부 Open API를 호출합니다.   
2. 응답된 데이터는 서비스단으로 이동하여 가공됩니다.   
    - 주거공간의 타입을 선별   
    - 주거공간의 위도, 경도 데이터를 위해 또 다른 외부 Open API를 호출   
    - HouseInfo 엔티티 필드에 맞는 데이터들을 뽑아내 DB에 저장   
(외부 Open API의 호출은 모두 WebClient 라이브러리를 이용하였습니다)

#### 2-3. 코드
:pushpin: [OpenApiController.Java](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/controller/OpenApiController.java)   
:pushpin: [OpenApiService.Java](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/service/OpenApiService.java)   
:pushpin: [GeoPointService.Java](https://github.com/bangjaeyoung/gyul-box/blob/main/server/src/main/java/jeju/oneroom/openapi/service/GeoPointService.java)   

</div>
</details>

</br>

### 코드 리팩토링 및 N+1 문제 해결

<details>
<summary>상세 설명</summary>
<div markdown="1">
</div>
</details>

</br>

### WebSocket을 활용한 실시간 채팅 기능 구현

<details>
<summary>상세 설명</summary>
<div markdown="1">
</div>
</details>

</br>

### RDS 마이그레이션 및 AWS EC2 배포 작업

<details>
<summary>상세 설명</summary>
<div markdown="1">
</div>
</details>

</br>

## 5. 핵심 트러블 슈팅
### 5.1. 첫 번째 문제
- 문제문제

</br>

## 6. 그 외 트러블 슈팅
