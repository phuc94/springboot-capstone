-- Insert data into roles table
INSERT INTO roles (title, description) VALUES
('Super Admin', 'Has access to all systems'),
('Content Manager', 'Can manage games and descriptions'),
('Sales Manager', 'Can manage sales and coupons');

-- Insert data into admins table
INSERT INTO admins (role_id, fullname, email, password) VALUES
(1, 'John Smith', 'admin@gamestore.com', 'hashed_password_1'),
(2, 'Jane Doe', 'content@gamestore.com', 'hashed_password_2'),
(3, 'Mark Johnson', 'sales@gamestore.com', 'hashed_password_3');

-- Insert parent platforms
INSERT INTO platforms (parent_id, name, title) VALUES
(NULL, 'steam', 'Steam'),
(NULL, 'origin', 'Origin'),
(NULL, 'ps', 'PlayStation'),
(NULL, 'nintendo', 'Nintendo'),
(NULL, 'xbox', 'Xbox'),
(NULL, 'uplay', 'Uplay');

-- Insert child platforms
INSERT INTO platforms (parent_id, name, title) VALUES
(1, 'steam_key', 'Steam Key'),
(1, 'steam_gift', 'Steam Gift'),
(6, 'uplay_key', 'Uplay Key'),
(6, 'ubisoft_connect', 'Ubisoft Connect'),
(5, 'xbox_game', 'Xbox Game'),
(5, 'xbox_game_ultimate', 'Xbox Game Pass Ultimate'),
(3, 'ps_game', 'PlayStation Game'),
(3, 'ps_game_plus', 'PlayStation Plus'),
(4, 'nintendo_switch', 'Nintendo Switch Game'),
(4, 'nintendo_membership', 'Nintendo Switch Online Membership');

-- Insert data into sales table
INSERT INTO sales (amount, status, start_date, end_date) VALUES
(0, 'DISABLED', '2025-05-01', '2025-06-01'),
(20, 'ACTIVE', '2025-05-01', '2025-06-01'),
(50, 'ACTIVE', '2025-05-10', '2025-05-20'),
(30, 'EXPIRE', '2025-04-01', '2025-05-01');

