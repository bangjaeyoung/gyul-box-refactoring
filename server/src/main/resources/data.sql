--- AREA TABLE DATA
INSERT INTO AREA (AREA_ID, AREA_NAME, LATITUDE, LONGITUDE)
VALUES (1, '가나읍', 37.23456, 126.78901),
       (2, '다라읍', 37.34567, 127.89012),
       (3, '마바읍', 37.45678, 128.90123),
       (4, '사아읍', 37.56789, 129.01234),
       (5, '자차읍', 37.67890, 130.12345);


--- HOUSE_INFO TABLE DATA
INSERT INTO HOUSE_INFO (HOUSE_INFO_ID, BUILD_UES, BUILDING_STRUCTURE, LATITUDE, LONGITUDE,
                        ELEVATOR, GRND_FLOOR, HOUSE_HOLD, HOUSE_NAME, MAIN_PURPS_CD_NM, PLAT_PLC, AVG_RATE,
                        BUILDING_RATE, INTERIOR_RATE, LOCATION_RATE, SECURITY_RATE, TRAFFIC_RATE, UGRND_FLOOR,
                        USE_APR_DAY, AREA_ID)
VALUES (1, 'Commercial', 'Concrete', 37.23456, 126.78901, 2, 2, 6,
        'Office Building 1', 'Commercial', '456 Elm Street', 4.2, 4.1, 4.4, 4.3, 4.5, 4.6, 1, '2023-09-29', 1),
       (2, 'Residential', 'Wood', 37.23456, 126.78901, 3, 1, 4, 'My House 3',
        'Residential', '789 Oak Street', 4.6, 4.2, 4.7, 4.5, 4.8, 4.9, 0, '2023-09-30', 1),
       (3, 'Commercial', 'Brick', 37.23456, 126.78901, 1, 3, 8, 'Shopping Mall 1',
        'Commercial', '101 Maple Street', 4.3, 4.0, 4.1, 4.4, 4.6, 4.7, 2, '2023-09-30', 1),
       (4, 'Residential', 'Concrete', 37.23456, 126.78901, 2, 2, 5, 'My House 5',
        'Residential', '202 Pine Street', 4.4, 4.3, 4.5, 4.2, 4.7, 4.8, 0, '2023-10-01', 1),
       (5, 'Commercial', 'Concrete', 37.23456, 126.78901, 3, 1, 7,
        'Business Center 1', 'Commercial', '303 Cedar Street', 4.5, 4.4, 4.6, 4.3, 4.8, 4.9, 1, '2023-10-01', 1),

       (6, 'Commercial', 'Concrete', 37.23456, 126.78901, 2, 2, 6,
        'Office Building 1', 'Commercial', '456 Elm Street', 4.2, 4.1, 4.4, 4.3, 4.5, 4.6, 1, '2023-09-29', 2),
       (7, 'Residential', 'Wood', 37.23456, 126.78901, 3, 1, 4, 'My House 3',
        'Residential', '789 Oak Street', 4.6, 4.2, 4.7, 4.5, 4.8, 4.9, 0, '2023-09-30', 2),
       (8, 'Commercial', 'Brick', 37.23456, 126.78901, 1, 3, 8, 'Shopping Mall 1',
        'Commercial', '101 Maple Street', 4.3, 4.0, 4.1, 4.4, 4.6, 4.7, 2, '2023-09-30', 2),
       (9, 'Residential', 'Concrete', 37.23456, 126.78901, 2, 2, 5, 'My House 5',
        'Residential', '202 Pine Street', 4.4, 4.3, 4.5, 4.2, 4.7, 4.8, 0, '2023-10-01', 2),
       (10, 'Commercial', 'Concrete', 37.23456, 126.78901, 3, 1, 7,
        'Business Center 1', 'Commercial', '303 Cedar Street', 4.5, 4.4, 4.6, 4.3, 4.8, 4.9, 1, '2023-10-01', 2),

       (11, 'Commercial', 'Concrete', 37.23456, 126.78901, 2, 2, 6,
        'Office Building 1', 'Commercial', '456 Elm Street', 4.2, 4.1, 4.4, 4.3, 4.5, 4.6, 1, '2023-09-29', 3),
       (12, 'Residential', 'Wood', 37.23456, 126.78901, 3, 1, 4, 'My House 3',
        'Residential', '789 Oak Street', 4.6, 4.2, 4.7, 4.5, 4.8, 4.9, 0, '2023-09-30', 3),
       (13, 'Commercial', 'Brick', 37.23456, 126.78901, 1, 3, 8,
        'Shopping Mall 1', 'Commercial', '101 Maple Street', 4.3, 4.0, 4.1, 4.4, 4.6, 4.7, 2, '2023-09-30', 3),
       (14, 'Residential', 'Concrete', 37.23456, 126.78901, 2, 2, 5, 'My House 5',
        'Residential', '202 Pine Street', 4.4, 4.3, 4.5, 4.2, 4.7, 4.8, 0, '2023-10-01', 3),
       (15, 'Commercial', 'Concrete', 37.23456, 126.78901, 3, 1, 7,
        'Business Center 1', 'Commercial', '303 Cedar Street', 4.5, 4.4, 4.6, 4.3, 4.8, 4.9, 1, '2023-10-01', 3),

       (16, 'Commercial', 'Concrete', 37.23456, 126.78901, 2, 2, 6,
        'Office Building 1', 'Commercial', '456 Elm Street', 4.2, 4.1, 4.4, 4.3, 4.5, 4.6, 1, '2023-09-29', 4),
       (17, 'Residential', 'Wood', 37.23456, 126.78901, 3, 1, 4, 'My House 3',
        'Residential', '789 Oak Street', 4.6, 4.2, 4.7, 4.5, 4.8, 4.9, 0, '2023-09-30', 4),
       (18, 'Commercial', 'Brick', 37.23456, 126.78901, 1, 3, 8,
        'Shopping Mall 1', 'Commercial', '101 Maple Street', 4.3, 4.0, 4.1, 4.4, 4.6, 4.7, 2, '2023-09-30', 4),
       (19, 'Residential', 'Concrete', 37.23456, 126.78901, 2, 2, 5, 'My House 5',
        'Residential', '202 Pine Street', 4.4, 4.3, 4.5, 4.2, 4.7, 4.8, 0, '2023-10-01', 4),
       (20, 'Commercial', 'Concrete', 37.23456, 126.78901, 3, 1, 7,
        'Business Center 1', 'Commercial', '303 Cedar Street', 4.5, 4.4, 4.6, 4.3, 4.8, 4.9, 1, '2023-10-01', 4),

       (21, 'Commercial', 'Concrete', 37.23456, 126.78901, 2, 2, 6,
        'Office Building 1', 'Commercial', '456 Elm Street', 4.2, 4.1, 4.4, 4.3, 4.5, 4.6, 1, '2023-09-29', 5),
       (22, 'Residential', 'Wood', 37.23456, 126.78901, 3, 1, 4, 'My House 3',
        'Residential', '789 Oak Street', 4.6, 4.2, 4.7, 4.5, 4.8, 4.9, 0, '2023-09-30', 5),
       (23, 'Commercial', 'Brick', 37.23456, 126.78901, 1, 3, 8,
        'Shopping Mall 1', 'Commercial', '101 Maple Street', 4.3, 4.0, 4.1, 4.4, 4.6, 4.7, 2, '2023-09-30', 5),
       (24, 'Residential', 'Concrete', 37.23456, 126.78901, 2, 2, 5, 'My House 5',
        'Residential', '202 Pine Street', 4.4, 4.3, 4.5, 4.2, 4.7, 4.8, 0, '2023-10-01', 5),
       (25, 'Commercial', 'Concrete', 37.23456, 126.78901, 3, 1, 7,
        'Business Center 1', 'Commercial', '303 Cedar Street', 4.5, 4.4, 4.6, 4.3, 4.8, 4.9, 1, '2023-10-01', 5);


