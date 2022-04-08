FROM mysql:5.7

#设置免密登录
ENV MYSQL_ALLOW_EMPTY_PASSWORD yes


COPY movie_db.sql /docker-entrypoint-initdb.d
