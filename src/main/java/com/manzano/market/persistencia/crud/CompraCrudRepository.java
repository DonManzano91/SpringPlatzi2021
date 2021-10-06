package com.manzano.market.persistencia.crud;

import com.manzano.market.persistencia.entidad.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//El CrudRepository necesita el valor del entity, y el tipo de dato de su Id
public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    Optional<List<Compra>> findByidCliente(String idCliente);


}
