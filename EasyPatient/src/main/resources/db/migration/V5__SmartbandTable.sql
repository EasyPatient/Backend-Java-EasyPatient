CREATE TABLE "smartband"(
"mac" varchar UNIQUE NOT NULL,
"name" varchar NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestamp,
"id" uuid PRIMARY KEY DEFAULT (uuid_generate_v4())
);