package com.company.Summative1PriyankaElleniChris.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "t_shirt")
public class T_shirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name= "t_shirt_id")
    @NotNull
    private Integer id;

    @Column(nullable = false, length = 20 )
    @NotNull @Size(max = 20)
    private Integer size;

    @NotNull @Size(max = 20)
    @Column(length = 20)
    private String color;

    @Column(length = 255)
    @NotNull @Size(max = 255)
    private String Description;
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)

    private Integer quantity;

    public T_shirt() {
    }

    public T_shirt(Integer id, Integer size, String color, String description, BigDecimal price, Integer quantity) {
        this.id = id;
        this.size = size;
        this.color = color;
        Description = description;
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
        return Objects.equals(id, t_shirt.id) && Objects.equals(size, t_shirt.size) && Objects.equals(color, t_shirt.color) && Objects.equals(Description, t_shirt.Description) && Objects.equals(price, t_shirt.price) && Objects.equals(quantity, t_shirt.quantity);
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
