
-- USERS
drop sequence if exists user_seq;
create sequence user_seq start with 1 increment by 1;

drop table if exists users cascade;
create table users
(
    id        int8    not null,
    archive   boolean not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255),
    bucket_id int8,
    primary key (id)
);

-- BUCKET
drop sequence if exists bucket_seq;
create sequence bucket_seq start with 1 increment by 1;

drop table if exists buckets cascade;
create table buckets
(
    id bigint not null,
    user_id int8,
    primary key (id)
);

--BETWEEN BUCKET AND USER
-- alter table if exists buckets
--     drop constraint if exists buckets_fk_users;
alter table if exists buckets
    add constraint buckets_fk_users
        foreign key (user_id) references users;

-- alter table if exists users
--     drop constraint if exists users_fk_buckets;
alter table if exists users
    add constraint users_fk_buckets
        foreign key (bucket_id) references buckets;

--CATEGORY
drop sequence if exists category_seq;
create sequence category_seq start with 1 increment by 1;

drop table if exists categories cascade;
create table categories
(
    id    int8    not null,
    title varchar(255),
    primary key (id)
);

--PRODUCT
drop sequence if exists product_seq;
create sequence product_seq start with 1 increment by 1;

drop table if exists products cascade;
create table products
(
    id    int8  not null,
    price numeric(38,2),
    title varchar(255),
    primary key (id)
);

--PRODUCT IN CATEGORY
drop table if exists products_categories cascade;
create table products_categories
(
    product_id int8 not null,
    category_id int8 not null
);
-- alter table if exists products_categories
--     drop constraint if exists products_categories_fk_category;
alter table if exists products_categories
    add constraint products_categories_fk_category
        foreign key (category_id) references categories;

-- alter table if exists products_categories
--     drop constraint if exists products_categories_fk_product;
alter table if exists products_categories
    add constraint products_categories_fk_product
        foreign key (product_id) references products;

--PRODUCT IN BUCKET
drop table if exists buckets_products cascade;
create table buckets_products
(
    bucket_id bigint not null,
    product_id bigint not null
);

-- alter table if exists buckets_products
--     drop constraint if exists buckets_products_fk_product;
alter table if exists buckets_products
    add constraint buckets_products_fk_product
        foreign key (product_id) references products;

-- alter table if exists buckets_products
--     drop constraint if exists buckets_products_fk_bucket;
alter table if exists buckets_products
    add constraint buckets_products_fk_bucket
        foreign key (bucket_id) references buckets;

--ORDERS
drop sequence if exists order_seq;
create sequence order_seq start with 1 increment by 1;

drop table if exists orders cascade;
create table orders
(
    id      int8   not null,
    address varchar(255),
    created timestamp(6),
    status  varchar(255),
    sum     numeric(38,2),
    updated timestamp(6),
    user_id int8,
    primary key (id)
);

-- alter table if exists orders
--     drop constraint if exists orders_fk_user;
alter table if exists orders
    add constraint orders_fk_user
        foreign key (user_id) references users;

--ORDER DETAILS
drop sequence if exists order_details_seq;
create sequence order_details_seq start with 1 increment by 1;

drop table if exists orders_details cascade;
create table orders_details
(
    id         int8    not null,
    amount     numeric(38,2),
    price      numeric(38,2),
    order_id   int8,
    product_id int8,
    details_id int8    not null,
    primary key (id)
);

-- alter table if exists orders_details
--     drop constraint if exists orders_details_fk_order;
alter table if exists orders_details
    add constraint orders_details_fk_order
        foreign key (order_id) references orders;

-- alter table if exists orders_details
--     drop constraint if exists orders_details_fk_product;
alter table if exists orders_details
    add constraint orders_details_fk_product
        foreign key (product_id) references products;

-- alter table if exists orders_details
--     drop constraint if exists orders_details_fk_details;
alter table if exists orders_details
    add constraint orders_details_fk_details
        foreign key (details_id) references orders_details;

-- alter table if exists orders_details
--     add constraint fk_details
--         unique (details_id)