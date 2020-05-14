package com.example.syngenta;

public class Product {
     private String name;
     private String size;
     private String price;
     private String cartonNumber;

    public Product() {
    }

    public Product(String name, String size, String price, String cartonNumber) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.cartonNumber = cartonNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCartonNumber() {
        return cartonNumber;
    }

    public void setCartonNumber(String cartonNumber) {
        this.cartonNumber = cartonNumber;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price='" + price + '\'' +
                ", cartonNumber='" + cartonNumber + '\'' +
                '}';
    }
}
