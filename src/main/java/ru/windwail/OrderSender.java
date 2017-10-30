package ru.windwail;

import com.google.gson.Gson;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class OrderSender {

    static String exchangeName = "product_availability";

    static ConnectionFactory factory;
    static Connection conn;
    static Channel channel;

    public static void main(String[] args) throws Exception {


        try {
            factory = new ConnectionFactory();
            factory.setUri("amqp://axtbsqtf:9Rvc4QxAzTBNxO4C-ETPm44BN7KMFV1H@salamander.rmq.cloudamqp.com/axtbsqtf");
            conn = factory.newConnection();

            channel = conn.createChannel();


            AMQP.Queue.DeclareOk rez = channel.queueDeclarePassive(exchangeName);


            Order order = new Order();
            order.setCreateDate( System.currentTimeMillis());
            order.setOrderType("REPLENISHMENT");

            Item item = new Item();
            item.setArticle("ARTICLE-123");
            item.setPrice(new BigDecimal(2401.23));
            item.setQuantity(12);
            item.setTitle("DELL XPS 13 FULLHD");


            AMQMessage msg = new AMQMessage();
            msg.setMessageType(AMQMessage.Type.ORDER);
            msg.setOrders(Arrays.asList(order));
            msg.setItems(Arrays.asList(item));


            Gson gson = new Gson();
            String jsonString = gson.toJson(msg);
            System.out.println("json " + jsonString);

            byte[] messageBodyBytes = jsonString.getBytes();
            channel.basicPublish("", exchangeName, null, messageBodyBytes);




        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            channel.close();
            conn.close();
        }


    }
}
