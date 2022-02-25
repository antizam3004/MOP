CREATE TABLE IF NOT EXISTS requests_tbl(
    request_id SERIAL NOT NULL,
    url VARCHAR(128) NOT NULL,
    request_executed_at DATE NOT NULL,
    response_time INT NOT NULL,
    PRIMARY KEY (request_id)
);