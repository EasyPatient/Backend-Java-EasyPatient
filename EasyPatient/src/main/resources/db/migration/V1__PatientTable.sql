CREATE TABLE "patient"(
"name" varchar NOT NULL,
"age" int,
"bed_id" uuid UNIQUE,
"arrived_at" timestamp NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestamp,
"id" uuid PRIMARY KEY DEFAULT (uuid_generate_v4())
);