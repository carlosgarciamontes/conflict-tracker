-- Insertar Países
INSERT INTO countries (name, code) VALUES ('Ucrania', 'UKR');
INSERT INTO countries (name, code) VALUES ('Rusia', 'RUS');
INSERT INTO countries (name, code) VALUES ('Israel', 'ISR');
INSERT INTO countries (name, code) VALUES ('Palestina', 'PSE');
INSERT INTO countries (name, code) VALUES ('Estados Unidos', 'USA');

-- Insertar Conflicto
INSERT INTO conflicts (name, start_date, status, description)
VALUES ('Guerra Ruso-Ucraniana', '2014-02-20', 'ACTIVE', 'Conflicto bélico en curso en Europa del Este.');

-- Relacionar Conflicto con Países (Tabla intermedia)
-- Asumiendo que el conflicto es ID 1, y Ucrania/Rusia son 1 y 2
INSERT INTO conflict_countries (conflict_id, country_id) VALUES (1, 1);
INSERT INTO conflict_countries (conflict_id, country_id) VALUES (1, 2);

-- Insertar Facciones (Opcional para test)
INSERT INTO factions (name, conflict_id) VALUES ('Fuerzas Armadas de Ucrania', 1);
INSERT INTO factions (name, conflict_id) VALUES ('Fuerzas Armadas de Rusia', 1);