package sn.isi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.ProduitDto;
import sn.isi.service.ProduitService;

import java.util.List;

@RestController
@RequestMapping("/api/produit")
public class ProduitController {
    private static final Logger logger = LoggerFactory.getLogger(ProduitController.class);
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProduitDto>> listProduits() {
        logger.debug("Affichage de la liste des produits");
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @PostMapping("/new")
    public ResponseEntity<ProduitDto> createProduit(@RequestBody ProduitDto produitDto) {
        logger.debug("Enregistrement d'un nouveau produit: {}", produitDto);
        ProduitDto createdProduit = produitService.createProduit(produitDto);
        return ResponseEntity.ok(createdProduit);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<ProduitDto> showEditProduitForm(@PathVariable int id) {
        logger.debug("Affichage des détails du produit ID: {}", id);
        ProduitDto produitDto = produitService.getProduit(id);
        return ResponseEntity.ok(produitDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProduit(@PathVariable int id, @RequestBody ProduitDto produitDto) {
        logger.debug("Mise à jour du produit ID: {}", id);
        produitService.updateProduit(id, produitDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable int id) {
        logger.debug("Suppression du produit ID: {}", id);
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}
