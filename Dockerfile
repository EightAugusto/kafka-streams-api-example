ARG APPLICATION=kafka-streams-api-example \
    JAVA_VERSION=21

FROM docker.io/maven:3.9.9-eclipse-temurin-${JAVA_VERSION} AS jre-build

ARG APPLICATION JAVA_VERSION
COPY ./target/${APPLICATION}.jar /application/${APPLICATION}/${APPLICATION}.jar
RUN mkdir -p /application/${APPLICATION}/${APPLICATION};  \
    cd /application/${APPLICATION}/${APPLICATION}; \
    jar --extract --file /application/${APPLICATION}/${APPLICATION}.jar; \
    cd /application/${APPLICATION}; \
    jdeps --ignore-missing-deps \
        -quiet  \
        --recursive  \
        --multi-release ${JAVA_VERSION}  \
        --print-module-deps  \
        --class-path /application/${APPLICATION}/${APPLICATION}/BOOT-INF/lib/* /application/${APPLICATION}/${APPLICATION}.jar > /application/${APPLICATION}/deps.info; \
    jlink --add-modules $(cat /application/${APPLICATION}/deps.info) \
        --strip-debug \
        --compress zip-9 \
        --no-header-files \
        --no-man-pages \
        --output /opt/java/jre-${JAVA_VERSION}; \
    rm -rf /application/${APPLICATION}/${APPLICATION} /application/${APPLICATION}/deps.info;

FROM docker.io/almalinux:9.4-minimal-20240723

ARG APPLICATION JAVA_VERSION
ENV APPLICATION=${APPLICATION} \
    JAVA_HOME=/opt/java/jre-${JAVA_VERSION} \
    PATH=/opt/java/jre-${JAVA_VERSION}/bin:${PATH}
COPY --from=jre-build ${JAVA_HOME} ${JAVA_HOME}
COPY --from=jre-build /application/${APPLICATION}/${APPLICATION}.jar /application/${APPLICATION}/${APPLICATION}.jar
ENTRYPOINT java -jar ${JAVA_OPTS} /application/${APPLICATION}/${APPLICATION}.jar