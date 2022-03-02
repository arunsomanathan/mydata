-- Create All Tables - START
create TABLE IF NOT EXISTS deposit_account(
    id serial PRIMARY KEY,
    bank_name text NOT NULL,
    branch text,
    account_number text NOT NULL UNIQUE,
    balance numeric(12,2) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS loan_account(
    id serial PRIMARY KEY,
    bank_name text NOT NULL,
    branch text,
    account_number text NOT NULL UNIQUE,
    balance numeric(12,2) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS miscellaneous(
    id serial PRIMARY KEY,
    investment_name text NOT NULL UNIQUE,
    balance numeric(12,2) not null,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS mutual_fund(
    id serial PRIMARY KEY,
    mf_code text NOT NULL UNIQUE,
    mf_name text NOT NULL UNIQUE,
    amc text NOT NULL,
    type text NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS saving_account(
    id serial PRIMARY KEY,
    bank_name text NOT NULL,
    branch text,
    account_number text NOT NULL UNIQUE,
    balance numeric(12,2) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);

create TABLE IF NOT EXISTS stock(
    id serial PRIMARY KEY,
    stock_code text NOT NULL UNIQUE,
    stock_name text NOT NULL UNIQUE,
    stock_exchange text NOT NULL,
    broker text NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active boolean NOT NULL DEFAULT TRUE
);
-- Create All Tables - END

-- Insert Deposit Account records
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name One', 'Branch One', '1', 1.1);
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name Two', 'Branch Two', '22', 22.22);
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name Three', 'Branch Three', '333', 333.33);
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name Four', 'Branch Four', '4444', 4444.44);
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name Five', 'Branch Five', '55555', 55555.55);

-- Insert Loan Account records
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name One', 'Branch One', '1', 1.1);
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name Two', 'Branch Two', '22', 22.22);
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name Three', 'Branch Three', '333', 333.33);
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name Four', 'Branch Four', '4444', 4444.44);
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name Five', 'Branch Five', '55555', 55555.55);

-- Insert Miscellaneous Account records
insert into miscellaneous (investment_name, balance) values ('Investment Name One', 1.1);
insert into miscellaneous (investment_name, balance) values ('Investment Name Two', 22.22);
insert into miscellaneous (investment_name, balance) values ('Investment Name Three', 333.33);
insert into miscellaneous (investment_name, balance) values ('Investment Name Four', 4444.44);
insert into miscellaneous (investment_name, balance) values ('Investment Name Five', 55555.55);

-- Insert Mutual Fund records
insert into mutual_fund (mf_code, mf_name, amc, type) values ('ONE', 'MF Name One', 'Six', 'Nine');
insert into mutual_fund (mf_code, mf_name, amc, type) values ('TWO', 'MF Name Two', 'Seven', 'Nine');
insert into mutual_fund (mf_code, mf_name, amc, type) values ('THREE', 'MF Name Three', 'Seven', 'Nine');
insert into mutual_fund (mf_code, mf_name, amc, type) values ('FOUR', 'MF Name Four', 'Eight', 'Ten');
insert into mutual_fund (mf_code, mf_name, amc, type) values ('FIVE', 'MF Name Five', 'Eight', 'Ten');


--Insert Saving Account records
insert into saving_account (bank_name, branch, account_number, balance) values ('Bank Name One', 'Branch One', '1', 1.1);
insert into saving_account (bank_name, branch, account_number, balance) values ('Bank Name Two', 'Branch Two', '22', 22.22);
insert into saving_account (bank_name, branch, account_number, balance) values ('Bank Name Three', 'Branch Three', '333', 333.33);
insert into saving_account (bank_name, branch, account_number, balance) values ('Bank Name Four', 'Branch Four', '4444', 4444.44);
insert into saving_account (bank_name, branch, account_number, balance) values ('Bank Name Five', 'Branch Five', '55555', 55555.55);

-- Insert Stock records
insert into stock (stock_code, stock_name, stock_exchange, broker) values ('ONE', 'Stock Name One', 'Six', 'Nine');
insert into stock (stock_code, stock_name, stock_exchange, broker) values ('TWO', 'Stock Name Two', 'Seven', 'Nine');
insert into stock (stock_code, stock_name, stock_exchange, broker) values ('THREE', 'Stock Name Three', 'Seven', 'Nine');
insert into stock (stock_code, stock_name, stock_exchange, broker) values ('FOUR', 'Stock Name Four', 'Eight', 'Ten');
insert into stock (stock_code, stock_name, stock_exchange, broker) values ('FIVE', 'Stock Name Five', 'Eight', 'Ten');

commit;