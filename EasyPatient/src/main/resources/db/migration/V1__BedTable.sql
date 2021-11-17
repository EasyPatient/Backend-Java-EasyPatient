CREATE TABLE "bed" IF NOT EXISTS (
"id" uuid PRIMARYKEY DEFAULT (uuid_generate_v4()),
"number" int UNIQUE NOT NULL,
"patient_id" uuid FOREIGN KEY REFERENCES "patient" ("id"),
"room_id" uuid FOREIGN KEY REFERENCES REFERENCES "room" ("id"),
"created_at" timestamp NOT NULL,
"updated_at" timestampl NOT NULL
);