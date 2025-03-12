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
  'PROCESSING',
  'PENDING',
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

CREATE TYPE "coupon_status" AS ENUM (
  'DISABLE',
  'ACTIVE',
  'EXPIRE'
);

CREATE TYPE "sale_status" AS ENUM (
  'DISABLE',
  'ACTIVE',
  'EXPIRE'
);

CREATE TYPE "cart_status" AS ENUM (
  'ACTIVE',
  'CHECKED_OUT',
  'EXPIRED',
  'ABANDONED'
);

CREATE TYPE "game_status" AS ENUM (
  'DRAFT',
  'VISIBLE',
  'HIDDEN',
  'ARCHIVED'
);

CREATE TABLE "games" (
  "id" interger PRIMARY KEY,
  "is_dlc" boolean NOT NULL DEFAULT false,
  "game_dlc_id" interger,
  "title" varchar NOT NULL,
  "price" double,
  "realease_date" timestamp,
  "size" interger,
  "esrb_rating" esrb_rating,
  "is_deleted" bool,
  "publisher_id" interger,
  "status" game_status NOT NULL,
  "description_id" interger,
  "developer_id" interger
);

CREATE TABLE "support_language" (
  "id" interger PRIMARY KEY,
  "language" varchar NOT NULL
);

CREATE TABLE "game_support_language" (
  "id" interger PRIMARY KEY,
  "support_language_id" interger NOT NULL,
  "game_id" interger NOT NULL
);

CREATE TABLE "genre" (
  "id" interger PRIMARY KEY,
  "genre" varchar NOT NULL
);

CREATE TABLE "game_genre" (
  "id" interger PRIMARY KEY,
  "game_id" interger NOT NULL,
  "genre_id" interger NOT NULL
);

CREATE TABLE "no_players" (
  "id" interger PRIMARY KEY,
  "number" varchar NOT NULL
);

CREATE TABLE "game_no_player" (
  "id" interger PRIMARY KEY,
  "no_player_id" interger NOT NULL,
  "game_id" interger NOT NULL
);

CREATE TABLE "medias" (
  "id" interger PRIMARY KEY,
  "product_id" interger NOT NULL,
  "media_type" media_type NOT NULL,
  "url" varchar NOT NULL,
  "is_primary" bool NOT NULL DEFAULT false
);

CREATE TABLE "game_description" (
  "id" interger PRIMARY KEY,
  "descrition" varchar NOT NULL
);

CREATE TABLE "publishers" (
  "id" interger PRIMARY KEY,
  "name" varchar NOT NULL
);

CREATE TABLE "publisher_sale" (
  "id" interger PRIMARY KEY,
  "sale_id" interger NOT NULL,
  "publisher_id" interger NOT NULL
);

CREATE TABLE "developer" (
  "id" interger PRIMARY KEY,
  "name" varchar NOT NULL
);

CREATE TABLE "play_mode" (
  "id" interger PRIMARY KEY,
  "name" varchar NOT NULL
);

CREATE TABLE "game_play_mode" (
  "id" interger PRIMARY KEY,
  "play_mode_id" interger NOT NULL,
  "game_id" interger NOT NULL
);

CREATE TABLE "users" (
  "id" interger PRIMARY KEY,
  "email" varchar NOT NULL,
  "password" varchar NOT NULL,
  "name" varchar,
  "address" varchar
);

CREATE TABLE "guests" (
  "id" interger PRIMARY KEY,
  "session_id" varchar NOT NULL
);

CREATE TABLE "orders" (
  "id" interger PRIMARY KEY,
  "user_id" interger NOT NULL,
  "order_status" order_status NOT NULL,
  "payment_method" payment_method NOT NULL,
  "payment_status" payment_status NOT NULL,
  "original_amount" double,
  "discount_amount" double,
  "total_amount" double NOT NULL
);

CREATE TABLE "order_item" (
  "id" interger PRIMARY KEY,
  "product_id" interger NOT NULL,
  "order_id" interger NOT NULL
);

CREATE TABLE "sales" (
  "id" interger PRIMARY KEY,
  "start_date" timestamp,
  "end_date" timestamp,
  "amount" double NOT NULL,
  "status" sale_status NOT NULL
);

CREATE TABLE "coupons" (
  "id" interger PRIMARY KEY,
  "code" varchar NOT NULL,
  "discount_amount" interger,
  "usage_limit" interger,
  "used_count" interger,
  "start_date" timestamp,
  "end_date" timestamp,
  "status" coupon_status NOT NULL,
  "type_id" integer NOT NULL
);

