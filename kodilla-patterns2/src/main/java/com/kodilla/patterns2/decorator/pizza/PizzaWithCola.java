package com.kodilla.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class PizzaWithCola extends AbstractOrderPizzaDecorator {
    protected PizzaWithCola(OrderPizza orderPizza) {
        super(orderPizza);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(5.0));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + cola 2L";
    }
}