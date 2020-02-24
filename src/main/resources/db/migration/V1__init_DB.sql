create table historian (
id  bigserial primary key,
address varchar(255) not null,
email varchar(255) not null,
first_name varchar(255) not null,
last_name varchar(255) not null,
login varchar(255) not null,
phone varchar(255) not null,
specific_library_access boolean
);


