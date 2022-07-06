package com.example.onlinestorenew.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart_good", schema = "public", catalog = "online-store")
public class CartGoodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "cart_id")
    private Integer cartId;
    @Basic
    @Column(name = "good_id")
    private Integer goodId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartGoodEntity that = (CartGoodEntity) o;
        return id == that.id && Objects.equals(cartId, that.cartId) && Objects.equals(goodId, that.goodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartId, goodId);
    }
}
