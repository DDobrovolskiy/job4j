CREATE TABLE IF NOT EXISTS cities (
   citi_id SERIAL PRIMARY KEY,
   citi_name TEXT
);

CREATE TABLE IF NOT EXISTS candidates (
   id SERIAL PRIMARY KEY,
   name TEXT,
   citi_id int NOT NULL,
   data timestamp DEFAULT current_timestamp,
   FOREIGN KEY (citi_id) REFERENCES cities (citi_id)
);

CREATE TABLE IF NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   name TEXT,
   email TEXT,
   password TEXT,
   UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS posts (
   id SERIAL PRIMARY KEY,
   name TEXT,
   data timestamp DEFAULT current_timestamp
);