package com.manzano.market.persistencia;

import com.manzano.market.persistencia.crud.ProductoCrudRepository;
import com.manzano.market.persistencia.entidad.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository /*Anotación que se le va dando por ser de interacción con nuestra DB*/
public class ProductoRepository {
    /**
     * Aqui se generan operaciones DB con base en el mapeo de tablas y auxiliados por los metodos de la interfaz
     * productoCrudRepository
     * */

    private ProductoCrudRepository productoCrudRepository;
    public List<Producto> obtenTodos(){
        /*EL casteo es necesario por el tipo de dato que se obtiene del metodo findAll()*/
        return (List<Producto>) productoCrudRepository.findAll();
    }
    /*Aqui tendremos la implementación del metodo definido en la interfaz ProductoCrudRepository*/
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getByCandidadAndEstado(int candidadStock, boolean estado){
        return  productoCrudRepository.findByCantidadStockLessThanAndEstado(candidadStock,estado);
    }

    /*Con esta opción buscaremos obtener por el id, el metodo findById es propio de la clase CrudRepository*/
    public Optional<Producto> getProductoPorId(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    /*De igual forma usa un metodo propio de spring data para hacer el save del objeto enunciado*/
    public Producto saveProducto(Producto producto){
        return productoCrudRepository.save(producto);
    }


}
