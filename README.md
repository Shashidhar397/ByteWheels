# ByteWheels
## Setup
Project requires following to run:
1. Tomcat server
2. Mysql
3. Maven

### 1. Configuring tomcat server for jndi lookup
1. Edit server.xml add following datasource:
```
<Resource name="jdbc/byteWheels" 
      global="jdbc/byteWheels" 
      auth="Container" 
      type="javax.sql.DataSource" 
      driverClassName="com.mysql.jdbc.Driver" 
      url="jdbc:mysql://localhost:3306/bytewheels" 
      username="root" 
      password="" 
      
      maxActive="100" 
      maxIdle="20" 
      minIdle="5" 
      maxWait="10000"/>
```
2. Edit context.xml add following for adding datasource:
```
<ResourceLink name="jdbc/byteWheelsDB"
                global="jdbc/byteWheels"
                auth="Container"
                type="javax.sql.DataSource" />
```

### 2. Clone or Download Code from Dev branch:
### 3. Build and Deploy
1. open cmd prompt move to parent pom in ByteWheels project which is in ByteWheels folder run following command:
```
mvn clean install

```
2. Go to target folder of ByteWheelsWebService copy war file and deploy in Tomcat by copying to webapps folder.

### 4.Project contains both UI and Rest : Rest is as follows:
#### 1. listing vehicles based on date:
```
method : POST url : http://localhost:8081/ByteWheels/listVehicles.do  
```
Request json :
```
{
	"rentingDateFrom" : "2018-04-04",
	"rentingDateTo" : "2018-04-04"
}

```
Date has to be in yyyy-MM-dd format.
#### 2. listing vehicles based on date and category filter:
```
method : POST url : http://localhost:8081/ByteWheels/listVehicles.do  
```
Request Json:
```
{
	"rentingDateFrom" : "2018-04-04",
	"rentingDateTo" : "2018-04-04",
	"category" 	  : "compact"
}
```
#### 3. listing vehicles based on date ,category filter and cost filter:
```
method : POST url : http://localhost:8081/ByteWheels/listVehicles.do  
```
Request Json:
```
{
	"rentingDateFrom" : "2018-04-04",
	"rentingDateTo" : "2018-04-04",
	"category" 	  : "compact",
	"costFrom"	  : 10,
	"costTo"	  : 30 
}
```

Response Sample:
```
{
    "availableDate": "2018-04-04 - 2018-04-04",
    "vehicles": [
        {
            "carName": "Ford Fiesta",
            "carType": "COMPACT",
            "carRentCost": 20,
            "availableCars": 2
        }
    ],
    "returnCode": 0,
    "returnMessage": "SUCCESS"
}
```
#### 4. Book vehicle by providing carName ,email Id, from date and to date :

```
method : POST url : http://localhost:8081/ByteWheels/bookVehicle.do  
```
Request Json:
```
{
  "carName" : "Chevrolet Malibu",
  "fromDate" : "2018-04-30",
  "toDate"   : "2018-05-01",
  "userEmailId" : "23sdf@gmail.com"
}
```
Confirmation Email will be sent to corresponding email id.
But for the reason of certificate issue Antivirus needs to disable mail protection.

Response Sample:
```
{
    "returnCode": 0,
    "returnMessage": "SUCCESS",
    "invoiceId": 32,
    "carId": "5",
    "carName": "Chevrolet Malibu",
    "cost": 60,
    "fromDate": "2018-04-30",
    "toDate": "2018-05-01",
    "userEmailId": "23sdf@gmail.com"
}
```
### 5. UI 
In the browser browse for the url : http://localhost:8081/ByteWheels/#/login
![alt text](https://raw.githubusercontent.com/username/projectname/branch/path/to/img.png)
