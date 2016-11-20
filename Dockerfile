FROM therickys93/ubuntu14java
ADD . /emergencybot
WORKDIR /emergencybot
RUN ./gradlew clean check stage
CMD /bin/bash run.sh