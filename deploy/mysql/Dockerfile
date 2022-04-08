FROM mysql:5.7
ENV MYSQL_ROOT_PASSWORD="movedbmysql"
CMD ["--lower_case_table_names= 1"]
COPY movie_db.sql /docker-entrypoint-initdb.d
