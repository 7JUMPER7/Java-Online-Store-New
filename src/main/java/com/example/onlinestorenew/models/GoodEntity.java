package com.example.onlinestorenew.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "good", schema = "public", catalog = "online-store")
public class GoodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "price")
    private double price;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic
    @Column(name = "preview_image")
    private String previewImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodEntity that = (GoodEntity) o;
        return id == that.id && Double.compare(that.price, price) == 0 && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(categoryId, that.categoryId) && Objects.equals(previewImage, that.previewImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, categoryId, previewImage);
    }
}
