# Getting Started

A demo application that reconfigures logback logger at runtime without
restart leveraging k8s config map and logback 'scan' property.

# Run demo
java17, [mvn](https://maven.apache.org/install.html), [kind](https://kind.sigs.k8s.io/docs/user/quick-start/),
kubectl must be installed and present in your path.
[Docker](https://docs.docker.com/get-docker/) daemon must be running.

```bash
mvn spring-boot:build-image
kind create cluster
kind load docker-image log-level-demo:0.0.1-SNAPSHOT
kubectl apply -f k8s/config-map.yaml -f k8s/deployment.yaml
```

Checks logs of pods:

```bash
kubectl get logs demo-deployment-0
kubectl get logs demo-deployment-1
```

Only 'info' message should be logged:

```bash
11:59:00.403 [scheduling-1] INFO  o.dmitry.example.logleveldemo.Task - Logger level is >= info
11:59:01.401 [scheduling-1] INFO  o.dmitry.example.logleveldemo.Task - Logger level is >= info
11:59:02.402 [scheduling-1] INFO  o.dmitry.example.logleveldemo.Task - Logger level is >= info
```

Modify k8s/config-map.yaml file and set APPLICATION_LOG_LEVEL variable
to debug. Apply config map again:

```
kubectl apply -f k8s/config-map.yaml
```

Wait for few minutes, debug messages should also appear in the logs:

```bash
12:01:42,671 |-INFO in ch.qos.logback.core.joran.util.ConfigurationWatchListUtil@4b553d26 - Adding [file:/config/log-levels.xml] to configuration watch list.
12:01:42,674 |-INFO in ch.qos.logback.classic.model.processor.ConfigurationModelHandler - Registering a new ReconfigureOnChangeTask ReconfigureOnChangeTask(born:1668427302674)
12:01:42,675 |-INFO in ch.qos.logback.classic.model.processor.ConfigurationModelHandler - Will scan for changes in [file:/workspace/BOOT-INF/classes/logback.xml] 
12:01:42,675 |-INFO in ch.qos.logback.classic.model.processor.ConfigurationModelHandler - Setting ReconfigureOnChangeTask scanning period to 1 seconds
12:01:42,675 |-INFO in ch.qos.logback.core.model.processor.AppenderModelHandler - Processing appender named [STDOUT]
12:01:42,675 |-INFO in ch.qos.logback.core.model.processor.AppenderModelHandler - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
12:01:42,676 |-INFO in ch.qos.logback.core.model.processor.ImplicitModelHandler - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
12:01:42,678 |-INFO in ch.qos.logback.classic.model.processor.RootLoggerModelHandler - Setting level of ROOT logger to INFO
12:01:42,678 |-INFO in ch.qos.logback.core.model.processor.AppenderRefModelHandler - Attaching appender named [STDOUT] to Logger[ROOT]
12:01:42,678 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [org.dmitry.example] to DEBUG
12:01:42,678 |-INFO in ch.qos.logback.core.model.processor.DefaultProcessor@11f9422b - End of configuration.
12:01:42,678 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@1f3ebe64 - Registering current configuration as safe fallback point
12:01:43.495 [scheduling-1] INFO  o.dmitry.example.logleveldemo.Task - Logger level is >= info
12:01:43.495 [scheduling-1] DEBUG o.dmitry.example.logleveldemo.Task - Logger level is >= debug
12:01:44.495 [scheduling-1] INFO  o.dmitry.example.logleveldemo.Task - Logger level is >= info
12:01:44.496 [scheduling-1] DEBUG o.dmitry.example.logleveldemo.Task - Logger level is >= debug
12:01:45.496 [scheduling-1] INFO  o.dmitry.example.logleveldemo.Task - Logger level is >= info
12:01:45.496 [scheduling-1] DEBUG o.dmitry.example.logleveldemo.Task - Logger level is >= debug
12:01:46.497 [scheduling-1] INFO  o.dmitry.example.logleveldemo.Task - Logger level is >= info
12:01:46.497 [scheduling-1] DEBUG o.dmitry.example.logleveldemo.Task - Logger level is >= debug
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/maven-plugin/reference/html/#build-image)

