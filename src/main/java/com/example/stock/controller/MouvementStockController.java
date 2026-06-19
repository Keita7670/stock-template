package com.example.stock.controller;

import com.example.stock.model.MouvementStock;
import com.example.stock.service.MouvementStockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mouvements")
@CrossOrigin("*")
public class MouvementStockController {

    private final MouvementStockService mouvementService;

    public MouvementStockController(MouvementStockService mouvementService) {
        this.mouvementService = mouvementService;
    }

    @PostMapping
    public MouvementStock creer(@RequestBody MouvementStock mouvement) {
        return mouvementService.enregistrer(mouvement);
    }

    @GetMapping
    public List<MouvementStock> liste() {
        return mouvementService.findAll();
    }

    @GetMapping("/produit/{id}")
    public List<MouvementStock> historique(@PathVariable Long id) {
        return mouvementService.findByProduit(id);
    }

    @PutMapping("/{id}")
    public MouvementStock update(@PathVariable Long id, @RequestBody MouvementStock mouvement) {
        return mouvementService.update(id, mouvement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mouvementService.deleteById(id);
    }
}