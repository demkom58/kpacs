CREATE TABLE IF NOT EXISTS  kpacs
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(250) NOT NULL,
    description   VARCHAR(2000),
    creation_date VARCHAR(10)  NOT NULL,
    UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS  kpac_sets
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS kpac_set_kpacs
(
    kpac_set_id INT,
    kpac_id     INT,
    PRIMARY KEY (kpac_set_id, kpac_id),
    FOREIGN KEY (kpac_set_id) REFERENCES kpac_sets (id),
    FOREIGN KEY (kpac_id) REFERENCES kpacs (id)
);