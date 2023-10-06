package com.example.bookstoreapp.model.entity;

import com.example.bookstoreapp.model.AbstractBeanEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role extends AbstractBeanEntity {
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Role role = (Role) obj;
        return Objects.equals(id, role.id) && roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    public enum RoleName {
        ROLE_ADMIN,
        ROLE_USER
    }
}
