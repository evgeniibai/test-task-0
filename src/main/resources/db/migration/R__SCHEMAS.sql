-- Table: Error storage table.
create table if not exists error_log
(
    error_id     int primary key auto_increment,
    created_date timestamp   not null default CURRENT_TIMESTAMP,
    error_name   varchar(50) not null,
    description  text        not null
);

-- Table: Fruits.
create table if not exists fruits
(
    fruit_id   int primary key auto_increment,
    fruit_name varchar(100) not null,
    unique (fruit_name)
);

-- Table: User Additional Information.
create table if not exists users_details
(
    detail_id    bigint primary key auto_increment,
    first_name   varchar(50),
    last_name    varchar(150),
    email        varchar(250),
    phone_number varchar(15),
    unique (email, phone_number)
);

-- Table: Users.
create table if not exists users
(
    user_id  bigint primary key auto_increment,
    uuid     varchar(36)  not null,
    username varchar(100) not null,
    password varchar(255) not null,
    details  bigint,
    role     varchar(20)  not null default 'USER',
    status   varchar(20)  not null default 'ACTIVE',
    unique (uuid, username),
    foreign key (details) references users_details (detail_id)
);

-- Table for mapping users and fruits.
create table if not exists users_fruits
(
    user_id  bigint,
    fruit_id int,
    primary key (user_id, fruit_id),
    foreign key (user_id) references users (user_id),
    foreign key (fruit_id) references fruits (fruit_id)
);

-- Insert Information.
-- 0000014b-b13d-4f98-ac90-b5b7b5fbcaf9
insert into fruits (fruit_name)
values ('Apple');
insert into fruits (fruit_name)
values ('Banana');
insert into users_details (first_name, last_name, email, phone_number)
values ('Evgenii', 'Bai', 'evgenii.bai@decathlon.com', '9135082020');
-- admin, password: password
insert into users (uuid, username, password, details, role, status)
values ('00000010-d729-4c52-ab29-b4dba065d2a7', 'admin', '{bcrypt}$2a$12$uPXJwU2XYGGR2yQwMn.NPOMIPgPuntsiqUmKbrpnW2feW9lN.uyF2', 1, 'ADMIN', 'ACTIVE');
-- user, password: 12345678
insert into users (uuid, username, password, details, role, status)
values ('000001c0-ac82-464b-bec4-a21fdd459e51', 'user', '{bcrypt}$2a$12$PBatXxHW4Xq0ySypCtv4a.pkvCHWXJw9o.0neEiuWjIgx/p/GY7rS', null, 'USER', 'ACTIVE');
insert into users_fruits
values (1, 1);
insert into users_fruits
values (1, 2);
insert into users_fruits
values (2, 2);