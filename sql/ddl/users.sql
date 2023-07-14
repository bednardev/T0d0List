create table users
(
    ID            int auto_increment
        primary key,
    Name          varchar(50)  null,
    Surname       varchar(200) null,
    mail          varchar(5)   null,
    CreatedAt     date         null,
    LastUpdatedAt date         null
);