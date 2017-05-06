DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', 'admin'),
  ('Admin1', 'admin1@gmail.com', 'admin1'),
  ('Admin2', 'admin2@gmail.com', 'admin2'),
  ('User1', 'user1@gmail.com', 'user1'),
  ('User2', 'user2@gmail.com', 'user2'),
  ('User3', 'user3@gmail.com', 'user3'),
  ('User4', 'user4@gmail.com', 'user4'),
  ('User5', 'user5@gmail.com', 'user5'),
  ('User6', 'user6@gmail.com', 'user6'),
  ('User7', 'user7@gmail.com', 'user7'),
  ('User8', 'user8@gmail.com', 'user8');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2015-05-30 10:00:00', 'Завтрак', 500, 100000),
  ('2015-05-30 13:00:00', 'Обед', 1000, 100000),
  ('2015-05-30 20:00:00', 'Ужин', 500, 100000),
  ('2015-05-31 10:00:00', 'Завтрак', 500, 100000),
  ('2015-05-31 13:00:00', 'Обед', 1000, 100000),
  ('2015-05-31 20:00:00', 'Ужин', 510, 100000),
  ('2015-06-01 14:00:00', 'Админ ланч', 510, 100001),
  ('2015-06-01 21:00:00', 'Админ ужин', 1500, 100002);
