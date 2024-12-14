package br.com.altbank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private String document;
    private BigDecimal balance;
}
