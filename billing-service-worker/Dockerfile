FROM openjdk:11
ADD target/*.jar billingserviceworker
EXPOSE 9098
ENTRYPOINT ["java","-jar","billingserviceworker"]