-- Drop database if exists and create a new one
DROP DATABASE IF EXISTS votingsystem;
CREATE DATABASE votingsystem;
USE votingsystem;

-- Create the Admin table
CREATE TABLE admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

-- Create the User (Voter) table
CREATE TABLE user (
    aadhar VARCHAR(12) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    mobile_number VARCHAR(15),
    address VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(50),
    pincode INT,
    gender VARCHAR(10),
    date_of_birth DATE,
    has_voted BOOLEAN DEFAULT FALSE,
    voter_id_number VARCHAR(50) UNIQUE
);

-- Migration script to add demo data

-- Insert demo admin accounts
INSERT INTO admin (username, password, first_name, last_name, email)
VALUES 
    ('admin1', 'password123', 'John', 'Doe', 'admin1@votenow.com'),
    ('admin2', 'password123', 'Jane', 'Smith', 'admin2@votenow.com');

-- Insert demo voter accounts
INSERT INTO user (aadhar, username, password, first_name, last_name, email, mobile_number, address, city, state, pincode, gender, date_of_birth, voter_id_number)
VALUES 
    ('123456789012', 'voter1', 'password123', 'Robert', 'Johnson', 'voter1@example.com', '9876543210', '123 Main St', 'New York', 'NY', 10001, 'Male', '1985-05-15', 'VID12345'),
    ('987654321098', 'voter2', 'password123', 'Emily', 'Williams', 'voter2@example.com', '8765432109', '456 Elm St', 'Los Angeles', 'CA', 90001, 'Female', '1990-08-20', 'VID67890'),
    ('456789012345', 'voter3', 'password123', 'Michael', 'Brown', 'voter3@example.com', '7654321098', '789 Oak St', 'Chicago', 'IL', 60601, 'Male', '1978-12-10', 'VID23456');

-- Create the Election table
CREATE TABLE election (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_date_time DATETIME NOT NULL,
    end_date_time DATETIME NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

-- Create the Candidate table
CREATE TABLE candidate (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    party VARCHAR(100),
    position VARCHAR(100),
    profile_picture VARCHAR(255),
    manifesto TEXT,
    election_id BIGINT NOT NULL,
    FOREIGN KEY (election_id) REFERENCES election(id) ON DELETE CASCADE
);

-- Create the Vote table
CREATE TABLE vote (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_aadhar VARCHAR(12),
    candidate_id BIGINT,
    election_id BIGINT,
    timestamp DATETIME NOT NULL,
    voter_hash VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_aadhar) REFERENCES user(aadhar) ON DELETE SET NULL,
    FOREIGN KEY (candidate_id) REFERENCES candidate(id) ON DELETE SET NULL,
    FOREIGN KEY (election_id) REFERENCES election(id) ON DELETE SET NULL,
    UNIQUE(user_aadhar, election_id)
);

-- Insert sample admin
INSERT INTO admin (aadhar, username, password, first_name, last_name, email)
VALUES ('123456789012', 'admin', 'password', 'System', 'Administrator', 'admin@votesystem.com');

-- Insert sample elections
INSERT INTO election (title, description, start_date_time, end_date_time, active)
VALUES 
('City Council Election 2025', 'Election for the city council representatives', '2025-06-01 08:00:00', '2025-06-02 18:00:00', true),
('Student Body President Election', 'Annual election for student body president and vice president', '2025-05-25 09:00:00', '2025-05-25 17:00:00', true);

-- Insert sample candidates
INSERT INTO candidate (name, party, position, profile_picture, manifesto, election_id)
VALUES
('John Smith', 'Progressive Party', 'Council Member', '', 'Focusing on improving public transportation and green spaces.', 1),
('Emily Johnson', 'Citizens Alliance', 'Council Member', '', 'Advocating for small business support and public safety.', 1),
('Michael Wilson', 'Independent', 'Council Member', '', 'Working to improve education and community services.', 1),
('Sarah Martinez', 'Student Union', 'President', '', 'Planning to improve campus facilities and student welfare.', 2),
('David Thompson', 'Campus Reform', 'President', '', 'Advocating for curriculum reforms and student representation.', 2);
