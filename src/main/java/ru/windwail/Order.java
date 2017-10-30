package ru.windwail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by icetusk on 27.10.17.
 */

public class Order implements Serializable {

    public enum Type { REPLENISHMENT,  FULFILLMENT};

    private String orderId;
    private String orderType;
    private String saleLocation;
    private String stockId;
    private Long createDate;
    private Long requestDeliveryDate;

    private List<Item> items;
    private Customer customer;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSaleLocation() {
        return saleLocation;
    }

    public void setSaleLocation(String saleLocation) {
        this.saleLocation = saleLocation;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getRequestDeliveryDate() {
        return requestDeliveryDate;
    }

    public void setRequestDeliveryDate(Long requestDeliveryDate) {
        this.requestDeliveryDate = requestDeliveryDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
