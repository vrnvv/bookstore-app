package com.example.bookstoreapp.model.entity;

import com.example.bookstoreapp.model.AbstractBeanEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE shopping_carts SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted=false")
@Table(name = "shopping_carts")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ShoppingCart extends AbstractBeanEntity {
    @OneToOne()
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "shoppingCart",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setShoppingCart(this);
    }
}
