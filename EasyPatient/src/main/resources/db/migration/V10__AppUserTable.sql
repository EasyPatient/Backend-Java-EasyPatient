CREATE TABLE "app_user" (
    "username" varchar NOT NULL,
    "password" varchar NOT NULL,
    "first_name" varchar NOT NULL,
    "last_name" varchar NOT NULL,
    "born_date" timestamp,
    "app_user_role" varchar NOT NULL,
    "email" varchar NOT NULL,
    "phone_number" varchar,
    "phone_area_code" varchar,
    "staff_id" uuid,
    "locked" boolean,
    "enabled" boolean,
    "updated_at" timestamp,
    "created_at" timestamp,
    "id" uuid PRIMARY KEY DEFAULT (uuid_generate_v4())
);
ALTER TABLE "app_user" ADD FOREIGN KEY ("staff_id") REFERENCES "staff" ("id");
