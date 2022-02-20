package com.hoangdang.BookStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDTO {
    private int userId;
    private int addressId;
    private String country;
    private String province;
    private String district;
    private String ward;
    private String streetName;
    private String streetNumber;
    private String addressType;
    private boolean defaultAddress;
}
