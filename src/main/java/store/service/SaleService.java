package store.service;

import store.dto.SaleDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {

    List<SaleDTO> get(Long id, LocalDateTime saleDate, List<String> categories);

    SaleDTO add(SaleDTO saleDTO);

    SaleDTO update(SaleDTO saleDTO);

}
