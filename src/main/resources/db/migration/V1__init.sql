CREATE TABLE practice_sessions (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    notes TEXT,
    duration_minutes INT NOT NULL,
    practiced_at TIMESTAMP NOT NULL
);
