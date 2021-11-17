CREATE TABLE "patient" IF NOT EXISTS (
"id" uuid PRIMARYKEY DEFAULT (uuid_generate_v4()),
"name" varchar NOT NULL,
"age" int,
"bed_id" uuid,
"arrived_at" timestamp NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestampl
);