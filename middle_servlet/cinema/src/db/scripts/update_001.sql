CREATE TABLE IF NOT EXISTS account (
  accountId SERIAL PRIMARY KEY,
  name VARCHAR NOT NULL,
  email VARCHAR NOT NULL UNIQUE,
  phone VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS ticket (
    ticketId SERIAL PRIMARY KEY,
    sessionId INT NOT NULL,
    row INT NOT NULL,
    cell INT NOT NULL,
	price INT NOT NULL,
    accountId INT REFERENCES account(accountId),
    UNIQUE (sessionId, row, cell)
);