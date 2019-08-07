FROM openjdk:11

WORKDIR /user/src

ADD ./target/track-service-0.0.1-SNAPSHOT.jar /user/src/track-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","track-service-0.0.1-SNAPSHOT.jar"]