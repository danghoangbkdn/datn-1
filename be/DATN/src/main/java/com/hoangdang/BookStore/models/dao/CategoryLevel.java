package com.hoangdang.BookStore.models.dao;

import com.hoangdang.BookStore.models.compositKeys.CategoryLevelKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category_level")
@IdClass(CategoryLevelKey.class)
public class CategoryLevel implements Serializable {

    @Id
    @Column(name = "parent_category_id")
    private int parentCategoryId;

    @Id
    @Column(name = "child_category_id")
    private int childCategoryId;

    @Override
    public int hashCode() {
        return Objects.hash(parentCategoryId, childCategoryId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CategoryLevel cartProduct = (CategoryLevel) obj;
        return parentCategoryId == cartProduct.parentCategoryId &&
                childCategoryId == cartProduct.childCategoryId;
    }
}
