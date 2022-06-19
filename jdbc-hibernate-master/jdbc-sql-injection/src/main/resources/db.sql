create table users (
    id int primary key AUTO_INCREMENT,
    email varchar(100) not null unique,
    password varchar(100) not null
);

insert into users (email, password) values ('kamil@gmail.com', 'secretPassword');
insert into users (email, password) values ('otherUser@gmail.com', 'unknownPassword');