# Install Tomcat & openjdk 8 (openjdk has java and javac)
FROM tomcat:jdk8-openjdk

# Copy source files to tomcat folder structure
COPY src/main/webapp/ /usr/local/tomcat/webapps/ROOT/
COPY src/main/java/cl/playground/triggersapp/servlets/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/cl/playground/triggersapp/servlets/

# Compile the servlets
RUN ["javac", "-cp", ".:/usr/local/tomcat/lib/servlet-api.jar", "-d", "/usr/local/tomcat/webapps/ROOT/WEB-INF/classes/", "/usr/local/tomcat/webapps/ROOT/WEB-INF/classes/cl/playground/triggersapp/servlets/HelloServlet.java"]

# Serve Tomcat
EXPOSE 8080
CMD ["catalina.sh", "run"]
