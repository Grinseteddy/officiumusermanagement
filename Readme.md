# Officium
## User Management
This software is designed to create a light weight task management application.
The software is designed as complete microservice architecture.

The according service is the user management service. It provides CRUD methods
to users, who can be assigned to taks or who are owner of projects or tasks. 
Please have a look into interfaceUserManagement.yaml.

This is the user management microservice of the Officium application.
The microservices accesses a postgreSQL database - offered in AWS.
A user can be determined by its UUID or its user name.
A user can be created by its user name, e-mail address, and name and surname. Please refer to the
according OpenAPI description available under

`com/annegret/officium/usermangement/Interfaces/interfaceUserManagement.yaml`

## Installation
The microservices is supposed to be installed in AWS (with according connection 
string to the database). Anyhow it is delivered as docker container with 

`docker run -p 5050:5050 annegret/usermanagement`

In AWS, the microservice is running as daemon.

## Further Microservices

The entire Officium application needs more microservices. The following microservices
are available at the moment:
* Task Management https://github.com/Grinseteddy/officiumTaskmanagement
* Project Management https://github.com/Grinseteddy/officiumProjectmanagement
* Login https://github.com/Grinseteddy/officiumlogin
* Comments (not yet available on Github)

As client, one iOS client is available:
 * https://github.com/Grinseteddy/officium2ios