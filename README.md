# tsp

## build back
./mvnw clean package

## deploy front
just push

## deploy back
heroku container:push web -a tsp-mt
heroku container:release web -a tsp-mt