package com.manzano.market.persistencia.Mapeador;

import com.manzano.market.dominio.PurchaseItem;
import com.manzano.market.persistencia.entidad.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            //Ten en cuenta el id compuesto
            @Mapping(source = "id.idProducto" , target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            //@Mapping(source = "total", target = "total"), comentado por que ya no es necesario, al llamarse igual el campo
            @Mapping(source = "estado", target = "active")
    })

    PurchaseItem toPurchaseItem(CompraProducto compraProducto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    CompraProducto toCompraProducto(PurchaseItem purchaseItem);
}
