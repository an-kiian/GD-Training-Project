package store.service;

import store.dto.SaleDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {

    SaleDTO getById(Long id);

    List<SaleDTO> getByCategories(List<String> categories);

    List<SaleDTO> getByDates(LocalDateTime startDate, LocalDateTime endDate);

    List<SaleDTO> getAll();

    SaleDTO add(SaleDTO saleDTO);

    SaleDTO update(SaleDTO saleDTO);

}
