
BAZA priprema , rucno ili docker:

https://github.com/wnameless/docker-oracle-xe-11g   za bazu

docker pull wnameless/oracle-xe-11g

docker run -d -p 49160:22 -p 49161:1521 wnameless/oracle-xe-11g


create user betshop identified by "betshop!";

GRANT dba TO betshop WITH ADMIN OPTION;
