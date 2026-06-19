package com.example.stock.service;

import com.example.stock.model.MouvementStock;
import com.example.stock.model.Produit;
import com.example.stock.model.TypeMouvement;
import com.example.stock.repository.MouvementStockRepository;
import com.example.stock.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MouvementStockService {

    private final MouvementStockRepository mouvementRepository;
    private final ProduitRepository produitRepository;

    public MouvementStockService(
            MouvementStockRepository mouvementRepository,
            ProduitRepository produitRepository) {
        this.mouvementRepository = mouvementRepository;
        this.produitRepository = produitRepository;
    }

    public MouvementStock enregistrer(MouvementStock mouvement) {

        Produit produit = produitRepository
                .findById(mouvement.getProduit().getId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        if (mouvement.getType() == TypeMouvement.ENTREE) {
            produit.setQuantite(
                    produit.getQuantite() + mouvement.getQuantite()
            );
        } else {

            if (produit.getQuantite() < mouvement.getQuantite()) {
                throw new RuntimeException("Stock insuffisant");
            }

            produit.setQuantite(
                    produit.getQuantite() - mouvement.getQuantite()
            );
        }

        produitRepository.save(produit);

        mouvement.setDateMouvement(LocalDateTime.now());

        return mouvementRepository.save(mouvement);
    }

    public List<MouvementStock> findAll() {
        return mouvementRepository.findAll();
    }

    public List<MouvementStock> findByProduit(Long produitId) {
        return mouvementRepository.findByProduitId(produitId);
    }

    public MouvementStock update(Long id, MouvementStock nouveauMouvement) {
        MouvementStock ancien = mouvementRepository.findById(id).orElseThrow();
        Produit produit = produitRepository.findById(ancien.getProduit().getId()).orElseThrow();

        // Annuler l'effet de l'ancien mouvement
        if (ancien.getType() == TypeMouvement.ENTREE) {
            produit.setQuantite(produit.getQuantite() - ancien.getQuantite());
        } else {
            produit.setQuantite(produit.getQuantite() + ancien.getQuantite());
        }

        // Appliquer le nouveau mouvement
        if (nouveauMouvement.getType() == TypeMouvement.ENTREE) {
            produit.setQuantite(produit.getQuantite() + nouveauMouvement.getQuantite());
        } else {
            if (produit.getQuantite() < nouveauMouvement.getQuantite()) {
                throw new RuntimeException("Stock insuffisant !");
            }
            produit.setQuantite(produit.getQuantite() - nouveauMouvement.getQuantite());
        }

        produitRepository.save(produit);

        ancien.setType(nouveauMouvement.getType());
        ancien.setQuantite(nouveauMouvement.getQuantite());
        ancien.setMotif(nouveauMouvement.getMotif());
        ancien.setProduit(nouveauMouvement.getProduit());

        return mouvementRepository.save(ancien);
    }

    public void deleteById(Long id) {
        MouvementStock mouvement = mouvementRepository.findById(id).orElseThrow();
        Produit produit = produitRepository.findById(mouvement.getProduit().getId()).orElseThrow();

        // Annuler l'effet du mouvement avant suppression
        if (mouvement.getType() == TypeMouvement.ENTREE) {
            produit.setQuantite(produit.getQuantite() - mouvement.getQuantite());
        } else {
            produit.setQuantite(produit.getQuantite() + mouvement.getQuantite());
        }

        produitRepository.save(produit);
        mouvementRepository.deleteById(id);
    }

}