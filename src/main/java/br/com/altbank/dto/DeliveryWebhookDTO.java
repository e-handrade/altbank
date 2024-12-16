package br.com.altbank.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
@Data
public class DeliveryWebhookDTO {
    @Schema(description = "card id")
    private String trackingId;
    @Schema(description = "Delivery types", example = "PENDING,SHIPPED,DELIVERED")
    private String deliveryStatus;
    private LocalDateTime deliveryDate;
    @Schema(description = "Delivery Return Reason",
            example = "INVALID_ADDRESS,RECIPIENT_UNAVAILABLE,DAMAGED_PACKAGE,LOST_IN_TRANSIT,OTHER")
    private String deliveryReturnReason;
    @Schema(description = "Address id")
    private String deliveryAddress;
}
