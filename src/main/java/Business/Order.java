package Business;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
    private int orderId;
    private int clientId;
    private Date orderDate;


    public Order(int orderId, int clientId, Date orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate=orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public int getHours(){
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(orderDate);
        return c.get(Calendar.HOUR_OF_DAY);
    }
    public int getDay(){
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(orderDate);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
