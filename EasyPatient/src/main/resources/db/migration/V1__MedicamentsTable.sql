CREATE TABLE "medicaments" IF NOT EXISTS (
"id" uuid PRIMARYKEY DEFAULT (uuid_generate_v4()),
"name" varchar,
"type" varchar,
"value" varchar,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);