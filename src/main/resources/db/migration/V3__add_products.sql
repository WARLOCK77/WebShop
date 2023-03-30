insert into products (id, price, title)
values (10, 450.0, 'Cheese'),
       (12, 35.0, 'Beer'),
       (13, 45.0, 'Milk'),
       (14, 115.0, 'Tomato'),
       (15, 56.0, 'Bread');
alter sequence product_seq restart with 16;