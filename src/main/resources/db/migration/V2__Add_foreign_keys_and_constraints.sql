-- Add unique constraints
ALTER TABLE IF EXISTS access_tokens ADD CONSTRAINT UK_access_tokens_token UNIQUE (token); -- Ensure tokens in access_tokens table are unique
ALTER TABLE IF EXISTS confirmation_tokens ADD CONSTRAINT UK_confirmation_tokens_token UNIQUE (token); -- Ensure tokens in confirmation_tokens table are unique
ALTER TABLE IF EXISTS refresh_tokens ADD CONSTRAINT UK_refresh_tokens_token UNIQUE (token); -- Ensure tokens in refresh_tokens table are unique
ALTER TABLE IF EXISTS roles ADD CONSTRAINT UK_roles_name UNIQUE (name); -- Ensure role names in roles table are unique
ALTER TABLE IF EXISTS users ADD CONSTRAINT UK_users_email UNIQUE (email); -- Ensure emails in users table are unique
ALTER TABLE IF EXISTS users ADD CONSTRAINT UK_users_phone_number UNIQUE (phone_number); -- Ensure phone numbers in users table are unique
ALTER TABLE IF EXISTS specialities ADD CONSTRAINT UK_specialities_speciality UNIQUE (speciality_name); -- Ensure speciality name in specialities table are unique

-- Add foreign keys
ALTER TABLE IF EXISTS users ADD CONSTRAINT FK_users_role_id FOREIGN KEY (role_id) REFERENCES roles(id); -- Define foreign key relationship for users to roles

ALTER TABLE IF EXISTS doctors ADD CONSTRAINT FK_doctors_hospital_department_id FOREIGN KEY (hospital_department_id) REFERENCES hospital_departments(id); -- Define foreign key relationship for doctors to hospital_departments
ALTER TABLE IF EXISTS doctors ADD CONSTRAINT FK_doctors_user_id FOREIGN KEY (id) REFERENCES users(id); -- Define foreign key relationship for doctors to users
ALTER TABLE IF EXISTS doctors ADD CONSTRAINT FK_doctors_speciality_id FOREIGN KEY (speciality_id) REFERENCES specialities(id); -- Define foreign key relationship for doctors to specialities

ALTER TABLE IF EXISTS admins ADD CONSTRAINT FK_admins_id FOREIGN KEY (id) REFERENCES users(id); -- Define foreign key relationship for admins
ALTER TABLE IF EXISTS hospital_owners ADD CONSTRAINT FK_hospital_owners_hospital_id FOREIGN KEY (hospital_id) REFERENCES hospitals(id); -- Define foreign key relationship for hospital_owners to hospitals
ALTER TABLE IF EXISTS hospital_owners ADD CONSTRAINT FK_hospital_owners_id FOREIGN KEY (id) REFERENCES users(id); -- Define foreign key relationship for hospital_owners to users
ALTER TABLE IF EXISTS general_managers ADD CONSTRAINT FK_general_managers_id FOREIGN KEY (id) REFERENCES users(id); -- Define foreign key relationship for general_managers to users

ALTER TABLE IF EXISTS access_tokens ADD CONSTRAINT FK_access_tokens_user_id FOREIGN KEY (user_id) REFERENCES users(id); -- Define foreign key relationship for access_tokens
ALTER TABLE IF EXISTS confirmation_tokens ADD CONSTRAINT FK_confirmation_tokens_user_id FOREIGN KEY (user_id) REFERENCES users(id); -- Define foreign key relationship for confirmation_tokens
ALTER TABLE IF EXISTS refresh_tokens ADD CONSTRAINT FK_refresh_tokens_user_id FOREIGN KEY (user_id) REFERENCES users(id); -- Define foreign key relationship for refresh_tokens to users

ALTER TABLE IF EXISTS doctor_schedules ADD CONSTRAINT FK_doctor_schedules_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(id); -- Define foreign key relationship for doctor_schedules to doctors
ALTER TABLE IF EXISTS doctor_schedules ADD CONSTRAINT FK_doctor_schedules_schedule_id FOREIGN KEY (schedule_id) REFERENCES schedules(id); -- Define foreign key relationship for doctor_schedules to schedules

ALTER TABLE IF EXISTS hospital_departments ADD CONSTRAINT FK_hospital_departments_department_id FOREIGN KEY (department_id) REFERENCES departments(id); -- Define foreign key relationship for hospital_departments to departments
ALTER TABLE IF EXISTS hospital_departments ADD CONSTRAINT FK_hospital_departments_department_manager_id FOREIGN KEY (department_manager_id) REFERENCES doctors(id); -- Define foreign key relationship for hospital_departments to doctors (as department managers)
ALTER TABLE IF EXISTS hospital_departments ADD CONSTRAINT FK_hospital_departments_hospital_id FOREIGN KEY (hospital_id) REFERENCES hospitals(id); -- Define foreign key relationship for hospital_departments to hospitals

ALTER TABLE IF EXISTS hospitals ADD CONSTRAINT FK_hospitals_general_manager_id FOREIGN KEY (general_manager_id) REFERENCES general_managers(id); -- Define foreign key relationship for hospitals to general_managers
ALTER TABLE IF EXISTS schedules ADD CONSTRAINT FK_schedules_hospital_department_id FOREIGN KEY (hospital_department_id) REFERENCES hospital_departments(id); -- Define foreign key relationship for schedules to hospital_departments
