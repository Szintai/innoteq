package com.Innoteq.innoteq.service;

import com.Innoteq.innoteq.model.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase save(Purchase purchase);

    List<Purchase> findAll();
}
