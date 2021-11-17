CREATE TABLE "room" IF NOT EXISTS (
"id" uuid PRIMARYKEY DEFAULT (uuid_generate_v4()),
"number" int UNIQUE NOT NULL,
"name" varchar,
"created_at" timestamp NOT NULL,
"updated_at" timestampl
);