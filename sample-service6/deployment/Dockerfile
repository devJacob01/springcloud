FROM java:8
# Add an docker user, make work dir
RUN adduser --disabled-password --gecos "" docker && \
  echo "docker ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers && \
  mkdir -p /home/docker/app && \
  chown docker:docker /home/docker/app

WORKDIR /home/docker/app

COPY . ./


# RUN as docker user
USER docker
CMD java -jar target/sample-service6.jar