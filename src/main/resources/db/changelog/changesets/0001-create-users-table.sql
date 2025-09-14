--liquibase formatted sql

--changeset iamsane:0001-create-users-table
create sequence users_seq increment 50 start with 1050;

create table users
(
    id         bigint primary key,
    first_name varchar,
    last_name  varchar,
    username   varchar unique,
    password   varchar,
    created_at timestamp
);
