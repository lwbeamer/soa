#!/bin/bash
scp -r dist/* helios:/home/studs/s312200/public_html/soa-frontend
scp -r ~/Documents/GitHub/soa/spacemarine/target/spacemarine.war helios:~/soa
scp -r ~/Documents/GitHub/soa/second-service/target/starship.war helios:~/soa
scp -r ~/Documents/GitHub/soa/auth-service/target/auth-service-0.0.1-SNAPSHOT.jar helios:~/soa/
ssh helios '$WILDFLY_HOME/bin/jboss-cli.sh --connect --command=:shutdown && $PAYARA_HOME/glassfish/bin/asadmin stop-domain --force domain1 && $PAYARA_HOME/glassfish/bin/asadmin deploy --name spacemarine --contextroot /api/v1/spacemarines  ~/soa/spacemarine.war && $WILDFLY_HOME/standalone.sh && $WILDFLY_HOME/bin/jboss-cli.sh --connect --command="deploy deploy ~/soa/starship.war" '
