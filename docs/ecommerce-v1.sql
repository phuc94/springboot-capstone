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

CREATE TYPE "cart_status" AS ENUM (
  'ACTIVE',
  'CHECKED_OUT',
  'EXPIRED',
  'ABANDONED'
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
  "developer_id" interger,
  "description_id" interger,
  "publisher_id" interger,
  "no_player_id" interger
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
  "genre_id" interger NOT NULL,
  "game_id" interger NOT NULL
);

CREATE TABLE "no_player" (
  "id" interger PRIMARY KEY,
  "number" varchar NOT NULL
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
  "game_id" interger NOT NULL,
  "play_mode_id" interger NOT NULL
);

CREATE TABLE "users" (
  "id" interger PRIMARY KEY,
  "email" varchar NOT NULL,
  "password" varchar NOT NULL,
  "name" varchar,
  "address" varchar
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
  "amount" double NOT NULL
);

CREATE TABLE "coupons" (
  "id" interger PRIMARY KEY,
  "type_id" integer NOT NULL,
  "code" varchar NOT NULL,
  "discount_amount" interger,
  "usage_limit" interger,
  "used_count" interger,
  "start_date" timestamp,
  "end_date" timestamp,
  "status" coupon_status
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
  "status" cart_status
);

CREATE TABLE "cart_items" (
  "id" interger PRIMARY KEY,
  "cart_id" interger NOT NULL,
  "product_id" interger NOT NULL
);

CREATE TABLE "wishlist" (
  "id" interger PRIMARY KEY,
  "user_id" interger NOT NULL
);

CREATE TABLE "wishlist_item" (
  "id" interger PRIMARY KEY,
  "product_id" interger NOT NULL,
  "wishlist_id" interger NOT NULL
);

CREATE UNIQUE INDEX ON "game_support_language" ("support_language_id", "game_id");

CREATE UNIQUE INDEX ON "game_genre" ("genre_id", "game_id");

CREATE UNIQUE INDEX ON "publisher_sale" ("sale_id", "publisher_id");

CREATE UNIQUE INDEX ON "game_play_mode" ("game_id", "play_mode_id");

CREATE UNIQUE INDEX ON "order_item" ("product_id", "order_id");

CREATE UNIQUE INDEX ON "order_coupon" ("coupon_id", "order_id");

CREATE UNIQUE INDEX ON "cart_items" ("cart_id", "product_id");

CREATE UNIQUE INDEX ON "wishlist_item" ("wishlist_id", "product_id");

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

ALTER TABLE "coupon_types" ADD FOREIGN KEY ("id") REFERENCES "coupons" ("type_id");
