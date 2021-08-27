CREATE TABLE IF NOT EXISTS users (
  id_user SERIAL PRIMARY KEY,
  name_user VARCHAR NOT NULL,
  email_user VARCHAR NOT NULL,
  password_user VARCHAR NOT NULL,
  UNIQUE (email_user)
);

CREATE TABLE IF NOT EXISTS items (
  id SERIAL PRIMARY KEY,
  description VARCHAR NOT NULL,
  createdTime TIMESTAMP NOT NULL DEFAULT current_timestamp,
  done BOOLEAN NOT NULL DEFAULT false,
  id_user int NOT NULL REFERENCES users(id_user)
);