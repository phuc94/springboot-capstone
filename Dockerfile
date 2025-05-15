FROM node:22-alpine AS node_base

FROM node_base AS admin_build
WORKDIR /app
COPY ./admin/ ./
RUN \
  if [ -f yarn.lock ]; then yarn --frozen-lockfile; \
  elif [ -f package-lock.json ]; then npm ci; \
  elif [ -f pnpm-lock.yaml ]; then yarn global add pnpm && pnpm i --frozen-lockfile; \
  else echo "Lockfile not found." && exit 1; \
  fi
RUN yarn build

FROM node_base AS frontstore_build
WORKDIR /app
COPY ./frontstore/ ./
RUN corepack enable
RUN yarn
RUN yarn build

FROM maven:3.9-eclipse-temurin-21-alpine AS spring_boot_build
WORKDIR /app
COPY ./springboot/pom.xml .
RUN mvn dependency:go-offline
COPY ./springboot/src ./src
COPY --from=admin_build /app/dist ./src/main/resources/static/admin
COPY --from=frontstore_build /app/dist ./src/main/resources/static/frontstore
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=spring_boot_build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

