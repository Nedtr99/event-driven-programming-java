-- ============================================================
-- Week 10 Capstone: University Management System
-- Database setup script (Phase 1)
-- Run this in the MySQL command line client first:
--   mysql -u root -p
-- ============================================================

-- 1. Create the database
CREATE DATABASE IF NOT EXISTS university_db;
USE university_db;

-- 2. The Programs table (the "parent" side of the relationship)
CREATE TABLE IF NOT EXISTS programs (
    program_id INT AUTO_INCREMENT PRIMARY KEY,
    program_name VARCHAR(100) NOT NULL,
    required_units INT NOT NULL
);

-- Seed data for programs
INSERT INTO programs (program_name, required_units) VALUES
    ('Bachelor of Computer Science', 56),
    ('Diploma in IT', 24);

-- 3. The Students table (the "child" side, linked by Foreign Key)
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,           -- Unique ID for the student
    registration_number VARCHAR(20) UNIQUE NOT NULL,     -- School ID (e.g., S101)
    first_name VARCHAR(50) NOT NULL,                     -- First name
    last_name VARCHAR(50) NOT NULL,                      -- Last name
    email VARCHAR(100) UNIQUE NOT NULL,                   -- Email address
    program_id INT,                                       -- Links student to a program
    FOREIGN KEY (program_id) REFERENCES programs(program_id) -- Database relationship
);
