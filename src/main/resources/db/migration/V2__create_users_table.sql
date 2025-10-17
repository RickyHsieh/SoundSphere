-- Create users table for authentication
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create index on username and email for faster lookups
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);

-- Insert a test admin user (password is "admin123" hashed with BCrypt)
-- You'll need to use BCrypt to hash passwords in production
INSERT INTO users (username, email, password, role, created_at, updated_at)
VALUES ('admin', 'admin@soundsphere.com', '$2a$10$xQGXOBBxkXhH6vY7oWnBiOEv4Y2Z.ZZJJQxYj5eZ.ZZJJQxYj5eZZ', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
