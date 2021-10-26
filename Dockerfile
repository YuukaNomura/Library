FROM tomcat:9.0-jdk16

ARG VERSION
COPY ./src/main/webapp/META-INF/maven/library/library/target/library-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/library.war

CMD ["catalina.sh", "run"]

#CMD [ "sh", "-c", "java $JAVA_OPTIONS -jar /root/library.jar" ]

