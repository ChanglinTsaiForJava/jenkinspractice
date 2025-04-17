FROM tomcat:10.1.40-jre21

LABEL maintainer="cma@ispan.com.tw"

EXPOSE 8080/TCP
ENV TZ="Asia/Taipei"
ARG war_file=xxx.war
ADD ./${war_file} /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]