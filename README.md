# ProiectAndroidRetroFit
# MANOLE MIHAELA OTILIA - GRUPA 1103

Am creat o aplicatie mobila bancara care isi propune sa usureze procesul bancar cu accent pe crearea depozitelor si creditelor.




FLOW DATE, exemplificate si in screenshoturile de mai jos:

-Utilizatorul se logheaza ( dupa ce se inregistreaza), datele sunt salvate in baza de date locala DAO
-Utilizatorul ajunge in fereastra principala ( meniu)
-Posibilitate de creeare credite, selecteaza suma, dobanda si perioada creditului
-Creditele se memoreaza intr-o baza de date si se afiseaza din baza de date locala, se pot gasi in tabul lista credite 
-In lista credite se pot selecta si transforma si intr-o alta moneda
-Depozitele se creaza similar creditelor
-Avem o lista a depozitelor si o afisare pentru baza de date a depozitelor
-Conversie in alta moneda pentru depozite
-Se poate verifica programul bancilor din tabul sarbatorilor unde se selecteaza anul si condul tarii


Screenshoturi aplicatie:

![image](https://user-images.githubusercontent.com/84033800/117866639-08f94f00-b2a0-11eb-8b65-b504f8cad236.png)

![image](https://user-images.githubusercontent.com/84033800/117866751-29290e00-b2a0-11eb-876d-321980f38c4b.png)

![image](https://user-images.githubusercontent.com/84033800/117866956-65f50500-b2a0-11eb-961c-d01b6f4feeb8.png)

![image](https://user-images.githubusercontent.com/84033800/117867049-82913d00-b2a0-11eb-8390-f6abf0aabbaf.png)

![image](https://user-images.githubusercontent.com/84033800/117867154-a81e4680-b2a0-11eb-9e88-de9e4cd99447.png)

![image](https://user-images.githubusercontent.com/84033800/117867215-bec49d80-b2a0-11eb-88b6-bdd6e2e07bb2.png)

![image](https://user-images.githubusercontent.com/84033800/117867249-cc7a2300-b2a0-11eb-88e2-9c60d2c78a16.png)

![image](https://user-images.githubusercontent.com/84033800/117867352-efa4d280-b2a0-11eb-9288-f5273f327169.png)

![image](https://user-images.githubusercontent.com/84033800/117867413-0519fc80-b2a1-11eb-81b2-a35b7df5c61a.png)

![image](https://user-images.githubusercontent.com/84033800/117867495-1f53da80-b2a1-11eb-8194-c4b886ea9885.png)



Exemple de Request/Response:
1.API ExchangeRates
REQUEST:
http://api.exchangeratesapi.io/v1/latest?access_key=7f30e94e231f48989de394259d384855&base=EUR&symbols=GBP,RON,TWD,USD
RESPONSE:
{"success":true,"timestamp":1620755763,"base":"EUR","date":"2021-05-11","rates":{"GBP":0.858959,"RON":4.927237,"TWD":33.96672,"USD":1.215709}}

2.API Legal Holidays
REQUEST:
https://public-holiday.p.rapidapi.com/2020/RO
RESPONSE:
![image](https://user-images.githubusercontent.com/84033800/117868204-fe3fb980-b2a1-11eb-979d-8eb1b8ae4170.png)
![image](https://user-images.githubusercontent.com/84033800/117868450-4c54bd00-b2a2-11eb-8940-506a1a5f0c4e.png)


METODE HTTP

![image](https://user-images.githubusercontent.com/84033800/117868709-95a50c80-b2a2-11eb-9549-3e3de22e7c35.png)
![image](https://user-images.githubusercontent.com/84033800/117868803-b2d9db00-b2a2-11eb-91ec-c3bb0bedf948.png)
![image](https://user-images.githubusercontent.com/84033800/117868868-c5541480-b2a2-11eb-852a-5c875d4d1bd0.png)
![image](https://user-images.githubusercontent.com/84033800/117869073-02b8a200-b2a3-11eb-927d-d5acdecf5232.png)



