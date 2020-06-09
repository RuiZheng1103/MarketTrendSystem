# Market Trend Investment System

Market Trend Investment System Demo.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them based on Mac OS:

```
IDE: Spring Suit Tool 3 
JDK: Java 8
DataBase: Redis 6.0
Java Message Server: RadditMQ 3.8
Distributed Tracing System: Zipkin 2.20

```
### Installing

A step by step series of examples that tell you how to get a development env running through terminal.

#### Redis

Download, extract and compile Redis: 

```
$ wget http://download.redis.io/releases/redis-6.0.4.tar.gz
$ tar xzf redis-6.0.4.tar.gz
$ cd redis-6.0.4
$ make
```

Run Redis:

```
$ src/redis-server
```

Open another terminal for test based on Redis-cli:

```
$ src/redis-cli
redis> set foo bar
OK
redis> get foo
"bar"
```
If test fails, please go and check at https://redis.io/download.

#### RabbitMQ
Before installing make sure the tap is updated:

```
$ brew update
$ brew install rabbitmq
$ export PATH=$PATH:/usr/local/sbin to your .bash_profile or .profile or .bashrc
```

Start Server:

```
$ brew services start rabbitmq 
```

RabbitMQ web management plugin:

```
$ rabbitmq-plugins enable rabbitmq_management
```
If any problem, please go and check at https://www.rabbitmq.com/install-homebrew.html


#### Zipkin
If you have Java 8 or higher installed, the quickest way to get started is to fetch the latest release as a self-contained executable jar:

```
$ curl -sSL https://zipkin.io/quickstart.sh | bash -s
$ java -jar zipkin.jar
```

Start Zipkin:

```
browse to http://localhost:9411 
```

If any problem, please go and check at https://zipkin.io/pages/quickstart.


## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
