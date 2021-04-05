package com.practicaweb.practicadaw.model;

public class Bitcoin {

    private final String base;
    private final String currency;
    private final int amount;



    public Bitcoin(String base, String currency, int amount){
        this.base = base;
        this.currency = currency;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getBase() {
        return base;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Bitcoin{" +
                "base='" + base + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
