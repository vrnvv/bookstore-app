package com.example.bookstoreapp.model.entity;

import com.example.bookstoreapp.model.AbstractBeanEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "order_items")
@SQLDelete(sql = "UPDATE order_items SET is_deleted = TRUE WHERE id=?")
@Where(clause = "is_deleted=false")
@EqualsAndHashCode(callSuper = false)
public class OrderItem extends AbstractBeanEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private boolean isDeleted = false;
}
