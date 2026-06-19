package com.example.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;

    private String nom;

    private String marque;

    private String dimension;

    private Integer quantite;

    private Double prixAchat;

    private Double prixVente;

    private Integer seuilAlerte;
    private LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Category category;

    @OneToMany(mappedBy = "produit")
    @JsonIgnore
    private List<MouvementStock> mouvements;

}
