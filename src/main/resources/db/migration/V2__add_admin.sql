insert into users (id, archive, email, name, password, role, bucket_id)
values (1, false, 'email', 'admin', 'pass', 'ADMIN', null);

alter sequence user_seq restart with 2;
