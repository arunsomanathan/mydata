-- Create All Tables - START
create TABLE IF NOT EXISTS deposit_account(
    id integer PRIMARY KEY,
    bank_name text NOT NULL,
    branch text,
    account_number text NOT NULL UNIQUE,
    balance numeric(12,2) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS loan_account(
    id integer PRIMARY KEY,
    bank_name text NOT NULL,
    branch text,
    account_number text NOT NULL UNIQUE,
    balance numeric(12,2) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS miscellaneous(
    id integer PRIMARY KEY,
    investment_name text NOT NULL UNIQUE,
    balance numeric(12,2) not null,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS mutual_fund(
    id integer PRIMARY KEY,
    mf_code text NOT NULL UNIQUE,
    mf_name text NOT NULL UNIQUE,
    amc text NOT NULL,
    type text NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS saving_account(
    id integer PRIMARY KEY,
    bank_name text NOT NULL,
    branch text,
    account_number text NOT NULL UNIQUE,
    balance numeric(12,2) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS stock(
    id integer PRIMARY KEY,
    stock_code text NOT NULL UNIQUE,
    stock_name text NOT NULL UNIQUE,
    stock_exchange text NOT NULL,
    broker text NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

-- Create All Tables - END

