Pokretanje servisa :

U projektu se samo kuca :

```bas
mvn package
```
Ako ti puca nesto u vezi putanje na linuxu , nadji na dnu pom-a taj deo, mozda je u razlici putanje windows-linux. (outputDirectory tag)

Ovo pravi jar koji ce se nalaziti na putanji "output\". Konfiguraciju "application.properties" je zapakovao u jar, a ako zelis da nesto menjas u njoj, nadji je na putanji "src/main/resources/application.properties" i prekopiraj je u "output/" direktorijum i izmeni sta treba da se izmeni. 

Nakon toga pokreces servis iz "\output" direktorijuma:

```bash
java -jar reco-app.jar
```


MongoDB
------

mongo instalacija rucno ili kroz docker: 
https://hub.docker.com/_/mongo/

```bash
docker pull mongo

a startovanje mongo-a

docker run --name mongo-instanca -p 27017:27017 -d mongo
```

Preko MongoBooster-a se nakaciti i odraditi sledece kreiranje index-a:
```bash
db.userBehavs.createIndex( { "time": 1 }, { expireAfterSeconds: 3600 } )
```
Mongo replica set 
```bash

startovanje : mongod --port 27021 --dbpath "c:\mongodb\m2" --replSet rs0

rs.initiate({ _id: "my-replica-set", members:[
          { _id : 0, host : "192.168.42.100:27017"},
          { _id : 1, host : "192.168.42.110:27017"},
          { _id : 2, host : "192.168.42.120:27017"} ]
         });
```
API 
-----

Za sada postoji dovlacenje tiketa za odredjenog user-a
```bash
x.x.x.x:8082/{userId}/tickets   GET  
```

Pojedinacni tiket za nekog igraca :

```bash
x.x.x.x:8082/{ticketId}/{userId}/tickets   GET   
```


Preporuke za odredjenog igraca : 

```bash
x.x.x.x:8082/{userId}/recommendation   GET   
```
