create table if not exists clients (
    id serial primary key,
    name varchar(128) not null,
    auto varchar(128) not null
);

create table if not exists parking_places (
    place_id serial primary key,
    square integer not null
);

create table if not exists orders (
    order_id serial primary key,
    price integer not null,
    start_time timestamp not null,
    end_time timestamp not null,
    client_id integer references clients(id),
    place_id integer references parking_places(place_id)
);