CREATE TABLE "coupon_types" (
  "id" interger PRIMARY KEY,
  "type" varchar NOT NULL
);

CREATE TABLE "order_coupon" (
  "id" interger PRIMARY KEY,
  "coupon_id" interger NOT NULL,
  "order_id" interger NOT NULL
);

CREATE TABLE "carts" (
  "id" interger PRIMARY KEY,
  "user_id" interger,
  "session_id" interger,
  "status" cart_status NOT NULL
);

CREATE TABLE "cart_items" (
  "id" interger PRIMARY KEY,
  "product_id" interger NOT NULL,
  "cart_id" interger NOT NULL
);

CREATE TABLE "wishlist_item" (
  "id" interger PRIMARY KEY,
  "product_id" interger NOT NULL,
  "user_id" interger NOT NULL
);

CREATE TABLE "reviews" (
  "id" interger PRIMARY KEY,
  "product_id" interger NOT NULL,
  "user_id" interger NOT NULL,
  "rating" interger NOT NULL DEFAULT 5,
  "comment" varchar
);

CREATE UNIQUE INDEX ON "game_support_language" ("support_language_id", "game_id");

CREATE UNIQUE INDEX ON "game_genre" ("genre_id", "game_id");

CREATE UNIQUE INDEX ON "publisher_sale" ("sale_id", "publisher_id");

CREATE UNIQUE INDEX ON "game_play_mode" ("game_id", "play_mode_id");

CREATE UNIQUE INDEX ON "order_item" ("product_id", "order_id");

CREATE UNIQUE INDEX ON "order_coupon" ("coupon_id", "order_id");

CREATE UNIQUE INDEX ON "cart_items" ("cart_id", "product_id");

CREATE UNIQUE INDEX ON "wishlist_item" ("user_id", "product_id");

CREATE UNIQUE INDEX ON "reviews" ("product_id", "user_id");

ALTER TABLE "games" ADD FOREIGN KEY ("publisher_id") REFERENCES "publishers" ("id");

ALTER TABLE "game_description" ADD FOREIGN KEY ("id") REFERENCES "games" ("description_id");

ALTER TABLE "game_play_mode" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "game_play_mode" ADD FOREIGN KEY ("play_mode_id") REFERENCES "play_mode" ("id");

ALTER TABLE "game_no_player" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "game_no_player" ADD FOREIGN KEY ("no_player_id") REFERENCES "no_players" ("id");

ALTER TABLE "game_genre" ADD FOREIGN KEY ("game_id") REFERENCES "genre" ("id");

ALTER TABLE "game_genre" ADD FOREIGN KEY ("genre_id") REFERENCES "games" ("id");

ALTER TABLE "games" ADD FOREIGN KEY ("developer_id") REFERENCES "developer" ("id");

ALTER TABLE "game_support_language" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "game_support_language" ADD FOREIGN KEY ("support_language_id") REFERENCES "support_language" ("id");

ALTER TABLE "games" ADD FOREIGN KEY ("id") REFERENCES "games" ("game_dlc_id");

ALTER TABLE "order_item" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "order_item" ADD FOREIGN KEY ("product_id") REFERENCES "games" ("id");

ALTER TABLE "order_coupon" ADD FOREIGN KEY ("coupon_id") REFERENCES "coupons" ("id");

ALTER TABLE "order_coupon" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("id") REFERENCES "carts" ("user_id");

ALTER TABLE "publisher_sale" ADD FOREIGN KEY ("publisher_id") REFERENCES "publishers" ("id");

ALTER TABLE "publisher_sale" ADD FOREIGN KEY ("sale_id") REFERENCES "sales" ("id");

ALTER TABLE "medias" ADD FOREIGN KEY ("product_id") REFERENCES "games" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "cart_items" ADD FOREIGN KEY ("cart_id") REFERENCES "carts" ("id");

ALTER TABLE "cart_items" ADD FOREIGN KEY ("product_id") REFERENCES "games" ("id");

ALTER TABLE "wishlist_item" ADD FOREIGN KEY ("product_id") REFERENCES "games" ("id");

ALTER TABLE "coupon_types" ADD FOREIGN KEY ("id") REFERENCES "coupons" ("type_id");

ALTER TABLE "wishlist_item" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "reviews" ADD FOREIGN KEY ("product_id") REFERENCES "games" ("id");

ALTER TABLE "reviews" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "guests" ADD FOREIGN KEY ("session_id") REFERENCES "carts" ("session_id");
