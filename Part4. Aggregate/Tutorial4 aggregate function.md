# Aggregate Functions

> Designer : ZHU Yueming in March 15th 2021

## Experimental-Objective

1. To learn how to use some aggregate function along with `distinct, group by` and `having`.

## Part 1.Queries with aggregate functions

>As the name says, aggregate function will aggregate all rows that share a feature (such as being films from the same country) and return a characteristic of each group of aggregated rows. It will be clearer with an example.

#### <font color=blue>(1) count(*)	</font>

##### aggregate functions ignore Nulls.

Example:

```sql
select count(*) - count(s.latitude) as null_latitude_column
from stations s;
```

All aggregate functions ignore NULLs, so result of count(*) may be different from count([column]).

##### round() and trunc()

Function round will  save a few decimal places.  Also you can use trunc(). 

- round( 3.141592, 3)  result is 3.142
- trunc( 3.141592, 3)  result is 3.141

#### <font color=blue>(2) max(), min(),  avg() </font>

Those are aggregate functions that would be return only one row as the specific executed result of the whole table. 

Example: 

```sql
select max(latitude) from stations s;
```

#### <font color=blue>(3) group by </font>

Usually executed with aggregate functions, and for each value of the group by column, those aggregate functions would return the corresponding result.

Example:

```sql
select s.district, max(latitude) from stations s group by s.district;
```

#### <font color =blue>(4) having </font>

`Having` is like a filter condition acted on the result set of  ```group by``` . 

Grammer:

```sql
select ...
		from ...
where ...
group by ...
having ...
```

Example:

```sql
select count(*), s.district
from stations s
where s.latitude is not null
group by s.district
having count(*) > 30
```

| count | district |
| ----- | -------- |
| 50    | Futian   |
| 38    | Nanshan  |

#### <font color=blue>(5) distinct	 </font>

Dumplicated values can not be existed in distincted columns. 

Example:

```sql
select distinct district from stations;
```
