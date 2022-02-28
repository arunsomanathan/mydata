CREATE TABLE IF NOT EXISTS miscellaneous(
    id integer PRIMARY KEY,
    investment_name text NOT NULL UNIQUE,
    balance numeric(12,2) not null,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);