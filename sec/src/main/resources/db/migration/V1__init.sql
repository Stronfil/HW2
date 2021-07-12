create table users
(
    id       bigserial,
    name     varchar(30) not null unique,
    password varchar(80) not null,
    score    integer     not null,
    primary key (id)
);

create table roles
(
    id        bigserial,
    role_name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id bigint    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (role_name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (name, password, score)
values ('user', '$2y$04$4ay0hPB7/G/jRvF8cP50KOTicB41l6DOGb3qtIAIFrpyU6MGpgj.K', '112'),
       ('user_ad', '$2y$04$4ay0hPB7/G/jRvF8cP50KOTicB41l6DOGb3qtIAIFrpyU6MGpgj.K', '53');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (2, 1);