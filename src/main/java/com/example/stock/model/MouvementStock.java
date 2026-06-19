package com.example.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mouvement_Stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MouvementStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeMouvement type;

    private Integer quantite;
    private String motif;
    private LocalDateTime dateMouvement;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
}
