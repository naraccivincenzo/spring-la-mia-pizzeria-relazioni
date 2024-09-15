package org.lessons.booleaners.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.text.NumberFormat;
import java.time.Instant;
import java.util.Locale;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 10)
    @Lob
    private String description;

    @NotNull
    @Size(min = 2, max = 500)
    @Column(length = 500)
    private String photo;

    @NotNull
    @Positive
    @Column(name = "price" , nullable = false)
    private Double price;

    @UpdateTimestamp
    private Instant updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice(){ return price; }

    public String getFormattedPrice() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        return currencyFormatter.format(this.price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}