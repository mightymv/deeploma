Za sve docker masine (activemq, baza...) pretpostavlja da su na 192.168.99.100  ( u windowsu docker-machine ). Moze da se izmeni u application.properties fajlovima ako je neki drugi url. Naravno, ne mora da se koristi docker.

BAZA
---

 priprema , rucno ili docker:

https://github.com/wnameless/docker-oracle-xe-11g   za bazu

```bash
docker pull wnameless/oracle-xe-11g
```

```bash
docker run -d -p 49160:22 -p 49161:1521 wnameless/oracle-xe-11g
```

nakcis se na bazu i onda :

```bash
create user betshop identified by "betshop!";

GRANT dba TO betshop WITH ADMIN OPTION;
```

Model baze :


![Alt text](modeBaze.png?raw=true "model baze")

API za betingshop service 
---

 Nalazi se u paketu gde su controleri, iscitaj dok ne napravim bolje. :)
 
 port 8080

```bash
/login             - logovanje     POST
/user              - registrovanje korisnika    PUT
/offer/{date}      - ponuda po danima. {date} u formatu yyyy-mm-dd   GET
/ticket/add/       - uplata tiketa    PUT

/standings/{date}  - table za odradjeni dan . {date} u formatu yyyy-mm-dd  GET
```   

Registrovanje korisnika treba da bude u obliku (password je md5 hash) - json u body-u:

```bash 
{
"name": "Alex",
"surname": "Grkajac",
"username": "grka@email.com",
"password": "2938y9urh3h4r392u42532",
}
```

Namestio sam da svako moze da povuce ponudu, ne treba logovanje i token. Takodje , naravno, i za dodavanje usera ("/user").
Header-i koje ocekuje (prva dva pri loginu, ovaj treci pri ostalim zahtevima):

X-Auth-Username,
X-Auth-Password,
X-Auth-Token,

Primer odgovora koji se dobija na uspesni login:
```bash
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2ZWxqa29tIiwidXNlcklkIjoiNDcifQ.HMfgonEY3hRBMHgvjHOKpxrfMFgo1v2gRvLRvrUJMHiiu-gtzcBkqS9jAa_GGKtWdQ8v_99qZIQwX3MnD3chkg",
  "name": "Aleks",
  "surname": "Grkajac",
  "id": 5001
}

```

Ovde ispod ocekuje minimalno u body-u tiket koji se uplacuje (sa ovim sam testirao i prolazi mi)

```bash
{
"userId":89,
"ticketRows":[{"betOddId":100, "matchId":1001, "subGameShortName":"1", "odd":1.5},
            {"betOddId":111, "matchId":1002, "subGameShortName":"2", "odd":2.4},
            {"betOddId":123, "matchId":1003, "subGameShortName":"h1", "odd":2.3}],
"cumulativeOdd":8.28}
``` 

Treba ti id user-a za dodavanje tiketa i dobijas ga pri logovanju. Mislim da je tu ok da id igraca ide u samom api-u. (iako ga sustinski vec imam u token-u, ali pitanje je da li ti mozes tek tako to da ekstraktujes).

ERROR HANDLING
--- 

ako se desi greska, dolazi u sledecem obliku :

```bash
{
 "errorCode":"MATCH_ALREADY_STARTED"
 "localized":"Ne mozete se kladiti na zapocet mec"
 }
```
Znaci, moze da se iskoristi ovaj lokalizovani deo poruke da se prikaze korisniku.


ACTIVEMQ
---
docker:
https://github.com/rmohr/docker-activemq


pa onda :
```bash
docker pull rmohr/activemq
docker run -p 61616:61616 -p 8161:8161 rmohr/activemq
```

Trenutno je u application.properties stavljeno da gadja localhost:61616

#Pokretanje servisa :


U projektu se samo kuca  :

```bas
mvn package
```
Ako ti puca nesto u vezi putanje na linuxu , nadji na dnu pom-a taj deo, mozda je u razlici putanje windows-linux. (outputDirectory tag)

Ovo pravi jar koji ce se nalaziti na putanji "output\". Konfiguraciju "application.properties" je zapakovao u jar, a ako zelis da nesto menjas u njoj, nadji je na putanji "src/main/resources/application.properties" i prekopiraj je u "output/" direktorijum i izmeni sta treba da se izmeni. 

!!! postoje dva parametra koja prvi put moraju biti odcekirana kada prvi put pokreces servis ("application.properties") , da bi inicijalizovala bazu. Sledeci put ih vrati na staro,
```bash
#spring.datasource.schema = classpath:/sql/schema.sql
#spring.datasource.data = classpath:/sql/data.sql
```

Nakon toga pokreces servis iz "\output" direktorijuma:

```bash
java -jar bettingshop-app.jar
```
