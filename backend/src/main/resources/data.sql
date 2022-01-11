-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_TUTOR');
INSERT INTO ROLE (name) VALUES ('ROLE_VACATION_HOME_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_BOAT_OWNER');

INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name', 'Surname', 'admin@example.com', true, '2017-10-01', 'address', 'city', 'state', '060789456', 1);

INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name1', 'Surname1', 'user1@example.com', true, '2017-10-01', 'address', 'city', 'state', '060123456', 1);
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name2', 'Surname2', 'user2@example.com', true, '2017-10-01', 'address', 'city', 'state', '060123456', 1);
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name3', 'Surname3', 'user3@example.com', true, '2017-10-01', 'address', 'city', 'state', '060123456', 1);

INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name1', 'Surname1', 'tutor1@example.com', true, '2017-10-01', 'address', 'city', 'state', '064957864', 1);
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name2', 'Surname2', 'tutor2@example.com', true, '2017-10-01', 'address', 'city', 'state', '064957864', 1);
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name3', 'Surname3', 'tutor3@example.com', true, '2017-10-01', 'address', 'city', 'state', '064957864', 1);

INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name1', 'Surname1', 'vacationHomeOwner1@example.com', true, '2017-10-01', 'address', 'city', 'state', '067989465', 1);
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name2', 'Surname2', 'vacationHomeOwner2@example.com', true, '2017-10-01', 'address', 'city', 'state', '067989465', 1);
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name3', 'Surname3', 'vacationHomeOwner3@example.com', true, '2017-10-01', 'address', 'city', 'state', '067989465', 1);

INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name1', 'Surname1', 'boatOwner1@example.com', true, '2017-10-01', 'address', 'city', 'state', '067989465', 1);
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name2', 'Surname2', 'boatOwner2@example.com', true, '2017-10-01', 'address', 'city', 'state', '067989465', 1);
INSERT INTO USERS (password, first_name, last_name, email, enabled, last_password_reset_date, address, city, state, phone_number, first_login) VALUES
('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Name3', 'Surname3', 'boatOwner3@example.com', true, '2017-10-01', 'address', 'city', 'state', '067989465', 1);


INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 2);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (7, 3);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (8, 4);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (9, 4);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (10, 4);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (11, 5);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (12, 5);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (13, 5);


INSERT INTO BOAT (boat_owner_id, boat_name, boat_location, boat_description, boat_price, boat_review) VALUES (11, 'boat1', 'location1', 'description1', '100', '3');
INSERT INTO BOAT (boat_owner_id, boat_name, boat_location, boat_description, boat_price, boat_review) VALUES (12, 'boat2', 'location2', 'description2', '200', '4');
INSERT INTO BOAT (boat_owner_id, boat_name, boat_location, boat_description, boat_price, boat_review) VALUES (13, 'boat3', 'location3', 'description3', '300', '5');
INSERT INTO BOAT (boat_owner_id, boat_name, boat_location, boat_description, boat_price, boat_review) VALUES (11, 'boat4', 'location4', 'description4', '400', '3');
INSERT INTO BOAT (boat_owner_id, boat_name, boat_location, boat_description, boat_price, boat_review) VALUES (12, 'boat5', 'location5', 'description5', '500', '4');
INSERT INTO BOAT (boat_owner_id, boat_name, boat_location, boat_description, boat_price, boat_review) VALUES (13, 'boat6', 'location6', 'description6', '600', '5');

INSERT INTO VACATION_HOME (vacation_home_owner_id, vacation_home_name, vacation_home_location, vacation_home_price, vacation_home_review) VALUES (8, 'vacation_home1', 'location1', '100', '3');
INSERT INTO VACATION_HOME (vacation_home_owner_id, vacation_home_name, vacation_home_location, vacation_home_price, vacation_home_review) VALUES (9, 'vacation_home2', 'location2', '200', '4');
INSERT INTO VACATION_HOME (vacation_home_owner_id, vacation_home_name, vacation_home_location, vacation_home_price, vacation_home_review) VALUES (10, 'vacation_home3', 'location3', '300', '5');
INSERT INTO VACATION_HOME (vacation_home_owner_id, vacation_home_name, vacation_home_location, vacation_home_price, vacation_home_review) VALUES (11, 'vacation_home4', 'location4', '400', '6');
INSERT INTO VACATION_HOME (vacation_home_owner_id, vacation_home_name, vacation_home_location, vacation_home_price, vacation_home_review) VALUES (12, 'vacation_home5', 'location5', '500', '7');
INSERT INTO VACATION_HOME (vacation_home_owner_id, vacation_home_name, vacation_home_location, vacation_home_price, vacation_home_review) VALUES (13, 'vacation_home6', 'location6', '600', '8');


INSERT INTO ADVENTURE (adventure_name, adventure_address, adventure_description, adventure_price, adventure_review, adventure_tutor_id) VALUES ('adventure1', 'address1', 'description1', '100', '3', 5);
INSERT INTO ADVENTURE (adventure_name, adventure_address, adventure_description, adventure_price, adventure_review, adventure_tutor_id) VALUES ('adventure2', 'address2', 'description2', '200', '4', 6);
INSERT INTO ADVENTURE (adventure_name, adventure_address, adventure_description, adventure_price, adventure_review, adventure_tutor_id) VALUES ('adventure3', 'address3', 'description3', '300', '5', 7);

INSERT INTO BOAT_RESERVATION (end_date,start_date,boat_id, price, user_id) VALUE ('2020-01-05 12-30', '2020-01-02 12-30', 1, 283.3,1);
INSERT INTO BOAT_RESERVATION (end_date,start_date,boat_id, price, user_id) VALUE ('2020-01-10 12-30', '2020-01-05 12-30', 1, 211.3,1);
INSERT INTO BOAT_RESERVATION (end_date,start_date,boat_id, price, user_id) VALUE ('2020-01-16 12-30', '2020-01-12 12-30', 1, 183.3,1);





