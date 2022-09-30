CREATE or replace TABLE movie (id bigint primary key, title VARCHAR(50), description VARCHAR(100), running_time int, ticket_price numeric(10, 2), special_code int)
create or replace table showing(sequence int, start_date date, start_time time, id bigint, foreign key(id) references movie(id) )


insert into movie(id, title,description,running_time,ticket_price,special_code) values(1, 'Spider-Man: No Way Home', 'movie1', 90, 12.5, 1)
insert into movie(id, title,description,running_time,ticket_price,special_code)  values(2, 'Turning Red', 'movie2', 85, 11, 0)
insert into movie(id, title,description,running_time,ticket_price,special_code)  values(3, 'The Batman', 'movie3', 95, 9, 0)

insert into showing(sequence, start_date, start_time, id) values(2, current_date(), PARSEDATETIME('11:00', 'HH:mm'), 1)
insert into showing(sequence, start_date, start_time, id) values(3, current_date(), PARSEDATETIME('12:50', 'HH:mm'), 3)
insert into showing(sequence, start_date, start_time, id) values(4, current_date(), PARSEDATETIME('14:30', 'HH:mm'), 2)
insert into showing(sequence, start_date, start_time, id) values(5, current_date(), PARSEDATETIME('16:10', 'HH:mm'), 1)
insert into showing(sequence, start_date, start_time, id) values(6, current_date(), PARSEDATETIME('17:50', 'HH:mm'), 3)
insert into showing(sequence, start_date, start_time, id) values(7, current_date(), PARSEDATETIME('19:30', 'HH:mm'), 2)
insert into showing(sequence, start_date, start_time, id) values(8, current_date(), PARSEDATETIME('21:10', 'HH:mm'), 1)
insert into showing(sequence, start_date, start_time, id) values(9, current_date(), PARSEDATETIME('23:00', 'HH:mm'), 3)