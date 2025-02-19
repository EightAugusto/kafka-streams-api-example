ARG APPLICATION="kafka-streams-api-example" JDK_VERSION="21"

FROM docker.io/eclipse-temurin:${JDK_VERSION} AS jre
ARG APPLICATION JDK_VERSION
COPY ./target/${APPLICATION}.jar /application/${APPLICATION}.jar
RUN mkdir -p /application/${APPLICATION} &&  \
    cd /application/${APPLICATION} && \
    jar --extract --file /application/${APPLICATION}.jar && \
    cd /application/${APPLICATION} && \
    jdeps --ignore-missing-deps \
        -quiet  \
        --recursive  \
        --multi-release ${JDK_VERSION}  \
        --print-module-deps  \
        --class-path /application/${APPLICATION}/BOOT-INF/lib/* /application/${APPLICATION}.jar > /application/${APPLICATION}/deps.info && \
    jlink --add-modules $(cat /application/${APPLICATION}/deps.info) \
        --strip-debug \
        --compress zip-9 \
        --no-header-files \
        --no-man-pages \
        --output /opt/java/jre-${JDK_VERSION} && \
    rm -rf /application/${APPLICATION} /application/${APPLICATION}/deps.info;

FROM docker.io/debian:bookworm-slim
ARG APPLICATION JDK_VERSION
COPY --from=jre /opt/java/jre-${JDK_VERSION} /opt/java/jre-${JDK_VERSION}
COPY --from=jre /application/${APPLICATION}.jar /application/${APPLICATION}.jar
ENTRYPOINT /opt/java/$(ls /opt/java | grep jre | head)/bin/java -jar ${JAVA_OPTS} /application/$(ls /application | grep .jar | head)