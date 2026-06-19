package com.example.stock.service;

import com.example.stock.model.Produit;
import com.example.stock.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProduitService {
    

    @Autowired
    private ProduitRepository produitRepository;
    private ProduitRepository produitService;

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }


    public Produit findById(Long id) {
        return produitRepository.findById(id).orElse(null);

    }


    public Produit save(Produit produit) {
        // Générer la référence automatiquement si vide
        if (produit.getReference() == null || produit.getReference().isBlank()) {
            long count = produitRepository.count() + 1;
            produit.setReference(String.format("REF%03d", count));
        }
        return produitRepository.save(produit);
    }

    public void deleteById(Long id) {
        produitRepository.deleteById(id);
    }
    public List<Produit> findByCategoryId(Long id) {
        return produitRepository.findByCategoryId(id);
    }
}
