create table categories
(
	id int not null auto_increment,
    name varchar(255) not null,
    created_at timestamp default current_timestamp,
    deleted_at timestamp,
    deleted_user int,
    primary key(id)
)
;
create table products
(
	id int not null auto_increment,
	model varchar(255) not null,
    name varchar(255) not null,
    image varchar(255) not null,
    price float not null,
    description varchar(600) not null,
    categori_id int not null,
    created_at timestamp default current_timestamp,
    deleted_at timestamp,
    deleted_user int,
    primary key(id),
    foreign key(categori_id) references categories(id)
)

