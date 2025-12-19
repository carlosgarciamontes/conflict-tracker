-- Este archivo es opcional ya que JPA genera el esquema automáticamente
-- Se incluye solo para referencia

-- Tabla de países
CREATE TABLE IF NOT EXISTS countries (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(3) NOT NULL UNIQUE
);

-- Tabla de conflictos
CREATE TABLE IF NOT EXISTS conflicts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    start_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    description TEXT
);

-- Tabla de facciones
CREATE TABLE IF NOT EXISTS factions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    conflict_id BIGINT NOT NULL,
    FOREIGN KEY (conflict_id) REFERENCES conflicts(id) ON DELETE CASCADE
);

-- Tabla de eventos
CREATE TABLE IF NOT EXISTS events (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    event_date DATE NOT NULL,
    location VARCHAR(200) NOT NULL,
    description TEXT,
    conflict_id BIGINT NOT NULL,
    FOREIGN KEY (conflict_id) REFERENCES conflicts(id) ON DELETE CASCADE
);

-- Tabla intermedia para conflictos-países (Many-to-Many)
CREATE TABLE IF NOT EXISTS conflict_countries (
    conflict_id BIGINT NOT NULL,
    country_id BIGINT NOT NULL,
    PRIMARY KEY (conflict_id, country_id),
    FOREIGN KEY (conflict_id) REFERENCES conflicts(id) ON DELETE CASCADE,
    FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE
);

-- Tabla intermedia para facciones-países (apoyos, Many-to-Many)
CREATE TABLE IF NOT EXISTS faction_supporting_countries (
    faction_id BIGINT NOT NULL,
    country_id BIGINT NOT NULL,
    PRIMARY KEY (faction_id, country_id),
    FOREIGN KEY (faction_id) REFERENCES factions(id) ON DELETE CASCADE,
    FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE
);

-- Índices para mejor rendimiento
CREATE INDEX idx_conflict_status ON conflicts(status);
CREATE INDEX idx_country_code ON countries(code);
CREATE INDEX idx_event_date ON events(event_date);
CREATE INDEX idx_event_conflict ON events(conflict_id);
CREATE INDEX idx_faction_conflict ON factions(conflict_id);