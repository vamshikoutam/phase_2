CREATE TABLE batch_category (
    categoryId INT PRIMARY KEY AUTO_INCREMENT,
    batchName VARCHAR(255) NOT NULL
);
CREATE TABLE batches (
    batchId INT PRIMARY KEY AUTO_INCREMENT,    
    schedule VARCHAR(255),
    instructor VARCHAR(255),
    categoryId INT,
    FOREIGN KEY (categoryId) REFERENCES batch_category(categoryId)
);

CREATE TABLE participants (
    participantId INT PRIMARY KEY AUTO_INCREMENT,
    participantName VARCHAR(255),
    age INT,
    gender VARCHAR(10),
    batchId INT,
    FOREIGN KEY (batchId) REFERENCES batches(batchId)
);

-- Inserting data into the 'batche_category' table
INSERT INTO batch_category (batchName) VALUES
    ('Morning'),
    ('Afternoon'),
    ('Evening');

-- Inserting data into the 'batches' table
	INSERT INTO batches (schedule, instructor, categoryId) VALUES
    ('Monday to Friday, 8:00 AM - 10:00 AM', 'John Doe', 1),
    ('Monday to Friday, 2:00 PM - 4:00 PM', 'Jane Doe', 2),
    ('Monday to Friday, 6:00 PM - 8:00 PM', 'Bob Smith', 3);

-- Inserting data into the 'participants' table
INSERT INTO participants (participantName, age, gender, batchId) VALUES
('Alice Johnson', 25, 'Female', 1),
('Bob Smith', 30, 'Male', 2),
('Charlie Brown', 22, 'Male', 3),
('Diana Miller', 28, 'Female', 1),
('Eva Davis', 35, 'Female', 2);


SELECT p.participantId, p.participantName, p.age, p.gender, p.batchId, b.batchName FROM participants p JOIN batches b ON p.batchId = b.batchId;

SELECT b.batchId, b.schedule, b.instructor, c.batchName FROM batches b JOIN batch_category c ON b.categoryId = c.categoryId;
	
SELECT p.participantId, p.participantName, p.age, p.gender, bc.batchName
FROM participants p
JOIN batches b ON p.batchId = b.batchId
JOIN batch_category bc ON b.categoryId = bc.categoryId;