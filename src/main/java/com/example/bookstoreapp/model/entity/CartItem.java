package com.example.bookstoreapp.model.entity;

import com.example.bookstoreapp.model.AbstractBeanEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@SQLDelete(sql = "UPDATE cart_items SET is_deleted = TRUE WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "cart_items")
@Entity
@EqualsAndHashCode(callSuper = false)
public class CartItem extends AbstractBeanEntity {
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private boolean isDeleted = false;
}
