CREATE TABLE "patient_medicaments"(
"patient_id" uuid NOT NULL,
"medicaments_id" uuid NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);
ALTER TABLE "patient_medicaments" ADD FOREIGN KEY ("patient_id") REFERENCES "patient" ("id");

ALTER TABLE "patient_medicaments" ADD FOREIGN KEY ("medicaments_id") REFERENCES "medicaments" ("id");