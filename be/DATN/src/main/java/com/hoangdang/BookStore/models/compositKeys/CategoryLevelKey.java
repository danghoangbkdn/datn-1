package com.hoangdang.BookStore.models.compositKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryLevelKey implements Serializable {
    private int parentCategoryId;
    private int childCategoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryLevelKey cartProductKey = (CategoryLevelKey) o;
        return parentCategoryId == cartProductKey.parentCategoryId &&
                childCategoryId == cartProductKey.childCategoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentCategoryId, childCategoryId);
    }

}
