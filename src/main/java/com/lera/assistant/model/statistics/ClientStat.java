package com.lera.assistant.model.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
public class ClientStat {
    private String name;
    private BigDecimal amountPaid;
}
