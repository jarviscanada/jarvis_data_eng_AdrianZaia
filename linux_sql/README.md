# Linux Cluster Monitoring Agent
## Introductions

The goal of this project is to create an MVP (Minimum Viable Product) as a means for a user to monitor different nodes that are connected to a Linux cluster. Then recording the information of each node (in real time) to a database that contains resoruce usage per each node.

##Quick Start
For starters, we have to create a psql instance using docker and create a container (if it has not been already made). We do this by using the following commands

```console
# Docker Creation
./scripts/psql_docker.sh create [db_username][db_password]

# Docker Startup
./scripts/psql_docker.sh start

# Docker Stop
./scripts/psql_docker stop
```


