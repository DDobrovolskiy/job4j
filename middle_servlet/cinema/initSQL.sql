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

INSERT INTO account(name, phone) VALUES
('Dmitriy', '+79505506330');

INSERT INTO ticket (sessionId, row, cell, price) VALUES
(1, 1, 1, 500),
(1, 1, 2, 500),
(1, 1, 3, 500),
(1, 2, 1, 450),
(1, 2, 2, 450),
(1, 2, 3, 450),
(1, 3, 1, 400),
(1, 3, 2, 400),
(1, 3, 3, 400);

SELECT * FROM ticket;
SELECT * FROM account;
