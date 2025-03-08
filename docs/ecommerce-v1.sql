CREATE TYPE "esrb_rating" AS ENUM (
  'EVERYONE',
  'EVERYONE_10',
  'TEEN',
  'MATURE',
  'ADULT',
  'PENDING',
  'PENDING_17'
);

CREATE TYPE "media_type" AS ENUM (
  'IMAGE',
  'VIDEO'
);

CREATE TYPE "order_status" AS ENUM (
  'PENDING',
  'PROCESSING',
  'CANCELLED',
  'FAILED'
);

CREATE TYPE "payment_method" AS ENUM (
  'STRIPE'
);

CREATE TYPE "payment_status" AS ENUM (
  'PENDING',
  'PAID',
  'FAIL'
);

CREATE TYPE "item_type" AS ENUM (
  'GAME',
  'MERCHANDISE'
);

CREATE TYPE "coupon_status" AS ENUM (
  'ACTIVE',
  'DISABLE',
  'EXPIRE'
);

CREATE TABLE "games" (
  "id" interger PRIMARY KEY,
  "is_dlc" boolean,
  "game_dlc_id" interger,
  "title" varchar,
  "price" double,
  "publisher_id" interger,
  "realease_date" timestamp,
  "size" double,
  "esrb_rating" esrb_rating,
  "developer_id" interger,
  "description_id" interger,
  "no_player_id" interger
);

CREATE TABLE "support_language" (
  "id" interger PRIMARY KEY,
  "language" varchar
);

CREATE TABLE "game_support_language" (
  "id" interger PRIMARY KEY,
  "support_language_id" interger,
  "game_id" interger
);

CREATE TABLE "genre" (
  "id" interger PRIMARY KEY,
  "genre" varchar
);

CREATE TABLE "game_genre" (
  "id" interger PRIMARY KEY,
  "genre_id" interger,
  "game_id" interger
);

CREATE TABLE "no_player" (
  "id" interger PRIMARY KEY,
  "number" varchar
);

CREATE TABLE "medias" (
  "id" interger PRIMARY KEY,
  "product_id" interger,
  "media_type" media_type,
  "url" varchar,
  "is_primary" bool
);

CREATE TABLE "game_description" (
  "id" interger PRIMARY KEY,
  "descrition" varchar
);

CREATE TABLE "publishers" (
  "id" interger PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "publisher_sale" (
  "id" interger PRIMARY KEY,
  "sale_id" interger,
  "publisher_id" interger
);

CREATE TABLE "developer" (
  "id" interger PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "play_mode" (
  "id" interger PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "game_play_mode" (
  "id" interger PRIMARY KEY,
  "game_id" interger,
  "play_mode_id" interger
);

CREATE TABLE "users" (
  "id" interger PRIMARY KEY,
  "email" varchar,
  "password" varchar,
  "name" varchar,
  "address" varchar
);

CREATE TABLE "orders" (
  "id" interger PRIMARY KEY,
  "user_id" interger,
  "order_status" order_status,
  "total_amount" double,
  "payment_method" payment_method,
  "payment_status" payment_status,
  "discount_amount" double,
  "original_amount" double
);

CREATE TABLE "order_item" (
  "id" interger PRIMARY KEY,
  "product_id" interger,
  "order_id" interger
);

CREATE TABLE "sales" (
  "id" interger PRIMARY KEY,
  "start_date" timestamp,
  "end_date" timestamp
);

CREATE TABLE "coupons" (
  "id" interger PRIMARY KEY,
  "code" varchar,
  "discount_amount" interger,
  "usage_limit" interger,
  "used_count" interger,
  "start_date" timestamp,
  "end_date" timestamp,
  "status" coupon_status
);

CREATE TABLE "order_coupon" (
  "id" interger PRIMARY KEY,
  "coupon_id" interger,
  "order_id" interger
);

CREATE TABLE "carts" (
  "id" interger PRIMARY KEY,
  "user_id" interger
);

CREATE TABLE "cart_items" (
  "id" interger PRIMARY KEY,
  "cart_id" interger,
  "product_id" interger
);

CREATE TABLE "wishlist" (
  "id" interger PRIMARY KEY,
  "user_id" interger
);

CREATE TABLE "wishlist_item" (
  "id" interger PRIMARY KEY,
  "product_id" interger,
  "wishlist_id" interger
);

ALTER TABLE "games" ADD FOREIGN KEY ("publisher_id") REFERENCES "publishers" ("id");

ALTER TABLE "game_description" ADD FOREIGN KEY ("id") REFERENCES "games" ("description_id");

ALTER TABLE "game_play_mode" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "game_play_mode" ADD FOREIGN KEY ("play_mode_id") REFERENCES "play_mode" ("id");

ALTER TABLE "games" ADD FOREIGN KEY ("no_player_id") REFERENCES "no_player" ("id");

ALTER TABLE "game_genre" ADD FOREIGN KEY ("game_id") REFERENCES "genre" ("id");

ALTER TABLE "game_genre" ADD FOREIGN KEY ("genre_id") REFERENCES "games" ("id");

ALTER TABLE "games" ADD FOREIGN KEY ("developer_id") REFERENCES "developer" ("id");

ALTER TABLE "game_support_language" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "game_support_language" ADD FOREIGN KEY ("support_language_id") REFERENCES "support_language" ("id");

ALTER TABLE "games" ADD FOREIGN KEY ("id") REFERENCES "games" ("game_dlc_id");

ALTER TABLE "orders" ADD FOREIGN KEY ("id") REFERENCES "order_item" ("order_id");

ALTER TABLE "games" ADD FOREIGN KEY ("id") REFERENCES "order_item" ("product_id");

ALTER TABLE "coupons" ADD FOREIGN KEY ("id") REFERENCES "order_coupon" ("coupon_id");

ALTER TABLE "orders" ADD FOREIGN KEY ("id") REFERENCES "order_coupon" ("order_id");

ALTER TABLE "users" ADD FOREIGN KEY ("id") REFERENCES "carts" ("user_id");

ALTER TABLE "publisher_sale" ADD FOREIGN KEY ("publisher_id") REFERENCES "publishers" ("id");

ALTER TABLE "sales" ADD FOREIGN KEY ("id") REFERENCES "publisher_sale" ("sale_id");

ALTER TABLE "games" ADD FOREIGN KEY ("id") REFERENCES "medias" ("product_id");

ALTER TABLE "users" ADD FOREIGN KEY ("id") REFERENCES "orders" ("user_id");

ALTER TABLE "carts" ADD FOREIGN KEY ("id") REFERENCES "cart_items" ("cart_id");

ALTER TABLE "games" ADD FOREIGN KEY ("id") REFERENCES "cart_items" ("product_id");

ALTER TABLE "users" ADD FOREIGN KEY ("id") REFERENCES "wishlist" ("user_id");

ALTER TABLE "wishlist" ADD FOREIGN KEY ("id") REFERENCES "wishlist_item" ("wishlist_id");

ALTER TABLE "games" ADD FOREIGN KEY ("id") REFERENCES "wishlist_item" ("product_id");
