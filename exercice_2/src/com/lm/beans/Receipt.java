package com.lm.beans;

public class Receipt {
    private int    id;
    private String date;
    private String name;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount( double amount ) {
        this.amount = amount;
    }

}
