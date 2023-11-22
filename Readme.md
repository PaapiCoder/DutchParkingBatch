# Dutch Parking application

Dutch Parking Batch application is build to generate fine report based on parked 
vehicle number and vehicle number collected during monitoring. 

## Tech stack used

1. Java - 17
2. Maven - 3.9.5
3. SpringBoot - 3.1.5

## Steps to setup

**1. Clone the application**

```bash
git clone https://github.com/PaapiCoder/DutchParkingBatch.git
```

**2. Build and run the app using maven**

```bash
cd DutchParkingBatch
mvn package
java -jar target/dutch-parking-batch-0.0.1-SNAPSHOT.jar
```

You can also run the app without packaging it using -

```bash
mvn spring-boot:run
```
