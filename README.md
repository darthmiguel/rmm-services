# RMM-Services

This project relates to a Remote Monitoring and Management (RMM) company that provides the bunch of smart services in order to monitor and manage devices.


### Prerequisites

* Maven 3.0
* Java 1.8
* Postgres 10

### Init DB Script
In order to create the user and database for this project, it is necessary to execute the [script](init-db/script.sql) in the Postgres command line

```
psql -U postgres postgres -f init-db/script.sql

```

### Build

To create the jar, run the following command

```
mvn clean package
```

### Run Application

To start the application, run the following command

```
java -jar target/rmm-services.jar 
```

### Stop Application

To stop the application, just press CTRL + C


## REST Endpoints

Documentation about the endpoints can be found through swagger

```
http://localhost:8080/ninjarmm-rest/api/v2/api-docs
```

To try out the endpoints

```
http://localhost:8080/ninjarmm-rest/api/swagger-ui.html
```

### Available users
* username: emestanza, password: Erika
* username: mmarmol, password: Miguel
* username: ifaier, password: Iris

### Device Type codes

* Windows WorkStation -> 1
* Windows Server -> 2
* Mac -> 3

### Available Services

* Antivirus Windows Windows WorkStation -> 4
* Antivirus Windows Server -> 5
* Antivirus Mac -> 6
* Cloudberry Windows WorkStation -> 7
* Cloudberry Windows Server -> 8
* Cloudberry Mac -> 9
* PSA Windows WorkStation -> 10
* PSA Windows Server -> 11
* PSA Mac -> 12
* TeamViewer Windows WorkStation -> 13
* TeamViewer Windows Server -> 14
* TeamViewer Mac -> 15