-- Insert data into game_description table
INSERT INTO game_description (description) VALUES
('<p><strong>Thành phố: Skylines</strong>&nbsp;là một hiện đại trên mô phỏng thành phố cổ điển.&nbsp;Trò chơi giới thiệu các yếu tố chơi trò chơi mới để nhận ra sự hồi hộp và khó khăn trong việc tạo và duy trì một thành phố thực sự trong khi mở rộng trên một số vùng nhiệt đới được thiết lập tốt của kinh nghiệm xây dựng thành phố.</p><br><p></p><div class="rll-youtube-player" data-src="https://www.youtube.com/embed/AqzMjzQ9pH4" data-id="AqzMjzQ9pH4" data-query="feature=oembed"></div><noscript><iframe title="Cities: Skylines - Playstation®4 Edition - Announcement Trailer | PS4" width="1020" height="574" src="https://www.youtube.com/embed/AqzMjzQ9pH4?feature=oembed" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe></noscript><p></p><br><p>Từ các nhà sản xuất nhượng quyền thành phố Motion in Motion, trò chơi tự hào có một hệ thống giao thông được thực hiện đầy đủ.&nbsp;Nó cũng bao gồm khả năng sửa đổi trò chơi cho phù hợp với lối chơi của bạn như một sự cân bằng đối trọng với mô phỏng nhiều lớp và đầy thách thức.&nbsp;Bạn chỉ bị giới hạn bởi trí tưởng tượng của mình, vì vậy hãy kiểm soát và vươn tới bầu trời!</p><br><h2 class="bb_tag">Mô phỏng nhiều tầng và đầy thách thức</h2><br><p>Xây dựng thành phố của bạn từ đầu trở lên là dễ học, nhưng khó để làm chủ.&nbsp;Vào vai thị trưởng thành phố của bạn, bạn sẽ phải đối mặt với việc cân bằng các yêu cầu thiết yếu như giáo dục, điện nước, cảnh sát, chữa cháy, chăm sóc sức khỏe và nhiều hơn nữa cùng với hệ thống kinh tế thực sự của thành phố.&nbsp;Công dân trong thành phố của bạn phản ứng trôi chảy, với gravitas và với không khí chân thực với vô số kịch bản chơi trò chơi.<br><br><img decoding="async" class="aligncenter entered lazyloading" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/Challenging_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 1" title="Cities: Skylines Steam Key 2" data-lazy-src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/Challenging_simulation.png?t=1561478451" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/Challenging_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 1" title="Cities: Skylines Steam Key 2"></noscript></p><br><h2 class="bb_tag">Mô phỏng giao thông địa phương mở rộng</h2><br><p>Kinh nghiệm sâu rộng của Colossal Order khi phát triển chuỗi Thành phố trong chuyển động được sử dụng đầy đủ trong mô phỏng giao thông địa phương hoàn chỉnh và được chế tạo tốt.<br><br><img decoding="async" class="aligncenter entered lazyloading" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/local_traffic_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 2" title="Cities: Skylines Steam Key 3" data-lazy-src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/local_traffic_simulation.png?t=1561478451" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/local_traffic_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 2" title="Cities: Skylines Steam Key 3"></noscript></p><br><h2 class="bb_tag">Quận và Chính sách</h2><br><p>Không chỉ là một quản trị viên từ tòa thị chính.&nbsp;Chỉ định các phần của thành phố của bạn như là một quận dẫn đến việc áp dụng các chính sách dẫn đến việc bạn trở thành vị trí Thị trưởng cho thành phố của chính bạn.<br><br><img decoding="async" class="aligncenter" src="data:image/svg+xml,%3Csvg%20xmlns=''http://www.w3.org/2000/svg''%20viewBox=''0%200%200%200''%3E%3C/svg%3E" alt="Cities: Skylines Steam Key 3" title="Cities: Skylines Steam Key 4" data-lazy-src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/classic_city_simulation.png?t=1561478451"><noscript><img decoding="async" class="aligncenter" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/classic_city_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 3" title="Cities: Skylines Steam Key 4"></noscript></p><br><h2 class="bb_tag">Sử dụng chu kỳ ngày và đêm</h2><br><p>Thành phố thay đổi trong giờ trong ngày và ảnh hưởng đến lịch trình của công dân.&nbsp;Giao thông chậm hơn rõ rệt vào ban đêm và một số khu vực được phân vùng không hoạt động với hiệu quả đầy đủ.&nbsp;Bản mở rộng này sẽ giúp bạn kiểm soát việc quản lý các khía cạnh khác nhau của chu kỳ ngày và đêm.<br><br><img decoding="async" class="aligncenter" src="data:image/svg+xml,%3Csvg%20xmlns=''http://www.w3.org/2000/svg''%20viewBox=''0%200%200%200''%3E%3C/svg%3E" alt="Cities: Skylines Steam Key 4" title="Cities: Skylines Steam Key 5" data-lazy-src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/day_night_cycle.png?t=1561478451"><noscript><img decoding="async" class="aligncenter" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/day_night_cycle.png?t=1561478451" alt="Cities: Skylines Steam Key 4" title="Cities: Skylines Steam Key 5"></noscript></p><br><h2 class="bb_tag">Hỗ trợ modding mở rộng</h2><br><p>Xây dựng hoặc cải thiện các bản đồ và cấu trúc hiện có.&nbsp;Sau đó, bạn có thể nhập chúng vào trò chơi, chia sẻ chúng cũng như tải xuống các tác phẩm của các nhà xây dựng thành phố khác trên xưởng Steam.</p>'),
('<div class="game_page_autocollapse_ctn expanded"><br><div class="game_page_autocollapse"><br><div class="game_area_description"><br><div class="game_page_autocollapse_ctn expanded"><br><div class="game_page_autocollapse"><br><div class="game_area_description"><br><p><img decoding="async" class="aligncenter entered lazyloading" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_explore.gif?t=1643303985" alt="Dont Starve Together - Mua 1 tặng 1 Steam Gift 1" title="Dont Starve Together - Mua 1 tặng 1 Steam Gift 2" data-lazy-src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_explore.gif?t=1643303985" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_explore.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 1" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 2"></noscript><br><br>Khám phá và khám phá một thế giới khổng lồ được tạo ra theo thủ tục và giàu hệ sinh vật với vô số tài nguyên và các mối đe dọa.&nbsp;Cho dù bạn bám vào thế giới bề mặt, đi quay trong các hang động, lặn sâu hơn vào Kho lưu trữ cổ đại hay đi thuyền đến các đảo Mặt Trăng, sẽ còn rất lâu nữa bạn mới hết việc phải làm.</p><br></div><br><div class="game_area_description"><br><h2>CÙNG NHAU CHIẾN ĐẤU</h2><br><p><img decoding="async" class="aligncenter entered lazyloading" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_fight.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 2" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 3" data-lazy-src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_fight.gif?t=1643303985" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_fight.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 2" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 3"></noscript><br><br>Những con trùm theo mùa, những con quỷ lang thang, những sinh vật bóng tối ẩn nấp, và rất nhiều loài động thực vật sẵn sàng biến bạn thành một con ma ma quái.</p><br></div><br><div class="game_area_description"><br><h2>CÙNG NHAU TRANG TRẠI</h2><br><p><img decoding="async" class="aligncenter entered lazyloading" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gift_farming.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 3" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 4" data-lazy-src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gift_farming.gif?t=1643303985" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gift_farming.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 3" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 4"></noscript><br><br>Cày ruộng và gieo hạt để phát triển trang trại trong mơ của bạn.&nbsp;Hướng đến cây trồng của bạn để giúp những người sống sót đồng hành của bạn được tiếp tục cho ăn và sẵn sàng cho những thử thách sắp tới.</p><br></div><br><div class="game_area_description"><br><h2>CÙNG NHAU XÂY DỰNG</h2><br><p><img decoding="async" class="aligncenter entered lazyloading" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_build.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 4" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 5" data-lazy-src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_build.gif?t=1643303985" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_build.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 4" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 5"></noscript><br><br>Hãy bảo vệ bản thân, bạn bè và mọi thứ bạn đã thu thập được, bởi vì bạn có thể chắc chắn rằng ai đó hoặc điều gì đó sẽ muốn nó trở lại.</p><br></div><br></div><br></div><br><div id="contentForThisGame_ctn"></div><br><div class="game_page_autocollapse_ctn"><br><div id="aboutThisGame" class="game_page_autocollapse" data-panel="{&#039;type&#039;:&#039;PanelGroup&#039;}"><br><div id="game_area_description" class="game_area_description"><br><h2>VỀ TRÒ CHƠI NÀY</h2><br><p><img decoding="async" class="aligncenter entered lazyloading" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_about.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 5" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 6" data-lazy-src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_about.gif?t=1643303985" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://cdn.akamai.steamstatic.com/steam/apps/322330/extras/dst_steam_gif_about.gif?t=1643303985" alt="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 5" title="Don&#039;t Starve Together - Mua 1 tặng 1 Steam Gift 6"></noscript><br><br>Cùng nhau chiến đấu, nông trại, xây dựng và khám phá trong phần mở rộng nhiều người chơi độc lập của trò chơi sinh tồn trong vùng hoang dã không khoan nhượng, Don’t Starve.</p><br><p>Bước vào một thế giới kỳ lạ và chưa được khám phá đầy rẫy những sinh vật kỳ quặc, những mối nguy hiểm tiềm ẩn và những bí mật cổ xưa.&nbsp;Thu thập tài nguyên để chế tạo các vật phẩm và xây dựng các công trình phù hợp với phong cách sinh tồn của bạn.&nbsp;Chơi theo cách của bạn khi bạn làm sáng tỏ những bí ẩn của “The Constant”.</p><br><p>Hợp tác với bạn bè của bạn trong một trò chơi riêng tư hoặc tìm những người bạn mới trực tuyến.&nbsp;Làm việc với những người chơi khác để tồn tại trong môi trường khắc nghiệt hoặc tự mình tấn công.</p><br><p>Làm bất cứ điều gì cần thiết, nhưng quan trọng nhất, Đừng chết đói.</p><br></div><br></div><br></div><br></div><br></div><br></div>'),
('Simulation game where you build and manage your own city.'),
('Racing game with realistic physics and various tracks.'),
('Adventure game with puzzle-solving elements.');

