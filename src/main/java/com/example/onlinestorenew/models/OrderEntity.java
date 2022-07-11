package com.example.onlinestorenew.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "public", catalog = "online-store")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "list")
    private String list;
    @Basic
    @Column(name = "total_price")
    private Double totalPrice;
    @Basic
    @Column(name = "place")
    private String place;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone")
    private String phone;

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

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPlace() { return place; }

    public void setPlace(String place) { this.place = place; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(list, that.list) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(place, that.place) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, list, totalPrice, place, email, phone);
    }
}
