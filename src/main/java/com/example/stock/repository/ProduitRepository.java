package com.example.stock.repository;

import com.example.stock.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategoryId(Long categoryId);
    List<Produit> findByNomContainingIgnoreCase(String nom);
}
