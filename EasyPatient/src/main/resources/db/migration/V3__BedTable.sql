CREATE TABLE "bed"(
"number" int UNIQUE NOT NULL,
"room_id" uuid,
"created_at" timestamp NOT NULL,
"updated_at" timestamp NOT NULL,
"id" uuid PRIMARY KEY DEFAULT (uuid_generate_v4())
);

ALTER TABLE "bed" ADD FOREIGN KEY ("room_id") REFERENCES "room" ("id");