-- Insert data into games table
INSERT INTO games (description_id, title, price, stock, platform_id, sale_id) VALUES
(1, 'Cities Skylines', 80000, 50, 5, 1),
(2, 'Dont Starve Together', 30000, 100, 1, 2),
(3, 'City Architect', 12000, 30, 1, 1),
(4, 'Speed Racers', 99000, 25, 6, 3),
(5, 'Puzzle Quest', 72000, 15, 9, 1);

-- Insert data into game_key table
INSERT INTO game_key (id, game_id, key, order_item_id, activated) VALUES
(1, 1, 'ABCD-EFGH-IJKL-MNOP', 1, false),
(2, 1, 'QRST-UVWX-YZ12-3456', 1, false),
(3, 2, '78AB-CDEF-GHIJ-KLMN', 2, false),
(4, 2, 'OPQR-STUV-WXYZ-1234', 2, false),
(5, 3, '5678-9ABC-DEFG-HIJK', 3, false),
(6, 4, 'LMNO-PQRS-TUVW-XYZ1', 4, false),
(7, 5, '2345-6789-ABCD-EFGH', 5, false);

-- insert data into medias table
insert into medias (game_id, media_type, url, is_primary) values
(1, 'IMAGE', 'https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp', true),
(1, 'IMAGE', 'https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp', false),
(1, 'VIDEO', 'https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp', false),
(2, 'IMAGE', 'https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp', true),
(2, 'VIDEO', 'https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp', false),
(3, 'IMAGE', 'https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp', true),
(4, 'IMAGE', 'https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp', true),
(5, 'IMAGE', 'https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp', true);

