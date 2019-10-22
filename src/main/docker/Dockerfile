FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/local/deploy-view
ADD @project.build.finalName@.jar /usr/local/deploy-view/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh