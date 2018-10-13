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