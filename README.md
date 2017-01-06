# WeatherApi
Weatherpi

It includes  two parts :

1)UI part:
   a)It consists of simple UI page .User Inputs for UI are : "City","latitude" and "longitude".
   b)After user Inputs two server side calls are made:
           b.1)Min Max temperature with timestamps
           b.2)Graph for temperature variation with time.
           
 2)Server side :
         a)Two Rest Resources are there:
              I am  using two clients for weather api:
                   a.1)Open weather Maps(https://openweathermap.org/)
                   a.2)Dark sky Api(https://darksky.net/dev/)
                   
  Some other points:
       Cors filter for cross network communication.
       Tokens are needed for working  of these apis.Configuration can be changed through these properties file.
       
       Process of generating token for (https://openweathermap.org/)
         Once we create login a key is generated which is appId.
         
         Same process for (https://darksky.net/dev/)
         
         
         
  3)Steps for running application :
  
   Deploy the application in tomcat or local machine .Once deployed , we can execute  \weatherUIClient.html
   which internally make two server calls and renders data .
   
   *Note :  weatherUIClient.htm will have http://localhost:8080 at client calls . In case you are deploying war in different machine and running html 
   elsewhere , we need to change IP and port respectively in html files 
   
   
   ##Test Data Example
   City : Delhi 
   Latitude : 43.5
   Longitude:-91
   
     