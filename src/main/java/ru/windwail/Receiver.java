package ru.windwail;

import com.google.gson.Gson;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class Receiver {

    static String exchangeName = "product_availability";

    static ConnectionFactory factory;
    static Connection conn;
    static Channel channel;
    static volatile boolean done;

    public static void main(String[] args) throws Exception {


        try {
            factory = new ConnectionFactory();
            factory.setUri("amqp://axtbsqtf:9Rvc4QxAzTBNxO4C-ETPm44BN7KMFV1H@salamander.rmq.cloudamqp.com/axtbsqtf");
            conn = factory.newConnection();

            channel = conn.createChannel();


            AMQP.Queue.DeclareOk rez = channel.queueDeclarePassive(exchangeName);


            Order order = new Order();
            order.setCreateDate(System.currentTimeMillis());
            order.setOrderType("REPLENISHMENT");

            AMQMessage msg = new AMQMessage();
            msg.setMessageType(AMQMessage.Type.ORDER);
            msg.setOrders(Arrays.asList(order));



            channel.basicConsume(exchangeName, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException
                {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    long deliveryTag = envelope.getDeliveryTag();
                    // (process the message components here ...)

                    System.out.println(new String(body));
                    //channel.basicAck(deliveryTag, false);
                    done = true;
                }
            });

            while(!done) {
                Thread.sleep(1000);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            channel.close();
            conn.close();
        }


    }
}
