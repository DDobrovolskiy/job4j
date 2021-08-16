  CREATE TABLE IF NOT EXISTS posts (
   id SERIAL PRIMARY KEY,
   name TEXT
);

CREATE TABLE IF NOT EXISTS candidates (
   id SERIAL PRIMARY KEY,
   name TEXT
);

CREATE TABLE IF NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   name TEXT,
   email TEXT,
   password TEXT,
   UNIQUE (email)
);