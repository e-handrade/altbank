package br.com.altbank.entity;


import br.com.altbank.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType type;

    @Column(nullable = false, unique = true)
    private Long number = cardNumberGenerator();

    @Column(nullable = false)
    private int cvv = cvvGenerator();

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CardStatus status;

    public Card(Account account, CardType type) {
        this.account = account;
        this.type = type;
    }

    public int cvvGenerator() {
        return new Random().nextInt(1000);
    }

    public Long cardNumberGenerator() {

        Random random = new Random();

        StringBuilder sb = new StringBuilder(16);

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return Long.getLong(sb.toString());

    }

}