create table if not exists stock_sell_transaction(
    id serial primary key,
    stock_id integer references stock(id),
    buy_ids integer[] not null,
    price numeric(12,2) not null,
    quantity integer not null unique,
    charge numeric(12,2) not null,
    sold_date timestamp not null default current_timestamp,
    profit_loss numeric(12,2) not null,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp
);