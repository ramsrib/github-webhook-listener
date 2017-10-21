README
======

This is a simple webhook listener (micro-service) developed using Spring Boot 2 + Kotlin + MongoDb + RabbitMQ. 

This application receives the github webhook event and dumps it into the mongodb and then publishes that event into the message queue (rabbitmq).


!!! RabbitMQ & MongoDB

If you don't have rabbitmq & mongodb installed in your local, you can use the **docker** to bring it up.

        $ docker-compose -d up


!!! Steps

1) Build and start the application:

        $ ./mvnw clean spring-boot:run


2) Start the ngrok

        $ ngrok http 9090


Note: ngrok is used to forward the internet traffic to the localhost. For more information, refer: https://ngrok.com/


3) Register the webhook with github using the ngrox url (ex: http://ab090ce1.ngrok.io)

4) Test the github webhook by pushing some commits into the repository.

5) Check the mongodb **events** collection for the webhook events.

        $ mongo
        $ db.event.find()

