-- Instructor Detail is the first
INSERT INTO instructor_detail (id, email, skype, work_experience)
VALUES (1, 'instructor1@mail.ru', 'instructor1@mail.ru', 10);
INSERT INTO instructor_detail (id, email, skype, work_experience)
VALUES (2, 'instructor2@mail.ru', 'instructor2@mail.ru', 8);
INSERT INTO instructor_detail (id, email, skype, work_experience)
VALUES (3, 'instructor3@mail.ru', 'instructor3@mail.ru', 5);
INSERT INTO instructor_detail (id, email, skype, work_experience)
VALUES (4, 'instructor4@mail.ru', 'instructor4@mail.ru', 6);

-- Instructor is the second
INSERT INTO instructor (id, first_name, last_name, detail_id)
VALUES (1, 'Bob', 'Marlly', 1);
INSERT INTO instructor (id, first_name, last_name, detail_id)
VALUES (2, 'Tim', 'Coock', 2);
INSERT INTO instructor (id, first_name, last_name, detail_id)
VALUES (3, 'Jonh', 'Travolta', 3);
INSERT INTO instructor (id, first_name, last_name, detail_id)
VALUES (4, 'Albert', 'Enstain', 4);
