#######  BUILDER  #######  
# temp container to build using gradle
FROM gradle:5.3.0-jdk-alpine AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    
RUN gradle build || return 0
COPY . .
RUN gradle clean build
    



## alpine linux with JRE 
FROM docker pull eclipse-temurin:21-jre-alpine 

ENV ARTIFACT_NAME=tournament-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

## create a nonroot user and group 
RUN addgroup -S spring && adduser -S spring -G spring 
 
## copy the spring jar 
#COPY target/*.jar /opt/myApp.jar 

WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME  /opt/app.jar 


## set the nonroot user as the default user 
USER spring:spring 
 
## set the working directory 
WORKDIR /opt 
 
ENTRYPOINT ["java", "-jar", "app.jar"] 
 
## expose the port to the external world 
EXPOSE 8080 