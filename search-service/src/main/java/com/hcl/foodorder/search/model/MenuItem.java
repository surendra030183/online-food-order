package com.hcl.foodorder.search.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "items_index")
public class MenuItem {
    @Id
    private Long id;

    @Field(type = FieldType.Long, name = "restaurantId")
    private Long restaurantId;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Double, name = "price")
    private Double price;

    @Field(type = FieldType.Integer, name = "quantity")
    private Integer quantity;

    @Field(type = FieldType.Boolean, name = "isAvailable")
    private Boolean isAvailable;

    public MenuItem(Long id, Long restaurantId, String name, String description, Double price, Integer quantity, Boolean isAvailable) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return  Objects.equals(id, menuItem.id) &&
                Objects.equals(restaurantId, menuItem.restaurantId) &&
                Objects.equals(name, menuItem.name) &&
                Objects.equals(description, menuItem.description) &&
                Objects.equals(price, menuItem.price) &&
                Objects.equals(quantity, menuItem.quantity) &&
                Objects.equals(isAvailable, menuItem.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, name, description, price, quantity, isAvailable);
    }

    @Override
    public String toString() {
        return "MenuItems{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
