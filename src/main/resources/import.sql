CREATE TABLE IF NOT EXISTS feature_toggle (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255)
);
INSERT INTO feature_toggle (id, name) VALUES ('1', 'test toggle');
