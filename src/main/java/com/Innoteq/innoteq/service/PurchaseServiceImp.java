package com.Innoteq.innoteq.service;

import com.Innoteq.innoteq.model.Purchase;
import com.Innoteq.innoteq.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImp implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImp(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }
}
