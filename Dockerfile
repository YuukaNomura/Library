FROM openjdk:11

COPY ./src/main/webapp/META-INF/maven/library/library/target/library-0.0.1-SNAPSHOT.jar /root/library.jar

CMD [ "sh", "-c", "java $JAVA_OPTIONS -jar /root/library.jar" ]
