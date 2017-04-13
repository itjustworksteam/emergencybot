FROM therickys93/alpinejava
ADD . /emergencybot
WORKDIR /emergencybot
RUN ./gradlew clean check stage
CMD /bin/bash run.sh