package store.service;

import store.dto.SaleDTO;

import java.time.LocalDate;
import java.util.List;

public interface SaleService {

    List<SaleDTO> get(Long id, LocalDate saleDate, List<String> categories);

    SaleDTO add(SaleDTO saleDTO);

    SaleDTO update(SaleDTO saleDTO);

}