--- USER TABLE DATA
INSERT INTO USERS (USER_ID, EMAIL, NICKNAME, PROFILE_IMAGE_URL, AREA_ID)
VALUES (1, 'usr1@example.com', '사용자1 닉네임', 'https://example.com/profile2.jpg', 1),
       (2, 'usr2@example.com', '사용자2 닉네임', 'https://example.com/profile2.jpg', 2),
       (3, 'usr3@example.com', '사용자3 닉네임', 'https://example.com/profile3.jpg', 3),
       (4, 'usr4@example.com', '사용자4 닉네임', 'https://example.com/profile4.jpg', 4),
       (5, 'usr5@example.com', '사용자5 닉네임', 'https://example.com/profile5.jpg', 5);


--- USER_ROLES TABLE DATA
INSERT INTO USER_ROLES (USER_USER_ID, ROLES)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_USER'),
       (4, 'ROLE_USER'),
       (5, 'ROLE_USER');


--- MESSAGE_ROOM TABLE DATA
INSERT INTO MESSAGE_ROOM (MESSAGE_ROOM_ID, LAST_MESSAGE, LAST_SENDER_ID, MESSAGE_ROOM_STATUS,
                          RECEIVER_ID, SENDER_ID)
VALUES (1, 'Hello', 1, 'UNCHECK', 1, 2),
       (2, '안녕하세요', 3, 'UNCHECK', 3, 4),
       (3, 'Hi there!', 5, 'CHECK', 5, 1);

