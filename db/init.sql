CREATE TYPE payment_status AS ENUM (
  'PENDING',
  'COMPLETED',
  'FAILED',
  'REFUNDED'
);

CREATE TYPE order_status AS ENUM (
  'PENDING',
  'PROCESSING',
  'COMPLETED',
  'CANCELLED',
  'REFUNDED'
);

CREATE TYPE cart_status AS ENUM (
  'ACTIVE',
  'CHECKED_OUT',
  'EXPIRED',
  'ABANDONED'
);

CREATE TYPE coupon_status AS ENUM (
  'DISABLED',
  'ACTIVE',
  'EXPIRE'
);

CREATE TYPE media_type AS ENUM (
  'IMAGE',
  'VIDEO'
);

CREATE TYPE sale_status AS ENUM (
  'DISABLED',
  'ACTIVE',
  'EXPIRE'
);

CREATE TABLE IF NOT EXISTS games (
  id SERIAL PRIMARY KEY,
  description_id INTEGER,
  title VARCHAR(255) NOT NULL,
  price INTEGER NOT NULL,
  stock INTEGER NOT NULL DEFAULT 0,
  avg_rating INTEGER NOT NULL DEFAULT 5,
  platform_id INTEGER NOT NULL,
  sale_id INTEGER NOT NULL DEFAULT 0,
  deleted_on TIMESTAMP DEFAULT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS game_description (
  id SERIAL PRIMARY KEY,
  description TEXT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS sales (
  id SERIAL PRIMARY KEY,
  amount INTEGER NOT NULL,
  status sale_status NOT NULL,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS game_key (
  id INTEGER PRIMARY KEY,
  game_id INTEGER NOT NULL,
  key VARCHAR(255) NOT NULL,
  activated BOOLEAN NOT NULL DEFAULT false,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS platforms (
  id SERIAL PRIMARY KEY,
  parent_id INTEGER DEFAULT NULL,
  name VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS admins (
  id SERIAL PRIMARY KEY,
  role_id INTEGER,
  fullname VARCHAR,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS roles (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description VARCHAR,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS coupons (
  id SERIAL PRIMARY KEY,
  coupon_type_id INTEGER NOT NULL,
  code VARCHAR(255) NOT NULL,
  discount_amount INTEGER,
  usage_limit INTEGER,
  used_count INTEGER,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  status coupon_status NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE coupon_types (
  id SERIAL PRIMARY KEY,
  type VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS medias (
  id SERIAL PRIMARY KEY,
  game_id INTEGER NOT NULL,
  media_type media_type NOT NULL,
  url VARCHAR(255) NOT NULL,
  is_primary bool NOT NULL DEFAULT false,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS carts (
  id SERIAL PRIMARY KEY,
  status cart_status NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cart_items (
  id SERIAL PRIMARY KEY,
  game_id INTEGER NOT NULL,
  cart_id INTEGER NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  avatar VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  name VARCHAR,
  address VARCHAR,
  phone VARCHAR,
  cart_id INTEGER,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS reviews (
  id SERIAL PRIMARY KEY,
  game_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  rating INTEGER NOT NULL DEFAULT 5,
  recommend BOOLEAN NOT NULL DEFAULT true,
  comment VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders (
  id SERIAL PRIMARY KEY,
  payment_method_id INTEGER NOT NULL,
  order_status order_status NOT NULL,
  payment_status payment_status NOT NULL,
  original_amount INTEGER,
  discount_amount INTEGER,
  total_amount INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  deleted_on TIMESTAMP DEFAULT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_item (
  id SERIAL PRIMARY KEY,
  game_id INTEGER NOT NULL,
  order_id INTEGER NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS payment_method (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  account VARCHAR(255) NOT NULL,
  image VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- TODO: wishlist
-- TODO: add unique constraint

ALTER TABLE games ADD FOREIGN KEY (sale_id) REFERENCES sales (id);

ALTER TABLE game_key ADD FOREIGN KEY (game_id) REFERENCES games (id);

ALTER TABLE admins ADD FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE games ADD FOREIGN KEY (description_id) REFERENCES game_description (id);

ALTER TABLE medias ADD FOREIGN KEY (game_id) REFERENCES games (id);

ALTER TABLE coupons ADD FOREIGN KEY (coupon_type_id) REFERENCES coupon_types (id);

ALTER TABLE games ADD FOREIGN KEY (platform_id) REFERENCES platforms (id);

ALTER TABLE cart_items ADD FOREIGN KEY (cart_id) REFERENCES carts (id);

ALTER TABLE cart_items ADD FOREIGN KEY (game_id) REFERENCES games (id);

ALTER TABLE users ADD FOREIGN KEY (cart_id) REFERENCES carts (id);

ALTER TABLE reviews ADD FOREIGN KEY (game_id) REFERENCES games (id);

ALTER TABLE reviews ADD FOREIGN KEY (user_id) REFERENCES users (id);


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
(NULL, 'pc', 'PC'),
(NULL, 'playstation', 'PlayStation'),
(NULL, 'xbox', 'Xbox'),
(NULL, 'nintendo', 'Nintendo');

-- Insert child platforms
INSERT INTO platforms (parent_id, name, title) VALUES
(2, 'ps4', 'PlayStation 4'),
(2, 'ps5', 'PlayStation 5'),
(3, 'xbox_one', 'Xbox One'),
(3, 'xbox_series_x', 'Xbox Series X'),
(4, 'switch', 'Nintendo Switch');

-- Insert data into sales table
INSERT INTO sales (amount, status, start_date, end_date) VALUES
(0, 'DISABLED', NULL, NULL), -- Default no sale
(20, 'ACTIVE', '2025-05-01', '2025-06-01'),
(50, 'ACTIVE', '2025-05-10', '2025-05-20'),
(30, 'EXPIRE', '2025-04-01', '2025-05-01');

-- Insert data into game_description table
INSERT INTO game_description (description) VALUES
('<p><strong>Thành phố: Skylines</strong>&nbsp;là một hiện đại trên mô phỏng thành phố cổ điển.&nbsp;Trò chơi giới thiệu các yếu tố chơi trò chơi mới để nhận ra sự hồi hộp và khó khăn trong việc tạo và duy trì một thành phố thực sự trong khi mở rộng trên một số vùng nhiệt đới được thiết lập tốt của kinh nghiệm xây dựng thành phố.</p><br><p></p><div class="rll-youtube-player" data-src="https://www.youtube.com/embed/AqzMjzQ9pH4" data-id="AqzMjzQ9pH4" data-query="feature=oembed"></div><noscript><iframe title="Cities: Skylines - Playstation®4 Edition - Announcement Trailer | PS4" width="1020" height="574" src="https://www.youtube.com/embed/AqzMjzQ9pH4?feature=oembed" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe></noscript><p></p><br><p>Từ các nhà sản xuất nhượng quyền thành phố Motion in Motion, trò chơi tự hào có một hệ thống giao thông được thực hiện đầy đủ.&nbsp;Nó cũng bao gồm khả năng sửa đổi trò chơi cho phù hợp với lối chơi của bạn như một sự cân bằng đối trọng với mô phỏng nhiều lớp và đầy thách thức.&nbsp;Bạn chỉ bị giới hạn bởi trí tưởng tượng của mình, vì vậy hãy kiểm soát và vươn tới bầu trời!</p><br><h2 class="bb_tag">Mô phỏng nhiều tầng và đầy thách thức</h2><br><p>Xây dựng thành phố của bạn từ đầu trở lên là dễ học, nhưng khó để làm chủ.&nbsp;Vào vai thị trưởng thành phố của bạn, bạn sẽ phải đối mặt với việc cân bằng các yêu cầu thiết yếu như giáo dục, điện nước, cảnh sát, chữa cháy, chăm sóc sức khỏe và nhiều hơn nữa cùng với hệ thống kinh tế thực sự của thành phố.&nbsp;Công dân trong thành phố của bạn phản ứng trôi chảy, với gravitas và với không khí chân thực với vô số kịch bản chơi trò chơi.<br><br><img decoding="async" class="aligncenter entered lazyloading" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/Challenging_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 1" title="Cities: Skylines Steam Key 2" data-lazy-src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/Challenging_simulation.png?t=1561478451" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/Challenging_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 1" title="Cities: Skylines Steam Key 2"></noscript></p><br><h2 class="bb_tag">Mô phỏng giao thông địa phương mở rộng</h2><br><p>Kinh nghiệm sâu rộng của Colossal Order khi phát triển chuỗi Thành phố trong chuyển động được sử dụng đầy đủ trong mô phỏng giao thông địa phương hoàn chỉnh và được chế tạo tốt.<br><br><img decoding="async" class="aligncenter entered lazyloading" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/local_traffic_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 2" title="Cities: Skylines Steam Key 3" data-lazy-src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/local_traffic_simulation.png?t=1561478451" data-ll-status="loading"><noscript><img decoding="async" class="aligncenter" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/local_traffic_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 2" title="Cities: Skylines Steam Key 3"></noscript></p><br><h2 class="bb_tag">Quận và Chính sách</h2><br><p>Không chỉ là một quản trị viên từ tòa thị chính.&nbsp;Chỉ định các phần của thành phố của bạn như là một quận dẫn đến việc áp dụng các chính sách dẫn đến việc bạn trở thành vị trí Thị trưởng cho thành phố của chính bạn.<br><br><img decoding="async" class="aligncenter" src="data:image/svg+xml,%3Csvg%20xmlns=''http://www.w3.org/2000/svg''%20viewBox=''0%200%200%200''%3E%3C/svg%3E" alt="Cities: Skylines Steam Key 3" title="Cities: Skylines Steam Key 4" data-lazy-src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/classic_city_simulation.png?t=1561478451"><noscript><img decoding="async" class="aligncenter" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/classic_city_simulation.png?t=1561478451" alt="Cities: Skylines Steam Key 3" title="Cities: Skylines Steam Key 4"></noscript></p><br><h2 class="bb_tag">Sử dụng chu kỳ ngày và đêm</h2><br><p>Thành phố thay đổi trong giờ trong ngày và ảnh hưởng đến lịch trình của công dân.&nbsp;Giao thông chậm hơn rõ rệt vào ban đêm và một số khu vực được phân vùng không hoạt động với hiệu quả đầy đủ.&nbsp;Bản mở rộng này sẽ giúp bạn kiểm soát việc quản lý các khía cạnh khác nhau của chu kỳ ngày và đêm.<br><br><img decoding="async" class="aligncenter" src="data:image/svg+xml,%3Csvg%20xmlns=''http://www.w3.org/2000/svg''%20viewBox=''0%200%200%200''%3E%3C/svg%3E" alt="Cities: Skylines Steam Key 4" title="Cities: Skylines Steam Key 5" data-lazy-src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/day_night_cycle.png?t=1561478451"><noscript><img decoding="async" class="aligncenter" src="https://steamcdn-a.akamaihd.net/steam/apps/255710/extras/day_night_cycle.png?t=1561478451" alt="Cities: Skylines Steam Key 4" title="Cities: Skylines Steam Key 5"></noscript></p><br><h2 class="bb_tag">Hỗ trợ modding mở rộng</h2><br><p>Xây dựng hoặc cải thiện các bản đồ và cấu trúc hiện có.&nbsp;Sau đó, bạn có thể nhập chúng vào trò chơi, chia sẻ chúng cũng như tải xuống các tác phẩm của các nhà xây dựng thành phố khác trên xưởng Steam.</p>'),
('Fast-paced FPS with competitive multiplayer modes.'),
('Simulation game where you build and manage your own city.'),
('Racing game with realistic physics and various tracks.'),
('Adventure game with puzzle-solving elements.');

-- Insert data into games table
INSERT INTO games (description_id, title, price, stock, platform_id, sale_id) VALUES
(1, 'Epic Fantasy VII', 5999, 50, 5, 1),
(2, 'Call to Battle', 4999, 100, 1, 2),
(3, 'City Architect', 2999, 30, 1, 1),
(4, 'Speed Racers', 3999, 25, 6, 3),
(5, 'Puzzle Quest', 1999, 15, 9, 1);

-- Insert data into game_key table
INSERT INTO game_key (id, game_id, key, activated) VALUES
(1, 1, 'ABCD-EFGH-IJKL-MNOP', false),
(2, 1, 'QRST-UVWX-YZ12-3456', false),
(3, 2, '78AB-CDEF-GHIJ-KLMN', false),
(4, 2, 'OPQR-STUV-WXYZ-1234', true),
(5, 3, '5678-9ABC-DEFG-HIJK', false),
(6, 4, 'LMNO-PQRS-TUVW-XYZ1', false),
(7, 5, '2345-6789-ABCD-EFGH', false);

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
INSERT INTO users (email, password, name, address, phone, cart_id) VALUES
('user1@example.com', 'hashed_password_user1', 'Alex Johnson', '123 Main St, New York, NY', '555-123-4567', 1),
('user2@example.com', 'hashed_password_user2', 'Emma Smith', '456 Oak Ave, Los Angeles, CA', '555-234-5678', 2),
('user3@example.com', 'hashed_password_user3', 'Michael Brown', '789 Pine Rd, Chicago, IL', '555-345-6789', 3),
('user4@example.com', 'hashed_password_user4', 'Sophia Wilson', '101 Maple Dr, Houston, TX', '555-456-7890', 4),
('user5@example.com', 'hashed_password_user5', 'William Davis', '202 Cedar Ln, Phoenix, AZ', '555-567-8901', 5);

-- Insert data into cart_items table
INSERT INTO cart_items (game_id, cart_id) VALUES
(1, 1),
(3, 1),
(2, 2),
(5, 2),
(4, 3),
(1, 3),
(2, 4),
(3, 5);

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
('Credit Card', 'Visa/Mastercard', 'Pay with your credit or debit card', 'payment-processor-account', 'https://gamestore.com/images/card_logo.png'),
('PayPal', 'PayPal', 'Fast and secure payment with PayPal', 'paypal-account', 'https://gamestore.com/images/paypal_logo.png'),
('Bank Transfer', 'Direct Bank Transfer', 'Direct transfer from your bank account', 'bank-account', 'https://gamestore.com/images/bank_logo.png');

-- Insert data into orders table
INSERT INTO orders (payment_method_id, order_status, payment_status, original_amount, discount_amount, total_amount, user_id) VALUES
(1, 'COMPLETED', 'COMPLETED', 8998.00, 0.00, 8998.00, 1),
(2, 'COMPLETED', 'COMPLETED', 4999.00, 499.90, 4499.10, 2),
(1, 'PROCESSING', 'COMPLETED', 9998.00, 2000.00, 7998.00, 3),
(3, 'CANCELLED', 'REFUNDED', 3999.00, 0.00, 3999.00, 4),
(2, 'PENDING', 'PENDING', 1999.00, 0.00, 1999.00, 5);

-- Insert data into order_item table
INSERT INTO order_item (game_id, order_id) VALUES
(1, 1),
(3, 1),
(2, 2),
(1, 3),
(2, 3),
(4, 4),
(5, 5);
