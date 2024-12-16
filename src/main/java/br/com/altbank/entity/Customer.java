package br.com.altbank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String document;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliveryAddressId")
    private Address deliveryAddress;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    public Customer(String name, String document, Address deliveryAddress) {
        this.name = name;
        this.document = document;
        this.deliveryAddress = deliveryAddress;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

