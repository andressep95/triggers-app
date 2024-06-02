# Install Tomcat & openjdk 8 (openjdk has java and javac)
FROM tomcat:jdk8-openjdk

# Copy source files to tomcat folder structure
COPY src/main/webapp/ /usr/local/tomcat/webapps/ROOT/
COPY src/main/java/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Compile all Java files
RUN find /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/ -name "*.java" -exec javac -cp ".:/usr/local/tomcat/lib/servlet-api.jar" -d /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/ {} +

# Serve Tomcat
EXPOSE 8080
CMD ["catalina.sh", "run"]
