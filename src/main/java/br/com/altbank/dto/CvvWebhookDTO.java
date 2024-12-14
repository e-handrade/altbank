package br.com.altbank.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CvvWebhookDTO {
    private String accountId;
    private String cardId;
    private Integer nextCvv;
    private LocalDateTime expirationDate;
}
