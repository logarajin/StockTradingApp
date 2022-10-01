FROM logaraja/jdktomcat:latest
MAINTAINER Raja Jaganathan <raja.jaganathan@wipro.com>
ADD tomcat-users.xml /opt/apache-tomcat-8.5.27/conf/
ADD context.xml /opt/apache-tomcat-8.5.27/webapps/manager/META-INF/
ADD *.war /opt/apache-tomcat-8.5.27/webapps/
EXPOSE 8080
