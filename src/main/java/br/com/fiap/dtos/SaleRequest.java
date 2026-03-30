package br.com.fiap.dtos;

import java.math.BigDecimal;

public record SaleRequest(
        Long vehicleId,
        Long customerId,
        BigDecimal finalPrice
) {}