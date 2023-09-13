create table users
(
    ID            int auto_increment
        primary key,
    Name          varchar(50)  not null,
    Surname       varchar(200) not null,
    mail          varchar(30)  not null,
    CreatedAt     date         not null,
    LastUpdatedAt date         null
);


