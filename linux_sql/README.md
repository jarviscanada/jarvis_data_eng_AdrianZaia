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

## Scripts

### Shell Scripts
*host_info.sh* -> This is the main script that runs on all nodes and collects data to be stored into our created PSQL instance.

*host_usage.sh* -> This script runs every minute through the use of crontab and will retrieve the specified information from every node connected to the cluster.

*crontab* -> This tool was used so that our program runs at given intervals and update the collected infromation accordingly.

### SQL Scripts

*ddl.sql* -> A simple script that creates two tables in our host_agent database. The tables were named host_info and host_usage.

*queries.sql* -> This script runs three queries and a function.

# Improvements
1. Double check my code for errors before pushing onto the github branch 
2. Make it easier for people to use my code (i.e better comments in code)
3. Compress my code so it takes up less space
