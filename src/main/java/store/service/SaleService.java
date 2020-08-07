package store.service;

import store.dto.SaleDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface SaleService {

    List<SaleDTO> get(Long id, LocalDateTime dateOn, LocalDateTime dateOff, List<String> categories);

    SaleDTO add(SaleDTO saleDTO);

    SaleDTO update(SaleDTO saleDTO);

}
