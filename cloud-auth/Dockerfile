FROM jboss/keycloak
COPY realm-export.json /tmp
ENV KEYCLOAK_IMPORT=/tmp/realm-export.json
ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin
