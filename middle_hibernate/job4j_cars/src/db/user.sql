CREATE TABLE IF NOT EXISTS users
(
    user_id SERIAL PRIMARY KEY,
    user_email varchar(255) NOT NULL,
    user_name varchar(255) NOT NULL UNIQUE,
    user_password varchar(255) NOT NULL
)