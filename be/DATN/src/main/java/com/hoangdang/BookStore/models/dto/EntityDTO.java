package com.hoangdang.BookStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityDTO {
    private int id;
    private int storageId;
    private int productId;
    private LocalDateTime entryDay;
    private int totalQuantity;
    private int quantity;
}
