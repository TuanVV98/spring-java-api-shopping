
create table users
(
    id            int          not null auto_increment,
    username      varchar(55)  not null,
    mobile        varchar(13)  not null,
    email         varchar(101) not null unique,
    password      varchar(85)  not null,
    status        varchar(20)  not null,
    role          varchar(15)  default ('ROLE_USER'),
    registered_at timestamp    default current_timestamp,
    updated_at    timestamp,
    deleted_at    timestamp,
    primary key (id)

)
;

create table user_profiles
(
    id          int         not null auto_increment,
    user_id     int         not null,
    first_name  varchar(25) not null,
    middle_name varchar(25) not null,
    last_name   varchar(25) not null,
    gender      varchar(10) not null,
    updated_at  timestamp,
    primary key (id),
    foreign key (user_id) references users (id)
)
;

create table verification_token
(
    id          int         not null auto_increment,
    user_id     int         not null,
    token       varchar(75) not null,
    type        varchar(25) not null,
    created_at  timestamp   default current_timestamp,
    expires_at  timestamp   not null,
    verified_at timestamp,
    primary key(id),
    foreign key(user_id) references users(id)
)
;