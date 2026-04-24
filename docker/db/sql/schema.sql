CREATE
DATABASE IF NOT EXISTS pubQuiz;
USE
pubQuiz;

CREATE TABLE IF NOT EXISTS questions (
    id VARCHAR(36) PRIMARY KEY,
    question VARCHAR(255) NOT NULL,
    correct_answer VARCHAR(100) NOT NULL,
    incorrect_answer_1 VARCHAR(100) NOT NULL,
    incorrect_answer_2 VARCHAR(100) NOT NULL,
    incorrect_answer_3 VARCHAR(100) NOT NULL
    );

INSERT INTO questions (id, question, correct_answer, incorrect_answer_1, incorrect_answer_2, incorrect_answer_3)
VALUES ('6279ba79-7c12-4290-ab98-9592788a7e5b','What is 1 + 1?', '2', '1', '3', '4'),
       ('b6d5965d-7b1a-4f0c-82b3-247a36b1da95','What is 2 + 3?', '5', '4', '6', '7'),
       ('0875a75e-15e1-4bc6-9f61-7ca9cabf4aa7','What is 5 - 2?', '3', '2', '4', '1'),
       ('411f50c9-587d-4430-9542-4c08f5ff17a2','What is 10 / 2?', '5', '2', '10', '4'),
       ('3f1ee557-0b77-4df2-a9b9-06af12a8681f','What is 4 * 2?', '8', '6', '10', '12'),
       ('52fa49d9-a7aa-4fb5-9a97-d4564e4fa075','What is 7 + 4?', '11', '10', '12', '14'),
       ('59f7b46f-82ed-48b3-9844-7c0d8fb38eb9','What is 9 - 5?', '4', '3', '5', '2'),
       ('47307c91-00ae-4214-ae69-d9279ffde79e','What is 3 * 3?', '9', '6', '12', '8'),
       ('a2a9dcc6-e460-4843-b3a0-fddf6a6df6f2','What is 15 / 3?', '5', '3', '10', '15'),
       ('f8dc57f9-6dc1-4d80-8326-020ec5ce5921','What is 6 + 2?', '8', '4', '7', '9');