DROP TABLE IF EXISTS ro_marriage_certificate;
DROP TABLE If EXISTS ro_birth_certificate;
DROP TABLE IF EXISTS ro_passport;
DROP TABLE IF EXISTS ro_person;
DROP TABLE IF EXISTS ro_address;

CREATE TABLE ro_person
(
    id bigint not null,
    sex        smallint     not null,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    patronymic varchar(100),
    date_birth date         not null,
    PRIMARY KEY (id)
);

CREATE TABLE ro_passport
(
    passport_id      SERIAL,
    person_id        integer     not null,
    series           varchar(10) not null,
    number           varchar(10) not null,
    date_issue       date        not null,
    issue_department varchar(200),
    PRIMARY KEY (passport_id),
    FOREIGN KEY (person_id) REFERENCES ro_person (id) ON DELETE RESTRICT
);
CREATE TABLE ro_address
(

)
CREATE TABLE ro_birth_certificate
(
    birth_certificate_id serial,
    number_certificate   varchar(20) not null,
    date_issue           date        not null,
    person_id            integer     not null,
    father_id            integer,
    mother_id            integer,
    PRIMARY KEY (birth_certificate_id),
    FOREIGN KEY (person_id) REFERENCES ro_person (person_id) ON DELETE RESTRICT,
    FOREIGN KEY (father_id) REFERENCES ro_person (person_id) ON DELETE RESTRICT,
    FOREIGN KEY (mother_id) REFERENCES ro_person (person_id) ON DELETE RESTRICT
);
CREATE TABLE ro_marriage_certificate
(
    marriage_certificate_id serial,
    number_certificate      varchar(20) not null,
    date_issue              date        not null,
    husband_id              integer     not null,
    wife_id                 integer     not null,
    active                  boolean DEFAULT false,
    end_date                date,
    PRIMARY KEY (marriage_certificate_id),
    FOREIGN KEY (husband_id) REFERENCES ro_person (person_id) ON DELETE RESTRICT,
    FOREIGN KEY (wife_id) REFERENCES ro_person (person_id) ON DELETE RESTRICT
);

INSERT INTO ro_person (sex, sur_name, given_name, patronymic, date_of_birth)
VALUES (2, 'Елена', 'Васильева', 'Сергеевна', '1998-03-24'),
       (1, 'Олег', 'Васильев', 'Петрович', '1997-10-16'),
       (1, 'Николай', 'Васильев', 'Олегович', '2018-10-21');
INSERT INTO ro_passport(person_id, series, number, issue_date, issue_department)
VALUES (1, '40000', '123456', '2018-04-10', 'Department passport');

INSERT INTO ro_birth_certificate (number_certificate, issue_date, person_id, mother_id, father_id)
VALUES ('123 Birth', '2018-1-01', 3, 1, 2);
INSERT INTO ro_address (apartment, building, extension, street_code, person_id)
VALUES ('34', '2', '1', '1', 1);
INSERT INTO ro_marriage_certificate (active, end_date, issue_date, number_certificate, husband_id, wife_id)
VALUES (true, '2020-9-12', '2008-1-01', '4568392964', 2, 1)
