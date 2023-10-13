## Payara configuration 
### Setup 
#### JDBC postgres
Connection pool inside `resources` tag
```xml
<jdbc-connection-pool datasource-classname="org.postgresql.ds.PGSimpleDataSource" name="Postgres" res-type="javax.sql.DataSource">
<property name="password" value="postgres" />
<property name="databaseName" value="soa" />
<property name="username" value="postgres" />
<property name="serverName" value="localhost" />
<property name="portNumber" value="5432" />
</jdbc-connection-pool>
<jdbc-resource pool-name="Postgres" jndi-name="jdbc/soa" />
```

Inside `server` tag
```xml
<resource-ref ref="jdbc/soa" />
```
### Start and deploy payara
`./asadmin start-domain <domain-name>`\
`./asadmin deploy  <path-to-war>`

### Access 
Can access service on `http://localhost:8080/spacemarine/api/space-marines`