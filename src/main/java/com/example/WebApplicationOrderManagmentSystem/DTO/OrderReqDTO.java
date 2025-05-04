package com.example.WebApplicationOrderManagmentSystem.DTO;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class OrderReqDTO {
    @NotEmpty(message = "поле товаров не должно быть пустым")
    private List<OrderItemDTO> items;
    @DecimalMin(value = "0.01",message = "Сумма товаров не должно быть равным 0")
    private double totalPrice;


    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
