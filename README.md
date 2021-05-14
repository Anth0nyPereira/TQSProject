
### Application developed as the individual project of the *Teste & Qualidade de Software* course unit
#### Engenharia Informática, UA, Ano Letivo 2020/2021
###### Anth0nyPereira, nº 93016


<p align="center">
  <p>Deployed application website: https://need-some-fresh-air.herokuapp.com </p>
  <img src="cover.png">
</p>

### API endpoints

```
GET /api/data
- Search for air quality data by a city name.
- Example value:
{
  "aqIndex": 43,
  "co": 224,
  "o3": 22,
  "so2": 0.587665,
  "no2": 19,
  "pm10": 31,
  "pm25": 10.2193,
  "predominantPollenType": "Molds",
  "pollenLevelTree": 1,
  "pollenLevelWeed": 1,
  "pollenLevelGrass": 1,
  "pollenLevelMold": 1
}
```

```
GET /api/dataByCoords
- Search for air quality data by coordinates.
- Example value:
{
  "aqIndex": 53,
  "co": 282.049,
  "o3": 114.799,
  "so2": 1.60933,
  "no2": 0.360131,
  "pm10": 17.1957,
  "pm25": 5.51555,
  "predominantPollenType": "Molds",
  "pollenLevelTree": 1,
  "pollenLevelWeed": 1,
  "pollenLevelGrass": 1,
  "pollenLevelMold": 1
}
```

```
GET /api/cache
- Returns the actual content of the cache.
- Example value:
{
  "City{city='Aveiro', time=1620900709418, latitude=40.64427, longitude=-8.64554}": {
    "aqIndex": 43,
    "co": 224,
    "o3": 22,
    "so2": 0.587665,
    "no2": 19,
    "pm10": 31,
    "pm25": 10.2193,
    "predominantPollenType": "Molds",
    "pollenLevelTree": 1,
    "pollenLevelWeed": 1,
    "pollenLevelGrass": 1,
    "pollenLevelMold": 1
  }
}
```

```
GET /api/stats
- Returns cache statistics, such as the number of requests, hits and misses.
- Example value:
{
  "countRequests": 1,
  "hits": 1,
  "misses": 0
}
```
