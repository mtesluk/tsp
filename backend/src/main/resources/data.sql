INSERT INTO account (id, email, username, city, password, account_role) VALUES (1, 'janusz@gmail.com', 'januszek5', 'Wroclaw', '$2a$10$R1r1czTUJZnSIuCufozLyO.sK1BWd3fJ0RPpkKk2.I4iprCnDffJq', 'USER');

INSERT INTO report (description, report_status, city, street, street_number, latitude, longitude, account_id, created_date_time, updated_date_time) VALUES ('opis 1', 'NEW', 'Wrocław', 'Mickiewicza', 10, 51.1079, 17.049, 1, '2020-10-10', '2020-10-10');
INSERT INTO report (description, report_status, city, street, street_number, latitude, longitude, account_id, created_date_time, updated_date_time) VALUES ('opis 2', 'VERIFIED', 'Wrocław', 'Mickiewicza', 10, 51.1079, 17.0231, 1, '2020-10-10', '2020-10-10');
INSERT INTO report (description, report_status, city, street, street_number, latitude, longitude, account_id, created_date_time, updated_date_time) VALUES ('opis 3', 'NEW', 'Wrocław', 'Mickiewicza', 10, 51.1079, 17.04, 2, '2020-10-10', '2020-10-10');
