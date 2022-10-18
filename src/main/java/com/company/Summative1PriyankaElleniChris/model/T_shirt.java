package com.company.Summative1PriyankaElleniChris.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "t_shirt")
public class T_shirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name= "t_shirt_id")
    private int id;

    @Column(nullable = false, length = 20 )
    private int size;

    @NotNull
    @Column(length = 20)
    private String color;

    @Column(length = 255)
    @NotNull
    private String Description;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    public T_shirt(int id, int size, String color, String description, BigDecimal price, int quantity) {
        this.id = id;
        this.size = size;
        this.color = color;
        Description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public T_shirt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_shirt t_shirt = (T_shirt) o;
        return id == t_shirt.id && size == t_shirt.size && quantity == t_shirt.quantity && Objects.equals(color, t_shirt.color) && Objects.equals(Description, t_shirt.Description) && Objects.equals(price, t_shirt.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, color, Description, price, quantity);
    }

    @Override
    public String toString() {
        return "T_shirt{" +
                "id=" + id +
                ", size=" + size +
                ", color='" + color + '\'' +
                ", Description='" + Description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
