CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    email VARCHAR(400) NOT NULL,
    password VARCHAR(250) NOT NULL
)