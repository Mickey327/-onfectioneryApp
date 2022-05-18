INSERT INTO role(name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');
INSERT INTO users(email, password, first_name, last_name) VALUES ('usp2002@mail.ru',
                                                                 '$2y$10$aC5fVrO2WGlvsXMr3tUBzuQIWTUJZZDmnu4Vm1EIS9cHitMqG13eG',
                                                                 'Pavel',
                                                                 'Ulanov');
INSERT INTO users_to_role(user_id, role_id) VALUES (1, 1), (1, 2);
INSERT INTO cart(user_id) VALUES (1);