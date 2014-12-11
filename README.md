simple-spring
=============

very simple app to be able to duplicate issue where JTA transactions are not working properly (config?) in Wildfly

This sample expects the following mysql configuration on localhost
Database named test
User test with password password and permissions to test database

To run
Download sample configured wildfly https://www.dropbox.com/s/t2siyh5db5xb8i0/wildfly-8.2.0.Final-sample.zip?dl=0
Unzip and cd to directory
Run ./bin/standalone.sh -c standalone-full.xml
Deploy this sample app to wildfly with the following mvn clean package wildfly:deploy

Then open browser and enter the following
http://localhost:8080/simple-spring/rest/movies/100

Within the wildfly console wait for the processing to complete and then search for "broken". 100 items should normally be enought to break but higher valules of 500, 5000, or 10000 may be required.