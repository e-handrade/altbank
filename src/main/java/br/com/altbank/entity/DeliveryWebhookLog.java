package br.com.altbank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deliveryWebhookLog")
public class DeliveryWebhookLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trackingId", nullable = false)
    private String trackingId;

    @Column(name = "deliveryStatus", nullable = false)
    private String deliveryStatus;

    @Column(name = "deliveryDate", nullable = false)
    private LocalDateTime deliveryDate;

    @Column(name = "deliveryReturnReason")
    private String deliveryReturnReason;

    @Column(name = "deliveryAddress", nullable = false)
    private String deliveryAddress;

    @Column(name = "receivedAt", nullable = false, updatable = false)
    private LocalDateTime receivedAt;

    @PrePersist
    public void onPersist() {
        this.receivedAt = LocalDateTime.now();
    }
}
