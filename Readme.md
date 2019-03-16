# Version --1-- Notebook API Spring Boot Server

A simple notebook server api that can execute pieces of code in interpreter using
[Spring boot]() and [graalvm]() technologies.

### Functional spec
- [X] Set up a Spring Boot ENV.
- [X] Create `/execute` endpoint.
- [X] Challenge 1: Variables and state.
- [X] Challenge 2: Sessions.


### Supported interpreters
    - js
    - python
    - ruby
    - R
    
## Installation

### Setup with Maven

In order to run the notebook-api ,[Maven](https://maven.apache.org/) and the latest
GraalVM must be installed. It can be downloaded from
[GraalVM homepage](http://www.graalvm.org/downloads/).

Once downloaded, extract the archive, set the **GRAALVM_DIR** environment variable to
point to the graalvm directory, and **JAVA_HOME** to **GRAALVM_DIR**.

To be able to install additional languages, add grallvm bin dir to your path.

```
$ export PATH="$GRAALVM_DIR/bin:$PATH"
```

And use the following commands : 
```
$ gu install python
$ gu install R
$ gu install ruby
```

Clone the project with git : 
```
$ git clone https://github.com/wardov/Oracle-Notebook-Server.git
```

Instruction to start the project :

```
$ cd Oracle-Notebook-Server 
$ ./mvnw package -DskipTests
$ java -jar target/notebook-0.0.1.jar
```

Server is running in : 

```
http://localhost:8080
```

### Setup with Docker
the notebook server is pretty simple to setup and deploy in a Docker container.
In order to setup [docker](https://www.docker.com/) must be installed, installation instruction  can be found
in  [Docker homepage](https://docs.docker.com/install/).

Clone the project with git : 
```
$ git clone https://github.com/wardov/Oracle-Notebook-Server.git
```

Instruction to start the project :

```
$ cd Oracle-Notebook-Server 
$ docker build --rm -t notebook .
$ docker run -it --rm -p 8080:8080 notebook
```

Server is running in : 

```
http://localhost:8080
```

> **NOTE** all [GraalVM](https://github.com/oracle/graal) supported language are installed by default.

## Usage


To use notebook-server-API, you must have an HTTP client like [httpie](https://httpie.org/) if you
favor command line or [Postman](https://www.getpostman.com/) if you prefer GUI.

> **NOTE :** the cookies must be turned on in your HTTP client, to run notebook server correctly.

The code must be formatted like this:

```
%<interpreter-name><whitespace><code>
```

### Execute simple python code

```sh
$ http --session=/tmp/session.json :8080/execute code="%python print(1+1)" sessionId=1
```

Returns
```http
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Sat, 16 Mar 2019 11:40:20 GMT
Transfer-Encoding: chunked

{
    "result": "2"
}
```

### Execute python code with variable state

```sh
$ http --session=/tmp/session.json :8080/execute code="%python a=1" sessionId=1
```

Returns
```http
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Sat, 16 Mar 2019 11:41:18 GMT
Transfer-Encoding: chunked

{
    "result": ""
}
```

```sh
$ http --session=/tmp/session.json :8080/execute code="%python print(a+5)" sessionId=1
```

The state of python interpreter is preserved this code use variable **a** of the previous request.
```http
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Sat, 16 Mar 2019 11:41:23 GMT
Transfer-Encoding: chunked

{
    "result": "6"
}
```

### Timeout interval

If a request takes more than 10 seconds, it will automatically be canceled for protection against malicious code.


```sh
$ http --session=/tmp/session.json :8080/execute code="%python while True: 1" sessionId=1
```

Returns
```http
HTTP/1.1 400
Connection: close
Content-Type: application/json;charset=UTF-8
Date: Sat, 16 Mar 2019 11:41:18 GMT
Transfer-Encoding: chunked

{
    "cancelled": true,
    "exit": false,
    "exitStatus": 0,
    "guestException": true,
    "hostException": false,
    "incompleteSource": false,
    "internalError": false,
    "message": "Execution got cancelled.",
    "syntaxError": false,
    "timestamp": "2019-03-16T01:41:38.519+0000"
}

```

## Frameworks and Tools


notebook-api is currently using  the following frameworks and tools. Instructions on how to use them in your own application are linked below.

| Tools | README |
| ------ | ------ |
| Spring Boot | https://github.com/spring-projects/spring-boot |
| GraalVM | https://github.com/oracle/graal |



# Version --2-- Notebook API Spring Boot Server

This version is using some Java Class APIs 

java.lang.ProcessBuilder:  


This class is used to create operating system processes. 
Each ProcessBuilder instance manages a collectionof process attributes. The start() method creates a new Process instance with those attributes. The start() method can be invoked repeatedly from the same instanceto create new subprocesses with identical or related attributes. 

java.lang.Process:

The ProcessBuilder.start() and Runtime.execmethods create a native process and return an instance of asubclass of Process that can be used to control the processand obtain information about it. The class Processprovides methods for performing input from the process, performingoutput to the process, waiting for the process to complete,checking the exit status of the process, and destroying (killing)the process. 




