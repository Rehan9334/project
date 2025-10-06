-- V1__init.sql
-- Basic schema for users, user_roles, accounts, transactions.
CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_id BIGINT NOT NULL,
  role VARCHAR(50) NOT NULL,
  CONSTRAINT fk_userroles_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS accounts (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  account_number VARCHAR(255) NOT NULL UNIQUE,
  balance DECIMAL(19,4) NOT NULL,
  user_id BIGINT,
  CONSTRAINT fk_accounts_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS transactions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  from_account_id BIGINT,
  to_account_id BIGINT,
  amount DECIMAL(19,4) NOT NULL,
  type VARCHAR(50) NOT NULL,
  description VARCHAR(1000),
  created_at DATETIME,
  CONSTRAINT fk_tx_from_account FOREIGN KEY (from_account_id) REFERENCES accounts(id) ON DELETE SET NULL,
  CONSTRAINT fk_tx_to_account FOREIGN KEY (to_account_id) REFERENCES accounts(id) ON DELETE SET NULL
);
