CREATE TABLE IF NOT EXISTS cars
(
    car_id SERIAL PRIMARY KEY,
    car_created date NOT NULL,
    car_description varchar(255) NOT NULL,
    car_photo varchar(255),
    car_price int NOT NULL,
    car_status boolean NOT NULL,
    body_id int NOT NULL,
    mark_id int NOT NULL,
    user_id int NOT NULL,
    FOREIGN KEY (body_id) REFERENCES bodies (body_id),
    FOREIGN KEY (mark_id) REFERENCES marks (mark_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
)