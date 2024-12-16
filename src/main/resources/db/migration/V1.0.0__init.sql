CREATE TABLE IF NOT EXISTS mikasa_user
(
    id             UUID                NOT NULL,
    name           VARCHAR(32)         NOT NULL,
    surname        VARCHAR(32)         NOT NULL,
    phone          VARCHAR(255) UNIQUE NOT NULL,
    email          VARCHAR(512) UNIQUE NOT NULL,
    role           VARCHAR(32)         NOT NULL,
    password       VARCHAR(512)        NOT NULL,
    email_verified BOOLEAN             NOT NULL,
    created_at     TIMESTAMP           NOT NULL,
    updated_at     TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS mikasa_refresh_token
(
    user_id       UUID          NOT NULL,
    refresh_token VARCHAR(1024) NOT NULL,
    PRIMARY KEY (user_id, refresh_token),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES mikasa_user (id) ON DELETE CASCADE
);

/*INSERT INTO mikasa_user (id, name, surname, username, email, user_role, password, refresh_token,
                         email_verified,
                         created_at, updated_at)
VALUES ('c7f73c6f-8de6-4da7-af17-985580331600', 'Admin', 'Mikasa', 'mikasa',
        'mikasasports@gmail.com', 'ADMIN',
        '{bcrypt}$2a$10$2dXhOH2X9FW6kC.gn/uVju3vM9U4vtSV73ZcA6YTyx0OlV1NkSlfK', FALSE,
        NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);*/