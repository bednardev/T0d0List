create table tasks
(
    ID            int auto_increment
        primary key,
    Title         varchar(50)  not null,
    Description   varchar(200) null,
    Color         varchar(5)   not null,
    CreatedAt     date         null,
    LastUpdatedAt date         null
    UserId        int          null;
);

