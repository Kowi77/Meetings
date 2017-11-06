/*Populating DB*/

DELETE FROM members;
DELETE FROM meets;
DELETE FROM employers;
DELETE FROM departs;



INSERT INTO departs (name) VALUES
  ('Бухгалтерия'),
  ('Производство'),
  ('Бездельники'),
  ('Руководство');

INSERT INTO employers (fullname, birthday, depart_id) VALUES
  ('Коваленко Андрей Александрович','1977-03-17', 4),
  ('Коваленко Юлия Владимировна','1980-08-24', 3),
  ('Ибрагимович Златан Иванович','1974-07-12', 4),
  ('Кучеров Никита Андреевич','1992-09-07', 2),
  ('Петров Иван Иванович','1979-01-01', 1),
  ('Иванова Гадя Александровна','2000-04-23', 1),
  ('Михайлов Антон Антонович','1998-06-28', 1),
  ('Ефимов Артем Петрович','1995-07-30', 2);

INSERT INTO meets (date, theme, employer_id) VALUES
  ('2017-10-28 13:00', 'Увеличение продаж', 3),
  ('2017-10-29 15:00', 'Уменьшение зарплат', 1),
  ('2017-10-15 10:00', 'Ситуация в мире', 6),
  ('2017-01-11 13:00', 'Празднование Нового года', 2),
  ('2017-05-12 13:00', 'Что делать?', 8),
  ('2017-07-05 13:00', 'Как делать план?', 4);

INSERT INTO members (meet_id, employer_id) VALUES
  (1, 5),
  (1, 2),
  (1, 8),
  (1, 4),
  (2, 5),
  (2, 6),
  (3, 7),
  (3, 8),
  (4, 1),
  (4, 5),
  (4, 3),
  (5, 4),
  (5, 5),
  (5, 6),
  (5, 7),
  (6, 1),
  (6, 3),
  (6, 2);

/*SELECT meets.date, meets.theme, employers.fullName, departs.name, COUNT(members.employer_id) FROM meets
  LEFT JOIN employers ON meets.employer_id = employers.id
  LEFT JOIN departs ON employers.depart_id = departs.id
  LEFT JOIN members ON meets.id = members.meet_id
  GROUP BY meets.theme;*/

/*SELECT m.employer_id, e.fullname, e.birthday, d.name FROM members m
LEFT JOIN employers e ON m.employer_id = e.id
LEFT JOIN departs d ON e.depart_id = d.id
WHERE m.meet_id=4;*/

/*SELECT e.id, e.fullname, e.birthday, d.name AS departName FROM employers e
LEFT JOIN departs d ON e.depart_id = d.id;*/

SELECT Meets.id, Meets.date, Meets.theme, e.fullName AS employer, d.name AS depart, COUNT(m.employer_id) AS quantity FROM Meets
  LEFT JOIN Employers e ON Meets.employer_id = e.id
  LEFT JOIN Departs d ON e.depart_id = d.id
  LEFT JOIN Members m ON Meets.id = m.meet_id WHERE Meets.id = ANY (SELECT m.meet_id FROM members m WHERE m.employer_id = 2) GROUP BY Meets.theme;


