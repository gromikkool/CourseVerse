
FROM tomcat:9-jre10
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY controller/target/controller-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
ADD wait-for-it.sh .
RUN chmod ugo+x wait-for-it.sh
CMD ["./wait-for-it.sh", "mysql:3306", "-t", "45", "--", "catalina.sh","run"]
