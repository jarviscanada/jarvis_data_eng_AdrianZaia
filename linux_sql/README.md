# Linux Cluster Monitoring Agent
## Introductions

The goal of this project is to create an MVP (Minimum Viable Product) as a means for a user to monitor different nodes that are connected to a Linux cluster. Then recording the information of each node (in real time) to a database that contains resoruce usage per each node.

## Quick Start
For starters, we have to create a psql instance using docker and create a container (if it has not been already made). We do this by using the following commands

```console
# Docker Creation
./scripts/psql_docker.sh create [db_username][db_password]

# Docker Startup
./scripts/psql_docker.sh start

# Docker Stop
./scripts/psql_docker stop
```

Once we have created a PSQL the instance, we want to startup the postgres database
```Console
# Postgres Install
sudo yum install -y postgresql

# Export Password
export PGPASSWORD = 'password'

# Connect To Database
psql -h HOST_NAME -U USER_NAME -d DB_NAME -W
```

## Implementation
This project was implemented using Docker and creating containers within Docker to store information. On top of this, Bash scripting was used to make the function automated and is being triggered every minute through the use of crontab

### Scripts


