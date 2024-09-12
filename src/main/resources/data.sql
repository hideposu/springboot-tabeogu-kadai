--storesテーブル
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (1, '味噌カツ屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しい味噌カツが食べられます。', '3000 - 7000', '12:00 - 23:00', '073-0145', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (2, '手羽先屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しい手羽先が食べられます。', '3000〜5000', '12:00〜23:00', '030-0945', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (3, 'ひつまぶし屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しいひつまぶしが食べられます。', '3000〜6000', '12:00〜23:00', '029-5618', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (4, '味噌おでん屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しい味噌おでんが食べられます。', '2000〜4000', '12:00〜23:00', '989-0555', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (5, 'どて煮屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しいどて煮が食べられます。', '3000〜8000', '12:00〜23:00', '018-2661', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (6, '味噌煮込みうどん屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しい味噌煮込みうどんが食べられます。', '6000〜7000', '12:00〜23:00', '999-6708', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (7, '名古屋コーチンの店', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しい名古屋コーチン料理が食べられます。', '5000〜7000', '12:00〜23:00', '969-5147', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (8, '小倉トースト屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しい小倉トーストが食べられます。', '4000〜7000', '12:00〜23:00', '310-0021', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (9, 'えび煎餅屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しいえび煎餅が食べられます。', '3000〜4000', '12:00〜23:00', '323-1101', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (10, 'ういろう屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しいういろうが食べられます。', '1000〜7000', '12:00〜23:00' ,'370-0806', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')
INSERT IGNORE INTO stores (id, name, image_name, description, price_range, business_hours, postal_code, address, phone_number) VALUES (11, '彩屋', 'IMG_2761-2.jpg', '最寄り駅から徒歩10分。美味しいういろうが食べられます。', '1000〜7000', '12:00〜23:00' ,'370-0806', '愛知県名古屋市熱田区X-XX-XX', '012-345-678')


--usersテーブル
INSERT IGNORE INTO users (id, name, email, password, postal_code, address, phone_number, role_id, enabled) VALUES (1, '坂本龍馬', 'aaaaa@gmail.com', '$2a$08$Y8E8KyfW.NJ.zi1kFbHTpuxCLoiivbuhVAWrOh.SaeOYr21tLcPeO', '123-1234', '愛知県名古屋市熱田区X-XX-XX', '012-345-678', 2, false)
INSERT IGNORE INTO users (id, name, email, password, postal_code, address, phone_number, role_id, enabled) VALUES (2, '西郷隆盛', 'bbbbb@gmail.com', '$2a$08$uVkCDVFWvGorV7j7R.ULMOI7N4XQe/J4cRGJjOdXgr0G61GYdgmXK', '123-4567', '愛知県名古屋市X-XX-XX', '123-456-789', 3, false)
INSERT IGNORE INTO users (id, name, email, password, postal_code, address, phone_number, role_id, enabled) VALUES (3, '侍 義勝', 'yoshikatsu.samurai@example.com', 'password', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 1, false)
INSERT IGNORE INTO users (id, name, email, password, postal_code, address, phone_number, role_id, enabled) VALUES (4, '侍 幸美', 'sachimi.samurai@example.com', 'password', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 1, true)


--rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUE (1, 'ROLE_GENERAL')
INSERT IGNORE INTO roles (id, name) VALUE (2, 'ROLE_ADMIN')
INSERT IGNORE INTO roles (id, name) VALUE (3, 'ROLE_PREMIUM')


--reservationsテーブル
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (1, 1, 1, '2024-03-01', 4)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (2, 2, 1, '2024-03-01', 6)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (3, 3, 1, '2024-03-01', 4)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (4, 4, 1, '2024-03-01', 3)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (5, 5, 1, '2024-03-01', 2)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (6, 6, 1, '2024-03-01', 2)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (7, 7, 1, '2024-03-01', 7)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (8, 8, 1, '2024-03-01', 6)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (9, 9, 1, '2024-03-01', 5)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (10, 10, 1, '2024-03-01', 1)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (11, 11, 1, '2024-03-01', 2)
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (12, 12, 1, '2024-03-01', 1)



--reviewsテーブル
INSERT IGNORE INTO reviews (id, user_id, store_id, score, content) VALUES (1, 1, 1, 5, '美味しくて安い！')
INSERT IGNORE INTO reviews (id, user_id, store_id, score, content) VALUES (2, 1, 1, 4, '美味しい！')
INSERT IGNORE INTO reviews (id, user_id, store_id, score, content) VALUES (3, 1, 1, 3, '安い！')
INSERT IGNORE INTO reviews (id, user_id, store_id, score, content) VALUES (4, 1, 5, 3, '安い！')
