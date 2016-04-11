# Baltimore Parking Ticket Heat Map

## Overview of Project
The inspiration behind this application was for me to learn more about spring-boot and Angular.  The dataset used is provided by the [OpenBaltimore Initiative](https://data.baltimorecity.gov/). The dataset can be found on [OpenBaltimore](https://data.baltimorecity.gov/Transportation/Parking-Citations/n4ma-fj3m) and was accessible via Socrata's [java library](https://github.com/socrata/soda-java)(SODA) which called upon their [API](https://dev.socrata.com/). Although this data could have easily been obtained/queried via the available API and entered into the map; I choose to go with a database approach. This required me to import the data from the API into a database(H2/MySQL) via a batch process before serving the data into the heat map. 

The front end ultilizes the following APIs/Libraries: 
* [Google Maps JavaScript API](https://developers.google.com/maps/documentation/javascript/) - used to create heat map
* [Angular](https://angularjs.org/) - front end framework
* [NoUISlider](http://refreshless.com/nouislider/) - Time slider 
* [BootStrap](http://getbootstrap.com/) - A touch to keep the web page out of the early 2000s. 
* A sprinkle of different angular directives

Behind The Scenes:
* [Spring Boot](http://projects.spring.io/spring-boot/) - Meat and potatas.
* [Lombok](https://projectlombok.org/) - Time saver
* [soda-api-java](https://github.com/socrata/soda-java) - Get some SODA
* [Google Map Services Java Libraries](https://developers.google.com/maps/documentation/geocoding/intro) - Used for GeoCoding. Address to Long/Lat. Limited to 2,500 calls a day. 
* [mockito](http://mockito.org/) - Tasty mocks for unit testing. 
* [powermock](https://github.com/jayway/powermock) - Get lit unit testing!
* [wro4j](https://github.com/wro4j/wro4j) - Build project to get javascript libs in one place. 

Service Providers:
* MySQL - [DeckerServices](http://deckerservices.com/website-hosting/) or [ClearDB](https://www.cleardb.com)<sup>[1](#mysql)</sup>
* Application Hosting - [Pivotal Cloud Foundry](http://pivotal.io/platform)
* Javascript libary hosting - [CDNJS](https://cdnjs.com/)

<a name="mysql">1</a>: The initial DB host is Decker but if there are concerns about network latency it can be switch over to ClearDB. 

The inital implementation will be limited to just March 2016 as the geocoding apis are limited by calls. This could easily be circumvented by enabling the batch process to import 2,500 records each day. 

## Deployment
Instructions on deploying locally. 
### Locally
To run this application locally there are a few prerequisites you must have:
* [Maven](https://maven.apache.org/) installed.
* Google Maps API key obtainable [here](https://console.developers.google.com/apis/credentials).
* (Optional) A mysql database running with proper permission and connection details 

1. Rename application.properties.example to application.properties
2. Replace "YOUR_API_KEY" with the Google Maps API Key obtained above. 
3. Uncomment the appropriate database section that you would prefer to use. H2 or MySQL. 
3. Run ```mvn clean spring-boot:run -Dsoda.query.limit=XXXX```. 'XXXX' is the amount of data you would like to import into your instance. This property will limit the amount of data that is to be returned from the SODA API query. 


## Bugs 
Bugs can be tracked or reported in the [issues tab](/../../issues/). 


 
