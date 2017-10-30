package ru.windwail;

import java.io.Serializable;
import java.util.List;


public class AMQMessage implements Serializable {

    public enum Type { PRODUCT_AVAILABILITY, ORDER, PICKING_JOB, PIKING_RESULTS}

    private Type messageType;

    private List<Order> orders;

    private List<Item> items;

    public Type getMessageType() {
        return messageType;
    }

    public void setMessageType(Type messageType) {
        this.messageType = messageType;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

