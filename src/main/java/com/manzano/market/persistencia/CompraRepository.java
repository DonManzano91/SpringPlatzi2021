package com.manzano.market.persistencia;

import com.manzano.market.dominio.Purchase;
import com.manzano.market.dominio.repositorio.PurchaseRepository;
import com.manzano.market.persistencia.Mapeador.PurchaseMapper;
import com.manzano.market.persistencia.crud.CompraCrudRepository;
import com.manzano.market.persistencia.entidad.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
/**
 * @Author AlexManzano
 * Repositorio para la entity Compra, extiende la PurchaseRepository implementando sus metodos, mas los del CRUD
 * y el Mapper, siempre se toma de la persistencia(metodos crud) hacia el mapper(toPurchase)
 * */
@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    /**Se puede obtener getAll() directo de CrudRepository, no hay que definirlo explicitamente en
     * compraCrudReposity
     * */
    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    /**Hay que definir este metodo explicitamente en CompraCrudRepository, ya que no es parte de CrudRepository
     * */
    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByidCliente(clientId).
                map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        /**Se debe referir y asociar los productos de cada compra para que se guarden en cascada
         * para esto hay que establecer correctamente getter y setters para todos los campos necesarios.*/
        compra.getProducto().forEach(compraProducto -> compraProducto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