-- Insert data into coupon_types table
INSERT INTO coupon_types (type) VALUES
('percentage'),
('fixed');

-- Insert data into coupons table
INSERT INTO coupons (coupon_type_id, code, discount_amount, usage_limit, used_count, start_date, end_date, status) VALUES
(1, 'WELCOME10', 10, 100, 45, '2025-01-01', '2025-12-31', 'ACTIVE'),
(2, 'SUMMER20', 2000, 50, 10, '2025-06-01', '2025-08-31', 'ACTIVE'),
(1, 'EXPIRED25', 25, 200, 198, '2025-01-01', '2025-04-30', 'EXPIRE');

-- Insert data into carts table
INSERT INTO carts (status) VALUES
('ACTIVE'),
('ACTIVE'),
('CHECKED_OUT'),
('ABANDONED'),
('EXPIRED');

-- Insert data into users table
INSERT INTO users (email, password, name, cart_id) VALUES
('user1@example.com', 'hashed_password_user1', 'Alex Johnson', 1),
('user2@example.com', 'hashed_password_user2', 'Emma Smith', 2),
('user3@example.com', 'hashed_password_user3', 'Michael Brown', 3),
('user4@example.com', 'hashed_password_user4', 'Sophia Wilson', 4),
('user5@example.com', 'hashed_password_user5', 'William Davis', 5);

-- Insert data into cart_items table
INSERT INTO cart_items (game_id, cart_id, quantity) VALUES
(1, 1, 1),
(3, 1, 1),
(2, 2, 1),
(5, 2, 1),
(4, 3, 1),
(1, 3, 1),
(2, 4, 1),
(3, 5, 1);

-- Insert data into reviews table
INSERT INTO reviews (game_id, user_id, rating, comment) VALUES
(1, 1, 5, 'Amazing game with stunning visuals!'),
(1, 2, 4, 'Great storyline, but some minor bugs.'),
(2, 3, 5, 'Best multiplayer experience ever!'),
(3, 1, 3,  'Interesting concept but controls are difficult.'),
(4, 4, 4, 'Fun racing game with good variety of tracks.'),
(5, 5, 5, 'Challenging puzzles that kept me engaged for hours.');

-- Insert data into payment_method table
INSERT INTO payment_method (title, name, description, account, image) VALUES
('Stripe', 'Visa/Mastercard', 'Pay with your credit or debit card', 'payment-processor-account', 'https://images.stripeassets.com/fzn2n1nzq965/HTTOloNPhisV9P4hlMPNA/cacf1bb88b9fc492dfad34378d844280/Stripe_icon_-_square.svg'),
('PayPal', 'PayPal', 'Fast and secure payment with PayPal', 'paypal-account', 'https://gamestore.com/images/paypal_logo.png'),
('Bank Transfer', 'Direct Bank Transfer', 'Direct transfer from your bank account', 'bank-account', 'https://gamestore.com/images/bank_logo.png');

-- Insert data into orders table
INSERT INTO orders (payment_method_id, order_status, payment_status, original_amount, discount_amount, total_amount, user_id, session_id) VALUES
(1, 'COMPLETED', 'COMPLETED', 8998.00, 0.00, 8998.00, 1, 'DFOSDdslfkjDJFslLFJ82'),
(2, 'COMPLETED', 'COMPLETED', 4999.00, 499.90, 4499.10, 2, 'DFOSDdslfkjDJFslLFJ82'),
(1, 'PROCESSING', 'COMPLETED', 9998.00, 2000.00, 7998.00, 3, 'DFOSDdslfkjDJFslLFJ82'),
(3, 'CANCELLED', 'REFUNDED', 3999.00, 0.00, 3999.00, 4, 'DFOSDdslfkjDJFslLFJ82'),
(2, 'PENDING', 'PENDING', 1999.00, 0.00, 1999.00, 5, 'DFOSDdslfkjDJFslLFJ82');

-- Insert data into order_item table
INSERT INTO order_item (game_id, order_id, quantity, unit_price, total_price) VALUES
(1, 1, 1, 20000, 20000),
(3, 1, 1, 20000, 20000),
(2, 2, 1, 20000, 20000),
(1, 3, 1, 20000, 20000),
(2, 3, 1, 20000, 20000),
(4, 4, 1, 20000, 20000),
(5, 5, 1, 20000, 20000);

