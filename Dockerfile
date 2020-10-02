FROM adoptopenjdk:8-jre-openj9

ADD build/libs/*.jar exchangeRate.jar

ENTRYPOINT ["java", "-jar", "exchangeRate.jar"]
