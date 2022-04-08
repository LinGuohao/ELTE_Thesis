FROM openjdk:11
COPY *.jar /app.jar
ADD ./wait-for-it.sh /wait-for-it.sh
CMD ["--server.port=8888"]
EXPOSE 8888
EXPOSE 9080
#ENTRYPOINT ["java","-jar","/app.jar"]