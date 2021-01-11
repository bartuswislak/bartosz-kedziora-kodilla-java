package com.kodilla.good.patterns.food2door;

import java.util.ArrayList;
import java.util.List;

public class ExtraFoodShop implements Manufacturer
{
    private final String NAME = "Extra Food Shop";
    private String ADRESS = "Stroma 23, 40-226 Katowice";
    private final List<Product>productList = new ArrayList();

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public List<Product> getProductsList() {
        return null;
    }

    @Override
    public void process(Order order) {
        ExecutionOrder executionOrder = new ExecutionOrder();
        if(executionOrder.isComplete(order, productList))
        {
            sendOrder(order);
        }
        else
        {
            System.out.println("The order can not be processed");
        };
    }

    public void sendOrder(Order order)
    {
        System.out.println("Send Order: " + order.getProduct());
    }

    @Override
    public String toString() {
        return "ExtraFoodShop{" +
                "NAME='" + NAME + '\'' +
                ", ADRESS='" + ADRESS + '\'' +
                ", productList=" + productList +
                '}';
    }

    public String getADRESS() {
        return ADRESS;
    }

    public void setADRESS(String ADRESS) {
        this.ADRESS = ADRESS;
    }
}
