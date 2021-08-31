CREATE TABLE car
(
    car_id SERIAL PRIMARY KEY,
    car_name TEXT,
    engine_id int,
    FOREIGN KEY (engine_id) REFERENCES engine (engine_id)
)