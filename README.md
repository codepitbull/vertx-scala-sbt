#Getting vertx-lang-scala

It's in Maven central, current version is 3.4.0.Beta1.

#Work with this project

Create a runnable fat-jar
```
sbt assembly
```

play around in sbt
```
sbt
> console
scala> vertx.deployVerticle(s"scala:${classOf[HttpVerticle].getName}")
scala> vertx.deploymentIDs
```

From here you can freely interact with the Vertx-API inside the sbt-scala-shell.

#Dockerize
The project also contains everything you need to create a Docker-container.
Simply run the following command to package your fat-jar inside a Docker-container
```
sbt docker
```
To run use
```
docker run -p 8666:8666 default/vertx-scala-sbt
```
Point your browser to [http://127.0.0.1:8666/hello](http://127.0.0.1:8666/hello) and enjoy :)