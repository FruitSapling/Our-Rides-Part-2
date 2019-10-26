# Willem van Gerwen BookingGo Technical Test Submission

## Setup
This project is a Spring-Boot application, with Jackson as a dependency (via pom.xml, not an external jar like in part 1).

## Part 1

My work for Part 1 is in another Github Repository: https://github.com/FruitSapling/Our-Rides-Part-1

## Part 2

A jar can be compiled by, in a console, navigating to the project folder (/OurRides), and running `mvn clean package`.

The output jar is located at OurRides/target/demo-0.0.1-SNAPSHOT.jar.

`java -jar demo-0.0.1-SNAPSHOT.jar`

Then the API can be accessed, for example, at:
`http://localhost:8080/rides?pickupLat=1.0&pickupLong=2.03&dropoffLat=2.0&dropoffLong=2.0&numPassengers=13`

And a JSON payload will be returned.
