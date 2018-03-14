package com.jesusgsdev.services;

import com.jesusgsdev.entities.Purchase;
import com.jesusgsdev.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    @Transactional
    public List<Purchase> findPurchasesByCustomerEmail(String customerEmail){
        return purchaseRepository.findPurchaseByCustomerEmail(customerEmail);
    }

    @Transactional
    public List<Purchase> findPurchasesByBookId(String bookId){
        return purchaseRepository.findPurchaseByBookId(bookId);
    }

}
