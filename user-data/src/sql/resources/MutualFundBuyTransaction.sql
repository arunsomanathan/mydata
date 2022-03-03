create table if not exists mutualfund_buy_transaction(
    id serial primary key,
    mf_id integer references mutual_fund(id),
    nav numeric(12,2) not null,
    units integer not null unique,
    charge numeric(12,2) not null,
    buy_date timestamp not null default current_timestamp,
    sold_units integer not null unique,
    is_sold_out boolean not null default false,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp
);