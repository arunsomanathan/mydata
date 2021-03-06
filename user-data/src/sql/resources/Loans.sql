create table if not exists loan_account(
    id serial primary key,
    bank_name text not null,
    branch text,
    account_number text not null unique,
    balance numeric(12,2) not null,
    constraint balance_non_positive check (balance <= 0),
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp,
    active boolean not null default true
);