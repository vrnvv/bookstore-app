package com.example.bookstoreapp.dto.orderdto;

import com.example.bookstoreapp.model.entity.Order;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UpdateStatusRequestDto {
    @NotEmpty
    private Order.Status status;
}
