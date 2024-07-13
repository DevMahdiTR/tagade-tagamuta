create table users
(
    user_type    varchar(31)  not null,
    id           uuid         not null,
    address      varchar(255) not null,
    email        varchar(255) not null,
    first_name   varchar(255) not null,
    is_enabled   boolean      not null,
    last_name    varchar(255) not null,
    password     varchar(255) not null,
    phone_number varchar(255) not null,
    role_id      bigint,
    primary key (id)
);

create table admins
(
    id uuid not null,
    primary key (id)
);

create table general_managers
(
    id uuid not null,
    primary key (id)
);

create table hospital_owners
(
    id          uuid not null,
    hospital_id bigint,
    primary key (id)
);


create table doctors
(
    code_cnam              varchar(255),
    code_cnom              varchar(255),
    date_of_birth          date,
    id                     uuid not null,
    hospital_department_id bigint,
    speciality_id          bigint,
    primary key (id)
);

create table specialities
(
    id   bigint       not null,
    speciality_name varchar(255) not null,
    primary key (id)
);

create table hospitals
(
    id                 bigint not null,
    address            varchar(255),
    email              varchar(255),
    name               varchar(255),
    phone              varchar(255),
    general_manager_id uuid,
    primary key (id)
);

create table departments
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);


create table hospital_departments
(
    id                    bigint not null,
    department_id         bigint,
    department_manager_id uuid,
    hospital_id           bigint,
    primary key (id)
);

create table doctor_schedules
(
    id          bigint not null,
    work_day    date   not null,
    doctor_id   uuid,
    schedule_id bigint,
    primary key (id)
);


create table schedules
(
    id                     bigint not null,
    month                  date,
    hospital_department_id bigint,
    primary key (id)
);



create table refresh_tokens
(
    id      bigserial    not null,
    expired boolean      not null,
    revoked boolean      not null,
    token   varchar(255) not null,
    user_id uuid,
    primary key (id)
);

create table confirmation_tokens
(
    id      bigserial    not null,
    expired boolean      not null,
    revoked boolean      not null,
    token   varchar(255) not null,
    user_id uuid,
    primary key (id)
);

create table access_tokens
(
    id      bigserial    not null,
    expired boolean      not null,
    revoked boolean      not null,
    token   varchar(255) not null,
    user_id uuid,
    primary key (id)
);


create table roles
(
    id   bigint       not null,
    name varchar(255) not null,
    primary key (id)
);



-- Create sequences
create sequence department_sequence start with 1 increment by 1;
create sequence doctor_schedule_sequence start with 1 increment by 1;
create sequence hospital_department_sequence start with 1 increment by 1;
create sequence hospital_sequence start with 1 increment by 1;
create sequence role_sequence start with 1 increment by 1;
create sequence schedule_sequence start with 1 increment by 1;
create sequence specialty_sequence start with 1 increment by 1;

-- Create index

CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_users_role_id ON users (role_id);

CREATE INDEX idx_access_tokens_token ON access_tokens (token);
CREATE INDEX idx_access_tokens_user_id ON access_tokens (user_id);

CREATE INDEX idx_confirmation_tokens_token ON confirmation_tokens (token);
CREATE INDEX idx_confirmation_tokens_user_id ON confirmation_tokens (user_id);

CREATE INDEX idx_refresh_tokens_token ON refresh_tokens (token);
CREATE INDEX idx_refresh_tokens_user_id ON refresh_tokens (user_id);

CREATE INDEX idx_hospital_departments_hospital_id ON hospital_departments (hospital_id);
CREATE INDEX idx_hospital_departments_department_id ON hospital_departments (department_id);

CREATE INDEX idx_doctors_hospital_department_id ON doctors (hospital_department_id);


CREATE INDEX idx_schedules_hospital_department_id ON schedules (hospital_department_id);
