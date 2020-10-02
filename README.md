# exchangerate

This service acts like exchange rate stub. It mocks the real world currency changes and send those changes as events, so that all services listening to them can update their local stores. You can use it as standalone docker container. It is part of bigger project [teammanager](https://github.com/amasiakiewicz/teammanager). Teammanager also offers docker-compose config for it. Please build it before use by firing command in cloned directory:
```
./gradlew clean build
```
