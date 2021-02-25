package com.iot.algo;

public class Ladder {
    private String brand;
    private Integer length;
    private Integer weight;
    private String material;

    public Ladder() {
    }

    public Ladder(String brand, Integer length, Integer weight, String material) {
        this.brand = brand;
        this.length = length;
        this.weight = weight;
        this.material = material;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Ladder{" +
                "brand='" + brand + '\'' +
                ", length=" + length +
                ", weight=" + weight +
                ", material='" + material + '\'' +
                '}';
    }
}
