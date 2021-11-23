CREATE TABLE PUBLIC.host_info (
    id SERIAL   PRIMARY KEY NOT NULL,
    host_name   VARCHAR NOT NULL,
    cpu_number  INTEGER NOT NULL,
    cpu_model   VARCHAR NOT NULL,
    cpu_mhz     FLOAT(3) NOT NULL,
    12_cache    INT NOT NULL,
    total_mem   INT NOT NULL,
    'timestamp' TIMESTAMP NOT NULL
    );

CREATE TABLE PUBLIC.host_usage (
    "timestamp" TIMESTAMP NOT NULL,
     host_id SERIAL NOT NULL,
     memory_free INT NOT NULL,
     cpu_idle INT NOT NULL,
     cpu_kernel INT NOT NULL,
     disk_io INT NOT NULL,
     disk_available INT NOT NULL
     );

)