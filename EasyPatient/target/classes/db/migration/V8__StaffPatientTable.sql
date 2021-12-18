CREATE TABLE "staff_patient"(
"patient_id" uuid,
"staff_id" uuid,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);
ALTER TABLE "staff_patient" ADD FOREIGN KEY ("patient_id") REFERENCES "patient" ("id");

ALTER TABLE "staff_patient" ADD FOREIGN KEY ("staff_id") REFERENCES "staff" ("id");