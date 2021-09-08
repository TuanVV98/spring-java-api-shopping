
create table orders
(
    id          int         not null auto_increment,
    user_id     int         not null,
    sub_total   float       not null,
    status      varchar(20)  not null,
    full_name   varchar(50) not null,
    mobile      varchar(10) not null,
    address     varchar(100)not null,
    created_at  timestamp   default current_timestamp,
    updated_at  timestamp,
    primary key(id),
    foreign key(user_id) references users(id)
)
;

create table order_details
(
    id          int         not null auto_increment,
    order_id     int         not null,
    product_id  int         not null,
    price       float       not null,
    quantity    int         not null,
    created_at  timestamp   default current_timestamp,
    updated_at  timestamp,
    primary key(id),
    foreign key(order_id) references orders(id),
    foreign key(product_id) references products(id)
)

