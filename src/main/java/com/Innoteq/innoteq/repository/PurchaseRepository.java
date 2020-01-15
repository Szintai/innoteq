package com.Innoteq.innoteq.repository;

import com.Innoteq.innoteq.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findAll();

}
