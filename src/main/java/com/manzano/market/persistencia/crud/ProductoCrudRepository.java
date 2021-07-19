package com.manzano.market.persistencia.crud;

import com.manzano.market.persistencia.entidad.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author alexmanzano
 * */
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    /**
     * Esta interfaz nos facilitara la generación de operaciones en DB a travez de los metodos de CrudRepository
     * */
    /*Implementación de un Query Method que suplantaria un select * from Producto where idCategoria = ?, en spring,
    * existe tambien la opción de usar una anotación de query nativo, por buena practica se prefiere el query method
    * pero se podria poner por ejemplo del nativo:
    * @Query(value= "Select * from Producto where idCategoria = ?", nativeQuery = true)
    * */
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    Optional<List<Producto>> findByCantidadLessThan(int cantidadStock);



}
