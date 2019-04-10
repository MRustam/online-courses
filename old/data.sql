
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO role (name) VALUES ('ROLE_STUDENT');

INSERT INTO users (username, password, full_name, age, phone, email, skype, role_id, registered)
VALUES ('travoltaJohn', '$2a$10$Lp5Nvy4mHAbyC6MGxJrhVOQfnAHAzQiNbK7ZpzX11jGWqfp/59jke', 'John Travolta', 40, '+99800800', 'travolta@gmail.com', 'travolta@gmail.com', 2, '2018-02-02');
INSERT INTO users (username, password, full_name, age, phone, email, skype, role_id, registered)
VALUES ('timKook', '$2a$10$Lp5Nvy4mHAbyC6MGxJrhVOQfnAHAzQiNbK7ZpzX11jGWqfp/59jke', 'Tim Kook', 45, '+99700700', 'kook@gmail.com', 'kook@gmail.com', 2, '2018-02-02');
INSERT INTO users (username, password, full_name, age, phone, email, skype, role_id, registered)
VALUES ('albertEnstein', '$2a$10$Lp5Nvy4mHAbyC6MGxJrhVOQfnAHAzQiNbK7ZpzX11jGWqfp/59jke', 'Albert Enstein', 55, '+99400400', 'enstein@gmail.com', 'enstein@gmail.com', 3, '2018-02-02');
INSERT INTO users (username, password, full_name, age, phone, email, skype, role_id, registered)
VALUES ('jetLi', '$2a$10$Lp5Nvy4mHAbyC6MGxJrhVOQfnAHAzQiNbK7ZpzX11jGWqfp/59jke', 'Jet Li', 35, '+99100100', 'li@gmail.com', 'li@gmail.com', 3, '2018-02-02');
INSERT INTO users (username, password, full_name, age, phone, email, skype, role_id, registered)
VALUES ('tomHardy', '$2a$10$Lp5Nvy4mHAbyC6MGxJrhVOQfnAHAzQiNbK7ZpzX11jGWqfp/59jke', 'Tom Hardy', 45, '+89100100', 'tom@gmail.com', 'tom@gmail.com', 3, '2018-02-02');
INSERT INTO users (username, password, full_name, age, phone, email, skype, role_id, registered)
VALUES ('tomyLi', '$2a$10$Lp5Nvy4mHAbyC6MGxJrhVOQfnAHAzQiNbK7ZpzX11jGWqfp/59jke', 'Tomy Li Djons', 35, '+69100100', 'djons@gmail.com', 'djons@gmail.com', 3, '2018-02-02');
INSERT INTO users (username, password, full_name, age, phone, email, skype, role_id, registered)
VALUES ('johnRembo', '$2a$10$Lp5Nvy4mHAbyC6MGxJrhVOQfnAHAzQiNbK7ZpzX11jGWqfp/59jke', 'John Rembo', 65, '+9969100100', 'djohn@gmail.com', 'djohn@gmail.com', 1, '2018-02-02');


INSERT INTO instructor (user_id, work_experience, rating)
VALUES (1, 8, 4.2);
INSERT INTO instructor (user_id, work_experience, rating)
VALUES (2, 10, 3.5);

INSERT INTO student (user_id, academic_performance)
VALUES (3, 4);
INSERT INTO student (user_id, academic_performance)
VALUES (4, 3);
INSERT INTO student (user_id, academic_performance)
VALUES (5, 2);
INSERT INTO student (user_id, academic_performance)
VALUES (6, 5);

INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('Java 7EE', 'programming', 'For not beginners.', 35, '2018-03-01', 1, 9.5, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('C#', 'programming', 'For beginners.', 45, '2018-05-10', 1, 7.3, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('Java 8SE', 'programming', 'For advanced.', 15, '2018-04-20', 1, 5.6, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('English for every one', 'languages', 'pre-intermediate', 65, '2018-03-11', 2, 4.9, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('Germany', 'languages', 'For beginners.', 45, '2018-06-01', 2, 4.8, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('Swift', 'programming', 'Advanced', 100, '2018-06-21', 1, 2.5, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('C', 'programming', 'Only for beginners.', 29, '2018-05-31', 1, 7.7, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('Play guitar', 'hobby', 'For beginners.', 20, '2018-04-10', 2, 9.8, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('English for advanced', 'languages', 'upper-intermediate', 55, '2018-08-19', 2, 4.8, true);
INSERT INTO course (title, category, description, duration, start_date, instructor_id, rating, status)
VALUES ('Spain', 'languages', 'For intermediate.', 70, '2018-02-02', 2, 5.5, true);


INSERT INTO review (text, created, course_id, user_id)
VALUES ('В том случае, если среди читателей рассылки много пользователей различных устройств и почтовых клиентов, ' ||
 'необходимо делать письма адаптивными, чтобы все подписчики могли комфортно с ними взаимодействовать. Однако обеспечение ' ||
  'адаптивности — не такое уж и простое дело, поэтому, когда нужно сделать так, чтобы письма гарантированно хорошо выглядели ' ||
   'на максимально возможном количестве устройств и почтовых программ, стоит обратить свой взор на plain-text.', '2014-03-29', 1, 3);
INSERT INTO review (text, created, course_id, user_id)
VALUES ('Если компания хочет наладить личные отношения с текущим или потенциальным клиентом, то HTML-письмо — не лучший вариант ' ||
 'сделать это. Люди не пишут друг другу HTML-письма — не бывает такого, что вы получаете письмо от босса с темой «Важно, успеть до ' ||
  'понедельника», открываете его и видите там красиво сверстанный шаблон. ', '2011-01-11', 1, 4);
INSERT INTO review (text, created, course_id, user_id)
VALUES ('Если компания хочет наладить личные отношения с текущим или потенциальным клиентом, то HTML-письмо — не лучший вариант ' ||
 'сделать это. Люди не пишут друг другу HTML-письма — не бывает такого, что вы получаете письмо от босса с темой «Важно, успеть до ' ||
  'понедельника», открываете его и видите там красиво сверстанный шаблон. ', '2011-01-11', 1, 5);
  INSERT INTO review (text, created, course_id, user_id)
VALUES ('В том случае, если среди читателей рассылки много пользователей различных устройств и почтовых клиентов, ' ||
 'необходимо делать письма адаптивными, чтобы все подписчики могли комфортно с ними взаимодействовать. Однако обеспечение ' ||
  'адаптивности — не такое уж и простое дело, поэтому, когда нужно сделать так, чтобы письма гарантированно хорошо выглядели ' ||
   'на максимально возможном количестве устройств и почтовых программ, стоит обратить свой взор на plain-text.', '2014-03-29', 1, 3);
INSERT INTO review (text, created, course_id, user_id)
VALUES ('В том случае, если среди читателей рассылки много пользователей различных устройств и почтовых клиентов, ' ||
 'необходимо делать письма адаптивными, чтобы все подписчики могли комфортно с ними взаимодействовать. Однако обеспечение ' ||
  'адаптивности — не такое уж и простое дело, поэтому, когда нужно сделать так, чтобы письма гарантированно хорошо выглядели ' ||
   'на максимально возможном количестве устройств и почтовых программ, стоит обратить свой взор на plain-text.', '2014-03-29', 4, 3);


INSERT INTO student_course (student_id, course_id) VALUES (1, 1);
INSERT INTO student_course (student_id, course_id) VALUES (1, 2);
INSERT INTO student_course (student_id, course_id) VALUES (1, 3);
INSERT INTO student_course (student_id, course_id) VALUES (2, 4);
INSERT INTO student_course (student_id, course_id) VALUES (2, 5);
INSERT INTO student_course (student_id, course_id) VALUES (2, 6);
INSERT INTO student_course (student_id, course_id) VALUES (2, 1);
INSERT INTO student_course (student_id, course_id) VALUES (3, 1);
INSERT INTO student_course (student_id, course_id) VALUES (4, 1);
