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

CREATE TABLE "games" (
  "id" integer PRIMARY KEY,
  "is_dlc" boolean NOT NULL DEFAULT false,
  "description_id" integer,
  "title" varchar NOT NULL,
  "price" double NOT NULL,
  "stock" integer NOT NULL DEFAULT 0,
  "is_deleted" bool,
  "platform_id" interger NOT NULL,
  "sale" double NOT NULL DEFAULT 0
);

CREATE TABLE "medias" (
  "id" integer PRIMARY KEY,
  "game_id" integer NOT NULL,
  "media_type" media_type NOT NULL,
  "url" varchar NOT NULL,
  "is_primary" bool NOT NULL DEFAULT false
);

CREATE TABLE "game_description" (
  "id" integer PRIMARY KEY,
  "descrition" varchar NOT NULL
);

CREATE TABLE "platform_sale" (
  "id" integer PRIMARY KEY,
  "sale_id" integer NOT NULL,
  "platform_id" integer NOT NULL
);

CREATE TABLE "users" (
  "id" integer PRIMARY KEY,
  "email" varchar NOT NULL,
  "password" varchar NOT NULL,
  "name" varchar,
  "address" varchar,
  "phone" varchar
);

CREATE TABLE "guests" (
  "id" integer PRIMARY KEY,
  "session_id" varchar NOT NULL
);

CREATE TABLE "orders" (
  "id" integer PRIMARY KEY,
  "payment_method_id" integer NOT NULL,
  "order_status" order_status NOT NULL,
  "payment_status" payment_status NOT NULL,
  "original_amount" double,
  "discount_amount" double,
  "total_amount" double NOT NULL,
  "user_id" integer NOT NULL
);

CREATE TABLE "order_item" (
  "id" integer PRIMARY KEY,
  "game_id" integer NOT NULL,
  "order_id" integer NOT NULL
);

CREATE TABLE "sales" (
  "id" integer PRIMARY KEY,
  "start_date" timestamp,
  "end_date" timestamp,
  "amount" double NOT NULL,
  "status" sale_status NOT NULL
);

CREATE TABLE "coupons" (
  "id" integer PRIMARY KEY,
  "type_id" integer NOT NULL,
  "code" varchar NOT NULL,
  "discount_amount" integer,
  "usage_limit" integer,
  "used_count" integer,
  "start_date" timestamp,
  "end_date" timestamp,
  "status" coupon_status NOT NULL
);

CREATE TABLE "coupon_types" (
  "id" integer PRIMARY KEY,
  "type" varchar NOT NULL
);

CREATE TABLE "order_coupon" (
  "id" integer PRIMARY KEY,
  "coupon_id" integer NOT NULL,
  "order_id" integer NOT NULL
);

CREATE TABLE "carts" (
  "user_id" integer,
  "id" integer PRIMARY KEY,
  "status" cart_status NOT NULL,
  "session_id" integer
);

CREATE TABLE "cart_items" (
  "id" integer PRIMARY KEY,
  "game_id" integer NOT NULL,
  "cart_id" integer NOT NULL
);

CREATE TABLE "wishlist_item" (
  "id" integer PRIMARY KEY,
  "game_id" integer NOT NULL,
  "user_id" integer NOT NULL
);

CREATE TABLE "reviews" (
  "id" integer PRIMARY KEY,
  "game_id" integer NOT NULL,
  "user_id" integer NOT NULL,
  "rating" integer NOT NULL DEFAULT 5,
  "recommend" boolean NOT NULL DEFAULT true,
  "comment" varchar NOT NULL
);

CREATE TABLE "payment_method" (
  "id" integer PRIMARY KEY,
  "title" varchar NOT NULL
);

CREATE TABLE "platforms" (
  "id" integer PRIMARY KEY,
  "title" varchar NOT NULL,
  "buy_guide" varchar,
  "activate_guide" varchar
);

CREATE TABLE "admins" (
  "id" integer PRIMARY KEY,
  "role_id" integer,
  "fullname" varchar,
  "email" varchar NOT NULL,
  "password" varchar NOT NULL
);

CREATE TABLE "roles" (
  "id" integer PRIMARY KEY,
  "title" varchar NOT NULL,
  "descriptions" varchar
);

CREATE TABLE "game_key" (
  "id" integer PRIMARY KEY,
  "game_id" integer NOT NULL,
  "key" varchar NOT NULL,
  "sold" boolean NOT NULL DEFAULT false
);

CREATE UNIQUE INDEX ON "platform_sale" ("sale_id", "platform_id");

CREATE UNIQUE INDEX ON "order_item" ("game_id", "order_id");

CREATE UNIQUE INDEX ON "order_coupon" ("coupon_id", "order_id");

CREATE UNIQUE INDEX ON "cart_items" ("cart_id", "game_id");

CREATE UNIQUE INDEX ON "wishlist_item" ("user_id", "game_id");

CREATE UNIQUE INDEX ON "reviews" ("game_id", "user_id");

ALTER TABLE "game_key" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "admins" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");

ALTER TABLE "game_description" ADD FOREIGN KEY ("id") REFERENCES "games" ("description_id");

ALTER TABLE "order_item" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "order_item" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "order_coupon" ADD FOREIGN KEY ("coupon_id") REFERENCES "coupons" ("id");

ALTER TABLE "order_coupon" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("id") REFERENCES "carts" ("user_id");

ALTER TABLE "platform_sale" ADD FOREIGN KEY ("platform_id") REFERENCES "platforms" ("id");

ALTER TABLE "platform_sale" ADD FOREIGN KEY ("sale_id") REFERENCES "sales" ("id");

ALTER TABLE "medias" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "cart_items" ADD FOREIGN KEY ("cart_id") REFERENCES "carts" ("id");

ALTER TABLE "cart_items" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "wishlist_item" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "coupon_types" ADD FOREIGN KEY ("id") REFERENCES "coupons" ("type_id");

ALTER TABLE "wishlist_item" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "reviews" ADD FOREIGN KEY ("game_id") REFERENCES "games" ("id");

ALTER TABLE "reviews" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "guests" ADD FOREIGN KEY ("session_id") REFERENCES "carts" ("session_id");

ALTER TABLE "orders" ADD FOREIGN KEY ("payment_method_id") REFERENCES "payment_method" ("id");

ALTER TABLE "games" ADD FOREIGN KEY ("platform_id") REFERENCES "platforms" ("id");
