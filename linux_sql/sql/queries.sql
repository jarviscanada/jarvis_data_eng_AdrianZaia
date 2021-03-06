select
	cpu_number,
	id,
	total_mem
from
	host_info
order by
	sum(total_mem) over (partition by cpu_number)
    
/* Query 2 */
select
	host_id,
	host_name,
	date_trunc('hour', timestamp) + date_part('minute', timestamp)
	avg(
		( total_mem/1000) - memory_free
	) as used_memory
from
	host_usage
	join host_info
group by
	host_id,
	hostname,
	"timestamp";

/*round5 function */
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

/* Query 3 */
select
	host_id,
	round5(time_stamp) as round5_interval,
	count (*) as data
from
	host_usage
group by
	round5_interval,
	host_id
having
	count(*) < 3
order by
	round5(time_stamp);