--- MESSAGE TABLE DATA
INSERT INTO MESSAGE (MESSAGE_ID, CONTENT, MESSAGE_ROOM_MESSAGE_ROOM_ID, RECEIVER_USER_ID,
                     SENDER_USER_ID)
VALUES (1, '안녕하세요', 1, 1, 2),
       (2, '안녕하세요, 어떻게 지내세요?', 1, 2, 1),
       (3, '안녕하세요! 저도 잘 지내고 있어요.', 1, 1, 2),

       (4, '안녕하세요', 2, 3, 4),
       (5, '안녕하세요, 어떻게 지내세요?', 2, 4, 3),
       (6, '안녕하세요! 저도 잘 지내고 있어요.', 2, 3, 4),

       (7, '안녕하세요', 3, 1, 5),
       (8, '안녕하세요, 어떻게 지내세요?', 3, 5, 1),
       (9, '안녕하세요! 저도 잘 지내고 있어요.', 3, 1, 5);


--- POST TABLE DATA
INSERT INTO POST (POST_ID, CONTENT, TITLE, VIEWS, HOUSE_INFO_ID, USER_ID)
VALUES (1, '포스트 내용', '포스트 제목', 12, 1, 1),
       (2, '두 번째 포스트 내용', '두 번째 포스트 제목', 8, 1, 2),
       (3, '세 번째 포스트 내용', '세 번째 포스트 제목', 15, 2, 3),
       (4, '네 번째 포스트 내용', '네 번째 포스트 제목', 10, 2, 4),
       (5, '다섯 번째 포스트 내용', '다섯 번째 포스트 제목', 20, 3, 5),
       (6, '포스트 내용', '포스트 제목', 12, 3, 1),
       (7, '두 번째 포스트 내용', '두 번째 포스트 제목', 8, 4, 2),
       (8, '세 번째 포스트 내용', '세 번째 포스트 제목', 15, 4, 3),
       (9, '네 번째 포스트 내용', '네 번째 포스트 제목', 10, 5, 4),
       (10, '다섯 번째 포스트 내용', '다섯 번째 포스트 제목', 20, 5, 5),
       (11, '포스트 내용', '포스트 제목', 12, 6, 1),
       (12, '두 번째 포스트 내용', '두 번째 포스트 제목', 8, 6, 2),
       (13, '세 번째 포스트 내용', '세 번째 포스트 제목', 15, 7, 3),
       (14, '네 번째 포스트 내용', '네 번째 포스트 제목', 10, 7, 4),
       (15, '다섯 번째 포스트 내용', '다섯 번째 포스트 제목', 20, 8, 5),
       (16, '포스트 내용', '포스트 제목', 12, 8, 1),
       (17, '두 번째 포스트 내용', '두 번째 포스트 제목', 8, 9, 2),
       (18, '세 번째 포스트 내용', '세 번째 포스트 제목', 15, 10, 3),
       (19, '네 번째 포스트 내용', '네 번째 포스트 제목', 10, 11, 4),
       (20, '다섯 번째 포스트 내용', '다섯 번째 포스트 제목', 20, 12, 5);


