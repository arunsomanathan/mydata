create table if not exists mutual_fund_sell_transaction(
    id serial primary key,
    mf_id integer references mutual_fund(id),
    buy_ids integer[] not null,
    nav numeric(12,2) not null,
    constraint nav_non_negative check (nav >= 0),
    units numeric(12,2) not null,
    constraint units_non_negative check (units >= 0),
    charge numeric(12,2) not null,
    constraint charge_non_negative check (charge >= 0),
    sold_date timestamp not null default current_timestamp,
    profit_loss numeric(12,2) not null default 0,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp
);