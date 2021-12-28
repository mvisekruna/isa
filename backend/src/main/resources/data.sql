-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_TUTOR');
INSERT INTO ROLE (name) VALUES ('ROLE_VACATION_HOME_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_BOAT_OWNER');

INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, '2017-10-01', 'address', 'city', 'state', '060789456');
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user1@example.com', true, '2017-10-01', 'address1', 'city1', 'state1', '060123456');
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Petrovic', 'user2@example.com', true, '2017-10-01', 'address2', 'city2', 'state2', '064957864');
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Lazar', 'Lazic', 'user2@example.com', true, '2017-10-01', 'address3', 'city3', 'state3', '067989465');


INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 4);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 5);

INSERT INTO BOAT (boat_owner_id, boat_name, boat_location) VALUES (3, 'boat1', 'location1');
INSERT INTO BOAT (boat_owner_id, boat_name, boat_location) VALUES (4, 'boat2', 'location2');

INSERT INTO VACATION_HOME (vacation_home_owner_id, vacation_home_name, vacation_home_location) VALUES (3, 'vacation_home1', 'location1');
INSERT INTO VACATION_HOME (vacation_home_owner_id, vacation_home_name, vacation_home_location) VALUES (4, 'vacation_home2', 'location2');

