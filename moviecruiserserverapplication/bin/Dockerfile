FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABSE=moviecruiser
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviecruiser
ADD ./target/moviecruiserserverapplication-0.0.1-SNAPSHOT.jar /usr/src/moviecruiserserverapplication-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","moviecruiserserverapplication-0.0.1-SNAPSHOT.jar"]