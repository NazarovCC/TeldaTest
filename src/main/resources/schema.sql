CREATE TABLE IF NOT EXISTS region (
    id INTEGER GENERATED always as identity PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    short_title VARCHAR(100) NOT NULL
);