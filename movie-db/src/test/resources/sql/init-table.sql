drop table if exists movie;

create table movie (
  id varchar(255),
  name varchar(255)
);

drop table if exists info;

create table info (
      id varchar(255),
      name varchar(255),
      IMDb  DOUBLE,
      Tomatoes int
);

drop table if exists movie_lines;
create table movie_lines(
    id varchar(255),
    sentence varchar(1000),
    author varchar(50),
    line_id varchar(255)
);

drop table if exists movie_user;
create table movie_user(
    username varchar(255),
    password varchar(255),
    fullname varchar(50),
    roles varchar(50)
);
drop table if exists user_comment;
create table user_comment(
    commentID varchar(255),
    username varchar(255),
    movieID varchar(255),
    content varchar(2000)
);

drop table if exists user_like;
create table user_like(
    username varchar(255),
    id varchar(255)
);
