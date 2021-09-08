create table categories
(
    id         int          not null auto_increment,
    name       varchar(100) not null,
    context    varchar(510) not null,
    created_at timestamp    default current_timestamp,
    updated_at timestamp,
    deleted_at timestamp,
    primary key (id)
)
;

create table products
(
    id          int           not null auto_increment,
    model       varchar (100) not null,
    name        varchar (225) not null,
    image       varchar (225) not null,
    price       float         not null,
    description varchar (600) not null,
    user_id     int           not null,
    category_id int           not null,
    created_at  timestamp     default current_timestamp,
    updated_at  timestamp,
    deleted_at  timestamp,
    primary key (id),
    foreign key(category_id) references categories(id),
    foreign key(user_id) references users(id)
)
