package com.hoangdang.BookStore.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String province;
    private String district;
    private String ward;
    private String streetName;
    private String streetNumber;

    @Override
    public int hashCode() {
        return Objects.hash(id, country, province, district, ward, streetName, streetNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address address = (Address) obj;
        return id == address.id &&
                country.equals(address.country) &&
                province.equals(address.province) &&
                district.equals(address.district) &&
                ward.equals(address.ward) &&
                streetName.equals(address.streetName) &&
                streetNumber.equals(address.streetNumber);
    }
}

