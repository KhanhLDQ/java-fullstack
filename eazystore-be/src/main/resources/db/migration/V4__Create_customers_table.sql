CREATE TABLE IF NOT EXISTS customers
(
    customer_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(100)                        NOT NULL,
    email             VARCHAR(100)                        NOT NULL,
    mobile_number     VARCHAR(10)                         NOT NULL,
    password_hash     VARCHAR(500)                        NOT NULL,
    created_at        TIMESTAMP                           NOT NULL,
    created_by        VARCHAR(20)                         NOT NULL,
    last_modified_at  TIMESTAMP,
    last_modified_by  VARCHAR(20),
    CONSTRAINT unq_email UNIQUE (email),
    CONSTRAINT unq_mobile_number UNIQUE (mobile_number)
);