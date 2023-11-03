create database example_db;

use example_db;
create table team
(
    id varchar(3) not null primary key,
    name varchar(255) not null,
    country varchar(255) not null
);
create table player
(
    id bigint not null primary key auto_increment,
    fullName varchar(255) not null,
    position varchar(255) not null,
    team_id varchar(3) not null,

    constraint player_team_fk foreign key (team_id) references team(id)
);




