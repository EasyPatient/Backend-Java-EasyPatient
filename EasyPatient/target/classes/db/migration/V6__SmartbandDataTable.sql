CREATE TABLE "smartband_data"(
"smartband_id" uuid,
"patient_id" uuid,
"heart_rate" varchar,
"oxygen" varchar,
"temperature" varchar,
"battery" varchar,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);

ALTER TABLE "smartband_data" ADD FOREIGN KEY ("patient_id") REFERENCES "patient" ("id");

ALTER TABLE "smartband_data" ADD FOREIGN KEY ("smartband_id") REFERENCES "smartband" ("id");