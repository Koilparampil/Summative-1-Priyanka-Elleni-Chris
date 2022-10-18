package com.company.Summative1PriyankaElleniChris.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_shirt")
public class T_shirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "t_shirt_id")
    @NotNull
    private Integer id;

    @NotNull @Size(max = 20)
    private Integer size;

    @NotEmpty(message = "You should put the color") @Size(max = 20)
    @NotNull(message = "please put the color value")
    private String color;

    @NotEmpty(message = "You should put the description") @Size(max = 255)
    private String description;

    @Digits(integer =  5, fraction = 2)
    @NotNull(message = "Please add the price")
    private BigDecimal price;
    @NotEmpty(message = "You need to put the quantity")
    private Integer quantity;

    public T_shirt() {
    }

    public T_shirt(Integer id, Integer size, String color, String description, BigDecimal price, Integer quantity) {
        this.id = id;
        this.size = size;
        this.color = color;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
       this.description=  description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_shirt t_shirt = (T_shirt) o;
        return Objects.equals(id, t_shirt.id) && Objects.equals(size, t_shirt.size) && Objects.equals(color, t_shirt.color) && Objects.equals(description, t_shirt.description) && Objects.equals(price, t_shirt.price) && Objects.equals(quantity, t_shirt.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, color, description, price, quantity);
    }

    @Override
    public String toString() {
        return "T_shirt{" +
                "id=" + id +
                ", size=" + size +
                ", color='" + color + '\'' +
                ", Description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
