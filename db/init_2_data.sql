-- Insert data into roles table
INSERT INTO roles (title, description) VALUES
('ROLE_SUPER_ADMIN', 'Has access to all systems'),
('ROLE_CONTENT_ADMIN', 'Can manage games and descriptions'),
('ROLE_SALE_ADMIN', 'Can manage sales and coupons'),
('ROLE_BASE_ADMIN', 'Can manage games and descriptions'),
('ROLE_USER', 'Regular client user');

-- Insert data into admins table
INSERT INTO admins (role_id, name, email, password) VALUES
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

-- Insert data into coupon_types table
INSERT INTO coupon_types (type) VALUES
('base');

-- Insert data into coupons table
INSERT INTO coupons (coupon_type_id, code, discount_amount, usage_limit, used_count, start_date, end_date, status, coupon_unit, description) VALUES
(1, 'WELCOME10', 10, 100, 45, '2025-01-01', '2025-12-31', 'ACTIVE', 'PERCENTAGE', 'Some description about Counpon'),
(1, 'SUMMER20', 2000, 50, 10, '2025-06-01', '2025-08-31', 'ACTIVE', 'PERCENTAGE', 'Some description about Counpon'),
(1, 'EXPIRED25', 25, 200, 198, '2025-01-01', '2025-04-30', 'EXPIRE', 'FIXED', 'Some description about Counpon');

-- Insert data into carts table
INSERT INTO carts (status) VALUES
('ACTIVE'),
('ACTIVE'),
('CHECKED_OUT'),
('ABANDONED'),
('EXPIRED');

-- Insert data into users table
INSERT INTO users (email, role_id, password, name, cart_id) VALUES
('user1@example.com', '5', 'hashed_password_user1', 'Alex Johnson', 1),
('user2@example.com', '5', 'hashed_password_user2', 'Emma Smith', 2),
('user3@example.com', '5', 'hashed_password_user3', 'Michael Brown', 3),
('user4@example.com', '5', 'hashed_password_user4', 'Sophia Wilson', 4),
('user5@example.com', '5', 'hashed_password_user5', 'William Davis', 5);

-- Insert data into payment_method table
INSERT INTO payment_method (title, name, description, account, image) VALUES
('Stripe', 'Visa/Mastercard', 'Pay with your credit or debit card', 'payment-processor-account', 'https://images.stripeassets.com/fzn2n1nzq965/HTTOloNPhisV9P4hlMPNA/cacf1bb88b9fc492dfad34378d844280/Stripe_icon_-_square.svg'),
('PayPal', 'PayPal', 'Fast and secure payment with PayPal', 'paypal-account', 'https://gamestore.com/images/paypal_logo.png'),
('Bank Transfer', 'Direct Bank Transfer', 'Direct transfer from your bank account', 'bank-account', 'https://gamestore.com/images/bank_logo.png');

