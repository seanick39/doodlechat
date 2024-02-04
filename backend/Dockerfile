FROM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml /app/pom.xml
RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn -DskipTests install

RUN mkdir -p target/dependency
WORKDIR /app/target/dependency
RUN jar -xf ../*.jar

FROM eclipse-temurin:17-jre-focal

ARG DEPENDENCY=/app/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.doodle.backend.BackendChallengeApplicationStarter"]
