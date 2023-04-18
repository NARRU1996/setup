FROM ubuntu
ADD  https://dlcdn.apache.org/maven/maven-3/3.9.1/binaries/apache-maven-3.9.1-bin.tar.gz   /bin/
RUN tar -xJf /usr/src/things/apache-maven-3.9.1-bin.tar.gz -C /bin/
RUN make -C /bin/ all
ENTRYPOINT ["./bin/apache-maven-3.9.1-bin/mvn"]
CMD ["--version"]
