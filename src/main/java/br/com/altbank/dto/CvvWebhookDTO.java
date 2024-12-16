package br.com.altbank.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CvvWebhookDTO {
    private Long accountId;
    private Long cardId;
    private int nextCvv;
    private LocalDateTime expirationDate;
}
