package com.hoangdang.BookStore.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOrder {
    DANG_XU_LY("Đang xử lý"),
    XAC_NHAN("Đã xác nhận"),
    DANG_GIAO_HANG("Đang giao hàng"),
    DA_GIAO_HANG("Đã giao hàng"),
    HUY_DON("Đã hủy đơn");

    private String status;
}
