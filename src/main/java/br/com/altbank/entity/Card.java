package br.com.altbank.entity;


import br.com.altbank.enums.*;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private int cvv;

    @Column(name = "expirationDate", nullable = false)
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliveryStatus")
    private DeliveryStatus deliveryStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CardStatus status;

    public Card(Account account, CardType cardType) {
        this.account = account;
        this.cardType = cardType;
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
        System.out.println(Long.getLong(sb.toString()));

        return Long.parseLong(sb.toString());

    }

    @PrePersist
    protected void onCreate() {
        this.expirationDate = LocalDateTime.now().plusYears(4);
        this.deliveryStatus = DeliveryStatus.PENDING;
        this.number = cardNumberGenerator();
        this.cvv = cvvGenerator();
        this.status = CardStatus.BLOCKED;
    }

}