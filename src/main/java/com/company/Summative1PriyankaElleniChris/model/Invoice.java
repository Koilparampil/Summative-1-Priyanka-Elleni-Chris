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
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "You must supply a value for name")
    @Size(max = 80, message = "Name cannot be more than 80 characters")
    private String name;

    @NotNull(message = "Street cannot be null")
    @NotEmpty(message = "You must supply a value for street")
    @Size(max = 30, message = "Street cannot be more than 30 characters")
    private String street;

    @NotNull(message = "City cannot be null")
    @NotEmpty(message = "You must supply value for city")
    @Size(max = 30, message = "City cannot be more than 30 characters")
    private String city;

    @NotNull(message = "State cannot be null")
    @NotEmpty(message = "You must supply a value for state")
    @Size(min = 2, max = 2, message = "State must be 2 characters")
    private String state;

    @NotNull(message = "Zipcode  cannot be null")
    @NotEmpty(message = "You must supply a value for zipcode")
    @Size(max = 5, message = "Zipcode cannot be more than 5 characters")
    private String zipcode ;

    @Column(name = "item_type")
    @NotNull(message = "Item type cannot be null")

    @Size(max = 20, message = "Item type cannot be more than 20 characters")
    private String itemType;

    @Column(name = "item_id")
    @NotNull(message = "Item id cannot be null")

    private Integer itemId;

    @Column(name = "unit_price")
    @NotNull(message = "Unit price cannot be null")

    @Digits(integer = 5, fraction = 2)
    private BigDecimal unitPrice;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Subtotal cannot be null")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal subtotal;

    @NotNull(message = "Tax cannot be null")

    @Digits(integer = 5, fraction = 2)
    private BigDecimal tax;

    @Column(name = "processing_fee")
    @NotNull(message = "Processing fee cannot be null")

    @Digits(integer = 5, fraction = 2)
    private BigDecimal processingFee;

    @NotNull(message = "Total cannot be null")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal total;

    // Default constructor
    public Invoice() {}

    public Invoice(String name, String street, String city, String state, String zipcode, String itemType, Integer itemId, BigDecimal unitPrice, Integer quantity) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Invoice(Integer invoiceId, String name, String street, String city, String state, String zipcode, String itemType, Integer itemId, BigDecimal unitPrice, Integer quantity, BigDecimal subtotal, BigDecimal tax, BigDecimal processingFee, BigDecimal total) {
        this.invoiceId = invoiceId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.tax = tax;
        this.processingFee = processingFee;
        this.total = total;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceId, invoice.invoiceId) && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipcode, invoice.zipcode) && Objects.equals(itemType, invoice.itemType) && Objects.equals(itemId, invoice.itemId) && Objects.equals(unitPrice, invoice.unitPrice) && Objects.equals(quantity, invoice.quantity) && Objects.equals(subtotal, invoice.subtotal) && Objects.equals(tax, invoice.tax) && Objects.equals(processingFee, invoice.processingFee) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
