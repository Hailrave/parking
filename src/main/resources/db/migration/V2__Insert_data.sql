INSERT INTO clients(name, auto)
values ('Анисимов Владислав Юрьевич', 'LADA'),
       ('Макаренко Глеб Александрович', 'BMW'),
       ('Ратник Светлана Дмитриевна', 'Mini Cooper');

INSERT INTO parking_places(square)
values (23),
       (20),
       (21);

INSERT INTO orders(price, start_time, end_time, client_id, place_id)
values (300, '2021-01-02 05:05:00', '2021-01-02 09:00:00', 1, 1),
       (250, '2021-02-03 10:00:00', '2021-02-05 15:00:00', 2, 3),
       (126, '2021-05-10 15:00:00', '2021-05-13 18:00:00', 3, 2);

