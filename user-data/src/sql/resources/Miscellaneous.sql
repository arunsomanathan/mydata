create table if not exists miscellaneous(
    id serial primary key,
    investment_name text not null unique,
    balance numeric(12,2) not null,
    constraint balance_non_negative check (balance >= 0),
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp,
    active boolean not null default true
);