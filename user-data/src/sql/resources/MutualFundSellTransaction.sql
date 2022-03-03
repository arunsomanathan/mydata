create table if not exists mutualfund_sell_transaction(
    id serial primary key,
    mf_id integer references mutual_fund(id),
    buy_ids integer[] not null,
    nav numeric(12,2) not null,
    units integer not null unique,
    charge numeric(12,2) not null,
    sold_date timestamp not null default current_timestamp,
    profit_loss numeric(12,2) not null,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp
);