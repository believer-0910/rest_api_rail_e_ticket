insert into owner(id, active, created_by, created_date, updated_by, updated_date, email, name, password,user_role, username)
VALUES (1,true,null,now(),null,now(),'Email@gmail.com','Bekzod',1,1,'bekdev');

insert into destination(id, created_by, created_date, active, updated_by, updated_date, code, name)
VALUES (1, now(), now(), true, '1', now(), 1, 'Tashkent'),
       (2, now(), now(), true, '1', now(), 2, 'Bukhara');

insert into station(id, active, created_by, created_date, updated_by, updated_date, name, destination_id)
values (1, true, '1', now(), '1', now(), 'Southern', 1),
       (2, true, '1', now(), '1', now(), 'Northern', 1),
       (3, true, '1', now(), '1', now(), 'Northern', 2),
       (4, true, '1', now(), '1', now(), 'Southern', 2);

insert into train(id, active, created_by, created_date, updated_by, updated_date, code, type)
values (1, true, 1, now(), 1, now(), 1, 'Afrosiyob'),
       (2, true, 1, now(), 1, now(), 1, 'Sharq');

insert into car(id, active, created_by, created_date, updated_by, updated_date, code, num_seats, price, type, train_id)
VALUES (1, true, null, now(), null, now(), '123', 20, 20000, 'Kupe',1),
       (2, true, null, now(), null, now(), '1234', 20, 20000, '1-class',2);

-- it may bring troubles like SEQUENCE and generating value of primary key **
-- DataLoader is better than this way