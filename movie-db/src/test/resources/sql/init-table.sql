drop table if exists movie;

create table movie (
  id varchar(255),
  name varchar(255)
);

drop table if exists info;

create table info (
      id varchar(255),
      photo varchar(255),
      review varchar(255),
      OST varchar(255),
      clips varchar(255),
      details varchar(255),
      IMDb  DOUBLE,
      Tomatoes int

);
