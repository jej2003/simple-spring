simple-spring
=============

very simple app to be able to duplicate issue where JTA transactions are not working properly (config?) in Tomee

Download configured sample TomEE https://www.dropbox.com/s/0kefpndme1x9v3p/apache-tomee-plus-1.7.1-sample.zip?dl=0
Build project with mvn clean package
Deploy sample-spring.war to TomEE webapps directory
Start tomee with ./bin/catalina.sh run

Then open browser and enter the following
http://localhost:8080/simple-spring/rest/movies/100

Within the tomee console wait for the processing to complete and then search for "broken". 100 items should normally be enought to break but higher valules of 500, 5000, or 10000 may be required.

tomee.xml
=========

<?xml version="1.0" encoding="UTF-8"?>
<tomee>
  <!-- see http://tomee.apache.org/containers-and-resources.html -->
  
  <Resource id="ActiveMQResourceAdapter" type="ActiveMQResourceAdapter">
      BrokerXmlConfig=broker:(vm://localhost)
  </Resource>

<!-- see http://tomee.apache.org/containers-and-resources.html -->
  <Resource id="resources/jms/ConnectionFactory" type="javax.jms.ConnectionFactory">
     ResourceAdapter = ActiveMQResourceAdapter
  </Resource>

<Resource id="resources/jms/XAConnectionFactory" class-name="org.apache.activemq.ActiveMQXAConnectionFactory">
	BrokerURL = vm://localhost
    ResourceAdapter = ActiveMQResourceAdapter
</Resource>

<Resource id="testxa" class-name="com.mysql.jdbc.jdbc2.optional.MysqlXADataSource">
   Url jdbc:mysql://localhost:3306/test
   User root
</Resource>

<Resource id="movieDatabase" type="DataSource">
   XaDataSource testxa
   DataSourceCreator dbcp
   UserName root
</Resource>

<Resource id="resources/jms/PrintQueue" type="javax.jms.Queue"/>
<Resource id="resources/jms/PersistQueue" type="javax.jms.Queue"/>

  <!-- activate next line to be able to deploy applications in apps -->
  <Deployments dir="apps" />
</tomee>
