CREATE TABLE "staff" IF NOT EXISTS (
"id" uuid PRIMARYKEY DEFAULT (uuid_generate_v4()),
"name" varchar NOT NULL,
"email" varchar UNIQUE NOT NULL,
"phone" varchar UNIQUE NOT NULL,
"phone_area_code" varchar NOT NULL,
"password" varchar NOT NULL,
"role" varchar NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);