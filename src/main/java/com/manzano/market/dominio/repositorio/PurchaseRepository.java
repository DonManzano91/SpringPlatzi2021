package com.manzano.market.dominio.repositorio;

import com.manzano.market.dominio.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);


}