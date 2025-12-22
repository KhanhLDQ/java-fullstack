CREATE TABLE IF NOT EXISTS contacts (
    contact_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(15) NOT NULL,
    message VARCHAR(500) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(20)
);