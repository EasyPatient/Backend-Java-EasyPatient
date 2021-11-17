CREATE TABLE "staff_patient" IF NOT EXISTS (
"patient_id" uuid FOREIGN KEY REFERENCES "patient" ("id"),
"staff_id" uuid FOREIGN KEY REFERENCES "staff" ("id"),
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);
