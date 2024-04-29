CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL unique,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_users_roles_users FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS stocks
(
    id BIGSERIAL NOT NULL,
    ticker VARCHAR(255)NOT NULL,
    query_count BIGINT NOT NULL,
    results_count BIGINT NOT NULL,
    adjusted BOOLEAN NOT NULL,
    status VARCHAR(255),
    request_id VARCHAR(255),
    count INT NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS results
(
    id BIGSERIAL NOT NULL,
    volume BIGINT NOT NULL,
    volume_weight double precision NOT NULL,
    open double precision NOT NULL,
    close double precision NOT NULL,
    high double precision NOT NULL,
    low double precision NOT NULL,
    time BIGINT NOT NULL,
    number BIGINT NOT NULL,
    stock_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_results_stock_id FOREIGN KEY (stock_id) REFERENCES stocks(id)
);

CREATE TABLE tickers
(
    id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL
);