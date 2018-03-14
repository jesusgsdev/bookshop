package com.jesusgsdev.controllers;

import com.jesusgsdev.dtos.PurchaseRequestDTO;
import com.jesusgsdev.dtos.PurchaseResponseDTO;
import com.jesusgsdev.facades.PurchaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    PurchaseFacade purchaseFacade;

    @PostMapping
    public PurchaseResponseDTO addPurchase(@RequestBody @Valid PurchaseRequestDTO purchaseRequestDTO) {
        return purchaseFacade.performPurchase(purchaseRequestDTO);
    }

    @GetMapping(params = "email")
    public List<PurchaseResponseDTO> findPurchasesByCustomerEmail(@RequestParam("email") String email){
        return purchaseFacade.findPurchasesByCustomerEmail(email);
    }

}
