CREATE TABLE users (
id bigint PRIMARY KEY  GENERATED ALWAYS AS IDENTITY,
name VARCHAR(256) NOT NULL,
email varchar(64) UNIQUE NOT NULL
);

CREATE TABLE subscription (
id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
title VARCHAR(256) NOT NUll,
user_id bigint NOT NULL,
CONSTRAINT fk_users_subscription FOREIGN KEY (user_id) REFERENCES users(id)
);