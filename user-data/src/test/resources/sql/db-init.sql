-- Insert Deposit Account records
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name One', 'Branch One', '1', 1.1);
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name Two', 'Branch Two', '22', 22.22);
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name Three', 'Branch Three', '333', 333.33);
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name Four', 'Branch Four', '4444', 4444.44);
insert into deposit_account (bank_name, branch, account_number, balance) values ('Bank Name Five', 'Branch Five', '55555', 55555.55);

-- Insert Loan Account records
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name One', 'Branch One', '1', -1.1);
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name Two', 'Branch Two', '22', -22.22);
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name Three', 'Branch Three', '333', -333.33);
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name Four', 'Branch Four', '4444', -4444.44);
insert into loan_account (bank_name, branch, account_number, balance) values ('Bank Name Five', 'Branch Five', '55555', -55555.55);

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

-- Insert Mutual Fund Buy Transaction records
insert into mutual_fund_buy_transaction (mf_id, nav, units, charge) values (1, 10.11, 111.11, 5.55);
insert into mutual_fund_buy_transaction (mf_id, nav, units, charge) values (2, 10.22, 222.22, 4.44);
insert into mutual_fund_buy_transaction (mf_id, nav, units, charge) values (3, 10.33, 333.33, 3.33);
insert into mutual_fund_buy_transaction (mf_id, nav, units, charge) values (4, 10.44, 444.44, 2.22);
insert into mutual_fund_buy_transaction (mf_id, nav, units, charge) values (5, 10.55, 555.55, 1.11);

-- Insert Mutual Fund Sell Transaction records
insert into mutual_fund_sell_transaction (mf_id, buy_ids, nav, units, charge, profit_loss) values (1, '{1}', 10.11, 111.11, 5.55, 10);
insert into mutual_fund_sell_transaction (mf_id, buy_ids, nav, units, charge, profit_loss) values (2, '{2}', 10.22, 222.22, 4.44, -10);
insert into mutual_fund_sell_transaction (mf_id, buy_ids, nav, units, charge, profit_loss) values (3, '{3}', 10.33, 333.33, 3.33, 100);
insert into mutual_fund_sell_transaction (mf_id, buy_ids, nav, units, charge, profit_loss) values (4, '{4}', 10.44, 444.44, 2.22, -100);
insert into mutual_fund_sell_transaction (mf_id, buy_ids, nav, units, charge, profit_loss) values (5, '{5}', 10.55, 555.55, 1.11, 200);

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