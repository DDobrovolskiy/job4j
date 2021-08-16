CREATE TABLE IF NOT EXISTS cities (
   city_id SERIAL PRIMARY KEY,
   city_name TEXT
);

CREATE TABLE IF NOT EXISTS candidates (
   id SERIAL PRIMARY KEY,
   name TEXT,
   city_id int NOT NULL,
   data timestamp DEFAULT current_timestamp,
   FOREIGN KEY (city_id) REFERENCES cities (city_id)
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