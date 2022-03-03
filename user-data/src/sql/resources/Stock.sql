create table if not exists stock(
    id serial primary key,
    stock_code text not null unique,
    stock_name text not null unique,
    stock_exchange text not null,
    broker text not null,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp,
    active boolean not null default true
);