INSERT INTO roles (id,name)
VALUES (1,'ROLE_ADMIN'),
       (2,'ROLE_GENERAL_MANAGER'),
       (3,'ROLE_HOSPITAL_OWNER'),
       (4,'ROLE_DEPARTMENT_MANAGER'),
       (5,'ROLE_DOCTOR');

INSERT INTO users (id, first_name, last_name, phone_number, address, email, password, is_enabled, created_at, updated_at, role_id, user_type)
VALUES (uuid_generate_v4(), 'admin', 'admin', '654231987', '123 Main St', 'admin@example.com', '$2a$10$b0EGEuCUgui1wqmQ6s8gxeG/2Z3i1X0/CDLCJLGC/k8kNEMqidIwy', true, NOW(), NOW(), '1', 'ADMIN');

INSERT INTO users (id, first_name, last_name, phone_number, address, email, password, is_enabled, created_at, updated_at, role_id, user_type)
VALUES (uuid_generate_v4(), 'hospital', 'owner', '65423165465', '456 Another St', 'hospitalowner@example.com', '$2a$10$b0EGEuCUgui1wqmQ6s8gxeG/2Z3i1X0/CDLCJLGC/k8kNEMqidIwy', true, NOW(), NOW(), '3', 'HOSPITAL_OWNER');

INSERT INTO users (id, first_name, last_name, phone_number, address, email, password, is_enabled, created_at, updated_at, role_id, user_type)
VALUES (uuid_generate_v4(), 'general', 'manager', '1234567890', '123 Main St', 'generalmanager@example.com', '$2a$10$b0EGEuCUgui1wqmQ6s8gxeG/2Z3i1X0/CDLCJLGC/k8kNEMqidIwy', true, NOW(), NOW(), '2', 'GENERAL_MANAGER');

INSERT INTO users (id, first_name, last_name, phone_number, address, email, password, is_enabled, created_at, updated_at, role_id, user_type)
VALUES (uuid_generate_v4(), 'doctor', 'doctor', '0987654321', '456 Another St', 'doctor@example.com', '$2a$10$b0EGEuCUgui1wqmQ6s8gxeG/2Z3i1X0/CDLCJLGC/k8kNEMqidIwy', true, NOW(), NOW(), '5', 'DOCTOR');