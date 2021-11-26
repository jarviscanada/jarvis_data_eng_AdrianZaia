#! /bin/sh

#Setup Arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check For Valid Number of Arguments
if [ $# -ne 5 ]; then
  echo "Please Input Five (5) Arguments"
  exit 1
fi

#Retrieve Specifications
hostname=$(hostname -f)

memory_free=$(vmstat --unit M| awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(vmstat --unit M| awk '{print $15}'|tail -n1| xargs)
cpu_kernal=$(vmstat --unit M| awk '{print $13}'| tail -n1| xargs)
disk_io=$(vmstat -d| awk '{print $10}'|tail -n1| xargs)
disk_available=$(df -BM| awk '{print 4}' | tail -n1| xargs)

#Find Current Time
timestamp=$(date '+%F %T')

#Query For Matching ID through host_info Table
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

#Insert Info Into Database
insert_stmt="INSERT INTO host_usage VALUES('$timestamp', $host_id, $memory_free, $cpu_idle, $cpu_kernal, $disk_io, $disk_available)"

#Exporting Password
export PGPASSWORD=$psql_password

#Insert Data into Database (Using PSQL, CLI Argument and Insert Statement)
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit 0;