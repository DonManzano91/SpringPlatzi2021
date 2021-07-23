package com.manzano.market.persistencia;

import com.manzano.market.dominio.Product;
import com.manzano.market.dominio.repositorio.ProductRepository;
import com.manzano.market.persistencia.Mapeador.ProductMapper;
import com.manzano.market.persistencia.crud.ProductoCrudRepository;
import com.manzano.market.persistencia.entidad.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository /*Anotación que se le va dando por ser de interacción con nuestra DB*/
public class ProductoRepository implements ProductRepository {
    /**
     * Aqui se generan operaciones DB con base en el mapeo de tablas y auxiliados por los metodos de la interfaz
     * productoCrudRepository y ProductRepository, de modo que se emplea ya la traducción de objetos de persistencia y
     * de dominio con ayuda de mapper
     * */
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }
    /*Aqui tendremos la implementación del metodo definido en la interfaz ProductoCrudRepository*/
    @Override
    public Optional<List<Product>> getByCategoria(int idCategoria){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        /*Con esta opción buscaremos obtener por el id, el metodo findById es propio de la clase CrudRepository*/
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        /*De igual forma usa un metodo propio de spring data para hacer el save del objeto enunciado*/
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }

}
