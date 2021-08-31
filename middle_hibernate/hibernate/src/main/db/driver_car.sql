CREATE TABLE driver_car
(
    driver_id integer NOT NULL,
    car_id integer NOT NULL,
    FOREIGN KEY (driver_id) REFERENCES driver (driver_id)
    FOREIGN KEY (car_id) REFERENCES car (car_id)
)