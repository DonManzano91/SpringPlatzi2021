package com.manzano.market.persistencia.Mapeador;

import com.manzano.market.dominio.Purchase;
import com.manzano.market.persistencia.entidad.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra", target = "idPurchase"),
            @Mapping(source = "idCliente", target = "idClient"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comments"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "producto", target = "item"), //este mapper es el que usa el PurchaseItem.class, para mapear el item-compra 1 a 1
    })

    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true )
    Compra toCompra(Purchase purchase);

}
