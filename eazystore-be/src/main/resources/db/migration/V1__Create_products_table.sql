CREATE TABLE IF NOT EXISTS products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(250) NOT NULL,
    description VARCHAR(500) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    popularity INT NOT NULL,
    image_url VARCHAR(500),
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(255)
);