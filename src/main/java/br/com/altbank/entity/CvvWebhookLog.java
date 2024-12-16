package br.com.altbank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cvvWebhookLog")
public class CvvWebhookLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardId", nullable = false)
    private Card card;

    @Column(name = "nextCvv", nullable = false)
    private int nextCvv;

    @Column(name = "expirationDate", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "receivedAt", nullable = false, updatable = false)
    private LocalDateTime receivedAt;

    @PrePersist
    public void onPersist() {
        this.receivedAt = LocalDateTime.now();
    }

}