--- POST_COMMENT TABLE DATA
INSERT INTO POST_COMMENT (POST_COMMENT_ID, CONTENT, POST_ID, USER_ID)
VALUES (1, '댓글 내용 1', 13, 1),
       (2, '댓글 내용 2', 2, 2),
       (3, '댓글 내용 3', 5, 3),
       (4, '댓글 내용 4', 13, 4),
       (5, '댓글 내용 5', 11, 5),
       (6, '댓글 내용 6', 12, 1),
       (7, '댓글 내용 7', 15, 2),
       (8, '댓글 내용 8', 17, 3),
       (9, '댓글 내용 9', 19, 4),
       (10, '댓글 내용 10', 16, 5),
       (11, '댓글 내용 11', 12, 1),
       (12, '댓글 내용 12', 1, 2),
       (13, '댓글 내용 13', 2, 3),
       (14, '댓글 내용 14', 3, 4),
       (15, '댓글 내용 15', 4, 5),
       (16, '댓글 내용 16', 5, 1),
       (17, '댓글 내용 17', 6, 2),
       (18, '댓글 내용 18', 7, 3),
       (19, '댓글 내용 19', 8, 4),
       (20, '댓글 내용 20', 9, 5);


--- POST_LIKE TABLE DATA
INSERT INTO POST_LIKE (POST_LIKE_ID, POST_ID, USER_ID)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 4, 4),
       (5, 5, 5),
       (6, 6, 1),
       (7, 7, 2),
       (8, 8, 3),
       (9, 9, 4),
       (10, 10, 5),
       (11, 11, 1),
       (12, 12, 2),
       (13, 13, 3),
       (14, 14, 4),
       (15, 15, 5),
       (16, 16, 1),
       (17, 17, 2),
       (18, 18, 3),
       (19, 19, 4),
       (20, 20, 5);


--- REVIEW TABLE DATA
INSERT INTO REVIEW (REVIEW_ID, ADMIN_COST, ADVANTAGE, BUILDING_NAME, BUILDING_TYPE,
                    DISADVANTAGE, FLOOR, AVG_RATE, BUILDING_RATE, INTERIOR_RATE, LOCATION_RATE, SECURITY_RATE,
                    TRAFFIC_RATE, RESIDENCE_YEAR, HOUSE_INFO_ID, USER_ID)
