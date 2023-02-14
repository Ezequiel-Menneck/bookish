package com.menneck.bookish.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer phone;
    private Double comission;

    @JsonIgnore
    @OneToMany
    private List<Order> orderList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Seller seller = (Seller) o;
        return id != null && Objects.equals(id, seller.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
