package br.com.altbank.entity;

import br.com.altbank.enums.ReissueReason;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cardReissueRequest")
public class CardReissueRequest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardId", nullable = false)
    private Card card;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReissueReason reason;

    @Column(name = "requestedAt", nullable = false, updatable = false)
    private LocalDateTime requestedAt;

    public CardReissueRequest(Card card, ReissueReason reason) {
        this.card = card;
        this.reason = reason;
    }

    @PrePersist
    protected void onCreate() {
        this.requestedAt = LocalDateTime.now();
    }
}