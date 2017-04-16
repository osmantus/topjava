DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM MEALS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO MEALS (description, datetime, calories, user_id)
VALUES ('soup', DATE('01.04.2017'), 2000, 100000);
