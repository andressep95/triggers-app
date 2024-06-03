# Install Tomcat & openjdk 8 (openjdk has java and javac)
FROM tomcat:jdk8-openjdk

# Download the PostgreSQL driver
ADD https://jdbc.postgresql.org/download/postgresql-42.2.20.jar /usr/local/tomcat/lib/
# Download the Redis client library (Jedis)
ADD https://repo1.maven.org/maven2/redis/clients/jedis/3.5.2/jedis-3.5.2.jar /usr/local/tomcat/lib/
# Download the SLF4J library (required by Jedis)
ADD https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar /usr/local/tomcat/lib/
ADD https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.30/slf4j-simple-1.7.30.jar /usr/local/tomcat/lib/

# Copy source files to tomcat folder structure
COPY src/main/webapp/ /usr/local/tomcat/webapps/ROOT/
COPY src/main/java/cl/playground/triggersapp/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/cl/playground/triggersapp/
COPY src/main/resources/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Compile the Java classes
RUN find /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/cl/playground/triggersapp/ -name "*.java" -exec javac -cp ".:/usr/local/tomcat/lib/servlet-api.jar:/usr/local/tomcat/lib/postgresql-42.2.20.jar:/usr/local/tomcat/lib/jedis-3.5.2.jar:/usr/local/tomcat/lib/slf4j-api-1.7.30.jar:/usr/local/tomcat/lib/slf4j-simple-1.7.30.jar" -d /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/ {} +

EXPOSE 8080
CMD ["catalina.sh", "run"]
