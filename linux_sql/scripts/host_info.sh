#! /bin/sh

#Argument Setup
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5


#Check Arguments
if [ $# -ne 5 ]; then
  echo 'Enter Valid Number of Argument'
  exit 1
fi

#Store Variables
vmstat_mb=$(vmstat --unit M)
lscpu_out=`lscpu`



#Hardware Specs
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out" |grep -E "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" |grep -E "^CPU Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" |grep -E "^CPU Model:" | awk '{print $3 $4 $5}' | xargs)
cpu_mhz=$(echo "$lscpu_out" |grep -E "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out" |grep -E "^L2 Cache:" | awk '{print $3}' | xargs)
total_mem=$(grep -E MemTotal /proc/meminfo | awk '{print $2 $3}' | xargs)
timestamp=$(date +"%Y-%m-%d %H:%M:%S")


#Insert Data
insert_stmt="INSERT INTO host_info VALUES('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp')"

#Enviornment Setup
export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "$insert_stmt"
exit 0;