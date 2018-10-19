
INSERT INTO instructor_detail (work_experience, email, skype)
VALUES (12, 'travolta-instructor@mail.com', 'skype-travolta-instructor@mail.com');

INSERT INTO instructor (first_name, last_name, detail_id)
VALUES ('John', 'Travolta', 1);

INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('Java 7EE', 'programming', 'For not beginners.', 35, '2018-03-01', 1, 9.5);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('C#', 'programming', 'For beginners.', 45, '2018-05-10', 1, 7.3);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('Java 8SE', 'programming', 'For advanced.', 15, '2018-04-20', 1, 5.6);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('English for every one', 'languages', 'pre-intermediate', 65, '2018-03-11', 1, 4.9);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('Germany', 'languages', 'For beginners.', 45, '2018-06-01', 1, 4.8);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('Swift', 'programming', 'Advanced', 100, '2018-06-21', 1, 2.5);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('C', 'programming', 'Only for beginners.', 29, '2018-05-31', 1, 7.7);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('Play guitar', 'hobby', 'For beginners.', 20, '2018-04-10', 1, 9.8);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('English for advanced', 'languages', 'upper-intermediate', 55, '2018-08-19', 1, 4.8);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating)
VALUES ('Spain', 'languages', 'For intermediate.', 70, '2018-02-02', 1, 5.5);

INSERT INTO course_review (text, creation_time, course_id)
VALUES ('Bla bla-bla Bla bla-bla Bla bla-bla Bla bla-bla', '2014-03-29', 3);
INSERT INTO course_review (text, creation_time, course_id)
VALUES ('Bla bla-bla Bla bla-bla Bla bla-bla Bla bla-bla', '2011-01-11', 3);

INSERT INTO student_detail (email, skype, age, progress)
VALUES ('alex@mail.com', 'skype_alex@mail.com', 20, 33);
INSERT INTO student_detail (email, skype, age, progress)
VALUES ('rodger@mail.com', 'skype_rodger@mail.com', 30, 43);
INSERT INTO student_detail (email, skype, age, progress)
VALUES ('tim@mail.com', 'skype_tim@mail.com', 19, 65);
INSERT INTO student_detail (email, skype, age, progress)
VALUES ('bob@mail.com', 'skype_bob@mail.com', 24, 89);

INSERT INTO student (first_name, last_name, student_detail_id)
VALUES ('Alex', 'Fox', 1);
INSERT INTO student (first_name, last_name, student_detail_id)
VALUES ('Rodger', 'Donnavan', 2);
INSERT INTO student (first_name, last_name, student_detail_id)
VALUES ('Tim', 'Cock', 3);
INSERT INTO student (first_name, last_name, student_detail_id)
VALUES ('Bob', 'Swagger', 4);
