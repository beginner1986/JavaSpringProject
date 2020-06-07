DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO user (first_name, last_name, email) VALUES
  ('Jan', 'Kowalski', 'j.kowalski@gmail.com'),
  ('Maria', 'Kowalska', 'maria.kowalska@wp.pl'),
  ('Krzysztof', 'Nowak', 'k.nowak@gmail.com'),
  ('Anna', 'Żyżyńska', 'aniaz@buziaczek.pl'),
  ('Bill', 'Gates', 'ceo@microsoft.com'),
  ('Zuzanna', 'Cejrowska', 'zuzi@wp.pl'),
  ('Jadwiga', 'Łodyga', 'jadzka21@o2.pl'),
  ('Grzegorz', 'Brzęczyszczykiewicz', 'chrzaszczyrzeboszyce@gmina.pl');