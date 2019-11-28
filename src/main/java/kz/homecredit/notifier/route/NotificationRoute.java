package kz.homecredit.notifier.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class NotificationRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("direct:notification").log("Camel message body: ${body}"); //.to("rabbitmq:inbound-queue");

        from("direct:notification").log("Camel message body: ${body}")
                .streamCaching()
                .to("rabbitmq://localhost:5672/mss.direct.outgoing?autoDelete=false&routingKey=kzntf.v2&queue=inbound-queue");

        from("rabbitmq://localhost:5672/mss.direct.outgoing?autoDelete=false&routingKey=kzntf.v2&queue=inbound-queue")
                .streamCaching()
                .log("From RabbitMQ: ${body}");
    }
}
