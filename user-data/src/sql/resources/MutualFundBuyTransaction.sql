create table if not exists mutual_fund_buy_transaction(
    id serial primary key,
    mf_id integer references mutual_fund(id),
    nav numeric(12,2) not null default 0,
    constraint nav_non_negative check (nav >= 0),
    units numeric(12,4) not null,
    constraint units_non_negative check (units >= 0),
    charge numeric(12,2) not null,
    constraint charge_non_negative check (charge >= 0),
    buy_date timestamp not null default current_timestamp,
    sold_units numeric(12,4) not null default 0,
    constraint sold_units_non_negative check (sold_units >= 0),
    is_sold_out boolean not null default false,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp
);
