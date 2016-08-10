
BAZA priprema , rucno ili docker:

https://github.com/wnameless/docker-oracle-xe-11g   za bazu

docker pull wnameless/oracle-xe-11g

docker run -d -p 49160:22 -p 49161:1521 wnameless/oracle-xe-11g


create user betshop identified by "betshop!";

GRANT dba TO betshop WITH ADMIN OPTION;


API ti se nalazi u paketu gde su controleri, iscitaj dok ne napravim bolje. :)


/login

/offer/2016-8-10

/ticket/add/     ovde ocekuje u body-u tiket koji se uplacuje




header-i koje ocekuje (prva dva pri loginu, ovaj treci pri ostalim zahtevima):

X-Auth-Username,
X-Auth-Password,
X-Auth-Token


za sada trazim token pri svemu sto postoji, ali probacu a kasnije da skinem za offer, ili slicno....

login ce ti trenutno proci sta god da otkucas, i taj token posle koristi za ostale pozive (mada nema mnogo ostalog :) ))

u tokenu postoji username i id koji mogu da se iscupaju, dacu ti kod kako da to izvadis iz njega, da dekodujes.
treba ti id user-a za dodavanje tiketa. Mislim da je tu ok da id igraca ide u samom api-u. (iako ga sustinski vec imam u token-u).
