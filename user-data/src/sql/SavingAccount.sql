CREATE TABLE IF NOT EXISTS saving_account(
    id integer PRIMARY KEY,
    bank_name text NOT NULL,
    branch text,
    account_number text NOT NULL UNIQUE,
    balance numeric(12,2) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);
