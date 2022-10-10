[Micronaut Framework](https://micronaut.io) CLI app to report podcast episodes releases as a CSV file

To use it: 

```
sdk use java 17.0.4-amzn
./gradlew build        
java -jar build/libs/podcastreport-0.1-all.jar 
    -o /Users/sdelamo/Downloads/podcast.csv 
    -u https://feed.micronautpodcast.com/rss.xml
```
