CREATE TABLE "room"(
"number" int UNIQUE NOT NULL,
"name" varchar,
"created_at" timestamp NOT NULL,
"updated_at" timestamp,
"id" uuid PRIMARY KEY DEFAULT (uuid_generate_v4())
);