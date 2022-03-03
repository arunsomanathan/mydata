create table if not exists stock_buy_transaction(
    id serial primary key,
    stock_id integer references stock(id),
    price numeric(12,2) not null,
    quantity integer not null unique,
    charge numeric(12,2) not null,
    buy_date timestamp not null default current_timestamp,
    sold_quantity integer not null unique,
    is_sold_out boolean not null default false,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp
);