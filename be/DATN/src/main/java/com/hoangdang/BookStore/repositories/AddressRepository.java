package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByCountryAndProvinceAndDistrictAndWardAndStreetNameAndStreetNumber(
            String country, String province, String district, String ward, String streetName,
            String streetNumber);
}