VALUES (1, 100, '장점 내용', '건물 이름', '건물 타입', '단점 내용', 5, 4.5, 4.0, 4.2, 4.8, 4.6,
        4.7, 2020, 1, 1),
       (2, 120, '장점 내용 2', '건물 이름 2', '건물 타입 2', '단점 내용 2', 6, 4.4, 4.1, 4.3, 4.7,
        4.6, 4.8, 2021, 2, 2),
       (3, 150, '장점 내용 3', '건물 이름 3', '건물 타입 3', '단점 내용 3', 7, 4.2, 4.0, 4.2, 4.8,
        4.7, 4.9, 2019, 3, 3),
       (4, 80, '장점 내용 4', '건물 이름 4', '건물 타입 4', '단점 내용 4', 8, 4.3, 4.1, 4.4, 4.6,
        4.5, 4.7, 2018, 4, 4),
       (5, 90, '장점 내용 5', '건물 이름 5', '건물 타입 5', '단점 내용 5', 9, 4.1, 3.9, 4.3, 4.5,
        4.8, 4.6, 2022, 5, 5),
       (6, 110, '장점 내용 6', '건물 이름 6', '건물 타입 6', '단점 내용 6', 10, 4.0, 4.2, 4.5,
        4.7,
        4.6, 4.8, 2023, 6, 1),
       (7, 130, '장점 내용 7', '건물 이름 7', '건물 타입 7', '단점 내용 7', 11, 4.4, 4.3, 4.4,
        4.9,
        4.5, 4.8, 2020, 7, 2),
       (8, 140, '장점 내용 8', '건물 이름 8', '건물 타입 8', '단점 내용 8', 12, 4.3, 4.2, 4.4,
        4.8,
        4.7, 4.7, 2019, 8, 3),
       (9, 160, '장점 내용 9', '건물 이름 9', '건물 타입 9', '단점 내용 9', 13, 4.2, 4.1, 4.3,
        4.9,
        4.6, 4.9, 2018, 9, 4),
       (10, 170, '장점 내용 10', '건물 이름 10', '건물 타입 10', '단점 내용 10', 14, 4.1, 4.0,
        4.5,
        4.7, 4.8, 4.8, 2021, 10, 5),
       (11, 180, '장점 내용 11', '건물 이름 11', '건물 타입 11', '단점 내용 11', 15, 4.5, 4.4,
        4.2, 4.9, 4.6, 4.7, 2020, 11, 1),
       (12, 190, '장점 내용 12', '건물 이름 12', '건물 타입 12', '단점 내용 12', 16, 4.4, 4.3,
        4.3, 4.8, 4.7, 4.6, 2019, 12, 2),
       (13, 200, '장점 내용 13', '건물 이름 13', '건물 타입 13', '단점 내용 13', 17, 4.3, 4.2,
        4.4, 4.9, 4.5, 4.8, 2018, 13, 3),
       (14, 210, '장점 내용 14', '건물 이름 14', '건물 타입 14', '단점 내용 14', 18, 4.2, 4.1,
        4.5, 4.7, 4.8, 4.7, 2021, 14, 4),
       (15, 220, '장점 내용 15', '건물 이름 15', '건물 타입 15', '단점 내용 15', 19, 4.1, 4.0,
        4.3, 4.8, 4.7, 4.6, 2022, 15, 5),
       (16, 230, '장점 내용 16', '건물 이름 16', '건물 타입 16', '단점 내용 16', 20, 4.0, 4.2,
        4.4, 4.9, 4.6, 4.8, 2023, 16, 1),
       (17, 240, '장점 내용 17', '건물 이름 17', '건물 타입 17', '단점 내용 17', 21, 4.4, 4.3,
        4.2, 4.8, 4.7, 4.7, 2020, 17, 2),
       (18, 250, '장점 내용 18', '건물 이름 18', '건물 타입 18', '단점 내용 18', 22, 4.3, 4.2,
        4.4, 4.9, 4.5, 4.9, 2019, 18, 3),
       (19, 260, '장점 내용 19', '건물 이름 19', '건물 타입 19', '단점 내용 19', 23, 4.2, 4.1,
        4.5, 4.7, 4.8, 4.8, 2018, 19, 4),
       (20, 270, '장점 내용 20', '건물 이름 20', '건물 타입 20', '단점 내용 20', 24, 4.1, 4.0,
        4.3, 4.8, 4.7, 4.6, 2021, 20, 5);


--- REVIEW_LIKE TABLE DATA
INSERT INTO REVIEW_LIKE (REVIEW_LIKE_ID, REVIEW_ID, USER_ID)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 4, 4),
       (5, 5, 5),
       (6, 6, 1),
       (7, 7, 2),
       (8, 8, 3),
       (9, 9, 4),
       (10, 10, 5),
       (11, 11, 1),
       (12, 12, 2),
       (13, 13, 3),
       (14, 14, 4),
       (15, 15, 5),
       (16, 16, 1),
       (17, 17, 2),
       (18, 18, 3),
       (19, 19, 4),
       (20, 20, 5);
