package com.manzano.market.dominio.servicio;

import com.manzano.market.dominio.Purchase;
import com.manzano.market.dominio.repositorio.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String client){
        return purchaseRepository.getByClient(client);
    }

    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }


}
