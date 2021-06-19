create table users
(
	id int not null auto_increment ,
    username varchar(50) not null,
    email varchar(100) not null unique,
    password varchar(100) not null,
    address varchar(255) not null,
    role varchar(10) default ('ROLE_USER'),
    created_at timestamp default current_timestamp,
    deleted_at timestamp,
    deleted_user int,
    primary key(id)
)