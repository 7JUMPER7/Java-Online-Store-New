package com.example.onlinestorenew.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "image", schema = "public", catalog = "online-store")
public class ImageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "data")
    private byte[] data;
    @Basic
    @Column(name = "good_id")
    private Integer goodId;
    @Basic
    @Column(name = "filename")
    private String filename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getFilename() { return filename; }

    public void setFilename(String filename) { this.filename = filename; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageEntity that = (ImageEntity) o;
        return id == that.id && Arrays.equals(data, that.data) && Objects.equals(goodId, that.goodId) && Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, goodId, filename);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
