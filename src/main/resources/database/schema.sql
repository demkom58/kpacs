-- Language: MySQL

CREATE TABLE IF NOT EXISTS kpacs
(
    id            INT           NOT NULL AUTO_INCREMENT,
    title         VARCHAR(250)  NOT NULL,
    description   VARCHAR(2000) NOT NULL DEFAULT '',
    creation_date VARCHAR(10)   NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (title),
    INDEX (id)
);

CREATE TABLE IF NOT EXISTS kpac_sets
(
    id    INT          NOT NULL AUTO_INCREMENT,
    title VARCHAR(250) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (title),
    INDEX (id)
);

CREATE TABLE IF NOT EXISTS kpac_set_kpacs
(
    kpac_set_id INT NOT NULL REFERENCES kpac_sets (id) ON DELETE CASCADE,
    kpac_id     INT NOT NULL REFERENCES kpacs (id) ON DELETE CASCADE,
    PRIMARY KEY (kpac_set_id, kpac_id),
    INDEX (kpac_set_id)
);