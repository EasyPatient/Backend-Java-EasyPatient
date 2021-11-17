CREATE TABLE "smartband" IF NOT EXISTS (
"id" uuid PRIMARYKEY DEFAULT (uuid_generate_v4()),
"mac" varchar UNIQUE NOT NULL,
"name" varchar NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);