CREATE TABLE owner
(
    owner_id SERIAL PRIMARY KEY,
    owner_name TEXT,
    car_id int,
    driver_id int,
    FOREIGN KEY (car_id) REFERENCES car (car_id)
    FOREIGN KEY (driver_id) REFERENCES driver (driver_id)
)