INSERT INTO users (id, academic_performance, age, email, full_name, password, phone, registered, skype, username, work_experience)
VALUES ('1', 4.0, 23, 'adamsallywan@mail.ru', 'Adam Sallywan', '123qwe', '+7(956)345-09-98', '2019-01-01', 'adamsallywan@mail.ru', 'adamsallywan', 0);
INSERT INTO users (id, academic_performance, age, email, full_name, password, phone, registered, skype, username, work_experience)
VALUES ('2', 0.0, 43, 'johntravolta@mail.ru', 'John Travolta', '123qwe', '+7(926)245-29-28', '2015-01-01', 'johntravolta@mail.ru', 'johntravolta', 10);

INSERT INTO role (id, name) VALUES ('1', 'ROLE_STUDENT');
INSERT INTO role (id, name) VALUES ('2', 'ROLE_INSTRUCTOR');
INSERT INTO role (id, name) VALUES ('3', 'ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);