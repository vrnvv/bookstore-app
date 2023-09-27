package com.example.bookstoreapp.model.entity;

import com.example.bookstoreapp.model.AbstractBeanEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@SQLDelete(sql = "UPDATE categories SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "categories")
@EqualsAndHashCode(callSuper = false)
public class Category extends AbstractBeanEntity {
    private String name;
    private String description;
    @Column(nullable = false)
    private boolean isDeleted = false;
}
