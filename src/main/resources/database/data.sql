INSERT INTO kpacs (title, description, creation_date)
VALUES ('K-PAC 1', 'Description 1', '25-01-2021'),
       ('K-PAC 2', 'Description 2', '01-01-2022'),
       ('K-PAC 3', 'Description 3', '03-01-2022'),
       ('K-PAC 4', 'Description 4', '20-01-2023'),
       ('K-PAC 5', 'Description 5', '31-01-2024');

INSERT INTO kpac_sets (title)
VALUES ('K-PAC Set 1'),
       ('K-PAC Set 2');

INSERT INTO kpac_set_kpacs (kpac_set_id, kpac_id)
VALUES (1, 1),
       (1, 2),
       (2, 3);