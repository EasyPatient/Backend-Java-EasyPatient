CREATE TABLE "medicaments"(
"name" varchar,
"type" varchar,
"value" varchar,
"created_at" timestamp NOT NULL,
"updated_at" timestamp,
"id" uuid PRIMARY KEY DEFAULT (uuid_generate_v4())
);