create database betterme;

use betterme;

CREATE TABLE betterme_users(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    email      VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(50)  NOT NULL,
    active     BIT(1)      DEFAULT 1,
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id),
    CONSTRAINT uk_betterme_users_email UNIQUE (email)
) ENGINE = InnoDB;

CREATE TABLE daily_records(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id           BIGINT    NOT NULL,
    registration_date TIMESTAMP NOT NULL,

    -- Propiedades de Score (embebidas)
    score_nutrition   DECIMAL(5, 2),
    score_exercise    DECIMAL(5, 2),
    score_sleep       DECIMAL(5, 2),
    score_hydration   DECIMAL(5, 2),
    score_total       DECIMAL(5, 2),

    -- Propiedades de Nutrition (embebidas como booleanos)
    nut_breakfast     BOOLEAN   DEFAULT FALSE,
    nut_snack_one     BOOLEAN   DEFAULT FALSE,
    nut_meal          BOOLEAN   DEFAULT FALSE,
    nut_snack_two     BOOLEAN   DEFAULT FALSE,
    nut_dinner        BOOLEAN   DEFAULT FALSE,

    -- Propiedades directas
    exercise_minutes  DOUBLE PRECISION,
    hydration_ml      DOUBLE PRECISION,
    sleep_minutes     DOUBLE PRECISION,

    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);