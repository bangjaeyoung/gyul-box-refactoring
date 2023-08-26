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

## 4. 핵심 기능
이 서비스의 핵심 기능은 주거공간 조회 기능입니다.   
다음 기능의 흐름을 보면, 서비스가 어떻게 동작하는지 파악할 수 있습니다.

<details>
  <summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1. 전체 흐름
<img src="https://github.com/bangjaeyoung/gyul-box/assets/80241053/72a29c5c-dba1-46e0-8411-5c9544181cb6">

### 4.2. Controller
- 제주도 지역의 동 이름으로 해당 동의 주거공간들을 조회합니다.
```Java
    @GetMapping("/areas/search")
    public ResponseEntity<AreaDto.Response> getArea(@RequestParam("name") @NotBlank String areaName) {
        AreaDto.Response response = areaService.findAreaByAreaName(areaName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
```

:pushpin: [원본 코드 확인](https://github.com/bangjaeyoung/gyul-box/blob/a19f25dffbd728449c3f2e419047fa85938f3452/server/src/main/java/jeju/oneroom/area/controller/AreaController.java#L18C1-L23C6)

### 4.3. Service
- Controller에서 넘어온 동 이름으로 로직을 처리합니다.
```Java
    public AreaDto.Response findAreaByAreaName(String areaName) {
        Area area = areaRepository.findByAreaName(areaName);

        return areaMapper.areaToResponseDto(area);
    }
```

:pushpin: [원본 코드 확인](https://github.com/bangjaeyoung/gyul-box/blob/a19f25dffbd728449c3f2e419047fa85938f3452/server/src/main/java/jeju/oneroom/area/service/AreaService.java#L21C4-L25C6)

### 4.4. Repository
- DB에 해당 동 이름에 맞는 Area(지역) 정보를 요청하고 받아옵니다.
```Java
    public interface AreaRepository extends JpaRepository<Area, Long> {
      Area findByAreaName(String areaName);
    }
```

:pushpin: [원본 코드 확인](https://github.com/bangjaeyoung/gyul-box/blob/a19f25dffbd728449c3f2e419047fa85938f3452/server/src/main/java/jeju/oneroom/area/repository/AreaRepository.java#L6C1-L8C2)

- 받아온 데이터는 다시 Service - Controller를 거쳐 프론트엔드 서버로 전달됩니다.   
- 전달되는 응답 DTO는 다음과 같습니다.
```Java
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long areaCode;  // 지역 코드
        private String areaName;  // 지역 이름(동 이름)
        private Coordinate coordinate;  // 위도, 경도(지도 인터페이스를 사용하는 프론트단에서 필요)
        private List<HouseInfoDto.SimpleResponse> houseInfos;  // 주거정보(건물 이름, 건물 주소, 평균 리뷰점수, 리뷰 갯수)
    }
```

:pushpin: [Area 응답 DTO 코드](https://github.com/bangjaeyoung/gyul-box/blob/a19f25dffbd728449c3f2e419047fa85938f3452/server/src/main/java/jeju/oneroom/area/dto/AreaDto.java#L11C1-L20C6)   
:pushpin: [HouseInfo 응답 DTO 코드](https://github.com/bangjaeyoung/gyul-box/blob/a19f25dffbd728449c3f2e419047fa85938f3452/server/src/main/java/jeju/oneroom/houseInfo/dto/HouseInfoDto.java#L36C5-L47C6)

</div>
</details>

</br>

## 5. 핵심 트러블 슈팅
### 5.1. 첫 번째 문제
- 문제문제

</br>

## 6. 그 외 트러블 슈팅
