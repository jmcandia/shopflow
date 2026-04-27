CREATE TABLE users (
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    username     VARCHAR(25)  NOT NULL,
    password     VARCHAR(100) NOT NULL,
    full_name    VARCHAR(150) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15),
    role         VARCHAR(50)  NOT NULL DEFAULT 'USER',
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_users          PRIMARY KEY (id),
    CONSTRAINT uq_users_username UNIQUE (username),
    CONSTRAINT uq_users_email    UNIQUE (email)
);