package store.util;

import store.dto.ProductDTO;
import store.model.Sale;

import java.util.List;

public class SaleUtil {

    public static void setSalePriceForProduct(ProductDTO productDTO, List<Sale> sales) {

        double resultPercent = 0;
        for (Sale sale : sales) {
            for (String category : productDTO.getCategories()) {
                if (sale.getCategories().contains(category)){
                    if (sale.getPercent() > resultPercent){
                        resultPercent = sale.getPercent();
                    }
                }
            }
        }
        if (resultPercent == 0){
            productDTO.setSalePrice(null);
        } else {
            productDTO.setSalePrice(productDTO.getPrice() - (productDTO.getPrice()) / 100 * resultPercent);
        }
    }
}
