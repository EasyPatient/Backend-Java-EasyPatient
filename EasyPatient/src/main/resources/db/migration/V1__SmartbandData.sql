CREATE TABLE "smartband_data" IF NOT EXISTS (
"smartband_id" uuid FOREIGN KEY REFERENCES "smartband" ("id"),
"patient_id" uuid FOREIGN KEY REFERENCES "patient" ("id"),,
"heart_rate" varchar,
"oxygen" varchar,
"temperature" varchar,
"battery" varchar,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);