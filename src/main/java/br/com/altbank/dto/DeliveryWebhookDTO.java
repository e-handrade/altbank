package br.com.altbank.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DeliveryWebhookDTO {
    private String trackingId;
    private String deliveryStatus;
    private LocalDateTime deliveryDate;
    private String deliveryReturnReason;
    private String deliveryAddress;
}
