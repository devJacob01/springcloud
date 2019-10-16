FROM java:8-jre 

ENV VERTICLE_FILE sample-service6-0.0.1-SNAPSHOT.jar
# Set the location of the verticles
ENV VERTICLE_HOME /usr/verticles

# Add an docker user, make work dir
RUN adduser --disabled-password --gecos "" docker && \
  echo "docker ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers && \
  mkdir -p $VERTICLE_HOME && \
  chown docker:docker $VERTICLE_HOME

EXPOSE 80

WORKDIR $VERTICLE_HOME

# Copy your fat jar to the container
COPY ./sample-service6/target/sample-service6-0.0.1-SNAPSHOT.jar $VERTICLE_HOME/

# Launch the verticle
ENTRYPOINT ["sh", "-c"]

# RUN as docker user
USER docker
CMD ["exec java -jar $VERTICLE_FILE"]
