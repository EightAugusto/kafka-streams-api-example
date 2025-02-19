include .env

jar.build:
	@echo "Building '$(APPLICATION)'"
	@mvn clean install

jar.run: jar.build
	$(eval APPLICATION_JAR := $(shell ls target/*.jar | head))
	@echo "Running '$(APPLICATION_JAR)'"
	@KAFKA_BOOTSTRAP_SERVERS_CONFIG=localhost:29092,localhost:29093,localhost:29094 java -jar $(APPLICATION_JAR)

docker.network:
	@echo "Verifying/Creating '$(DOCKER_NETWORK)' Docker Network"
	@docker network inspect $(DOCKER_NETWORK) >/dev/null 2>&1 || docker network create --driver bridge $(DOCKER_NETWORK)

docker.start: jar.build docker.network
	@echo "Starting '$(APPLICATION)' in Docker"
	@docker compose --project-name $(APPLICATION) up --build --detach

docker.stop:
	@echo "Stopping '$(APPLICATION)' in Docker"
	@docker remove --force $$(docker ps --filter "label=com.docker.compose.project=$(APPLICATION)" --format '{{.ID}}')