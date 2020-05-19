-- auto-generated definition
create table user_entity
(
    id       bigserial    not null
        constraint user_entity_pkey
            primary key,
    email    varchar(255),
    nickname varchar(255),
    password varchar(255),
    role     varchar(255),
    username varchar(255) not null
        constraint uk_2jsk4eakd0rmvybo409wgwxuw
            unique
);

