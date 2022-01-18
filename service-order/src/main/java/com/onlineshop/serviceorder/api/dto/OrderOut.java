package com.onlineshop.serviceorder.api.dto;
import com.onlineshop.serviceorder.repo.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderOut {
    private Order someOrder;
    private List<IdentityDto> someUser;
    private List<ProductDto> someProduct;

    public OrderOut(Order someOrder, List<IdentityDto> someUser, List<ProductDto> someProduct) {
        this.someOrder = someOrder;
        this.someUser = someUser;
        this.someProduct = someProduct;
    }
}
