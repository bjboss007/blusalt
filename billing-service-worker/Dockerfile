FROM openjdk:8
ADD target/*.jar billingserviceworker
EXPOSE 9098
ENTRYPOINT ["java","-jar","billingserviceworker"]