create table orders
(
	id int not null auto_increment,
    address varchar(255) not null,
    user_id int not null,
    created_at timestamp default current_timestamp,
    deleted_at timestamp,
    deleted_user int,
    primary key(id),
    foreign key(user_id) references users(id)
)
;
create table order_details
(
	id int not null auto_increment,
    price float not null,
    quanlity int not null,
    order_id int not null,
    product_id int not null,
    created_at timestamp default current_timestamp,
    deleted_at timestamp,
    deleted_user int,
    primary key(id),
    foreign key(order_id) references orders(id),
    foreign key(product_id) references products(id)
)