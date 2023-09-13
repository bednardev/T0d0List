create table tasks
(
    ID            int auto_increment
        primary key,
    Title         varchar(50)  not null,
    Description   varchar(200) null,
    Color         varchar(5)   not null,
    CreatedAt     date         not null,
    LastUpdatedAt date         null,
    Status        varchar(15)  not null,
    UserId        int          not null,
    constraint tasks_ibfk_1
        foreign key (UserId) references users (ID)
);

create index UserId
    on tasks (UserId);

