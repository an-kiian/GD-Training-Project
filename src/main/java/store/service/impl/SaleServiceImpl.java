package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.SaleDTO;
import store.mapper.EntityMapper;
import store.model.Sale;
import store.repository.SaleRepository;
import store.service.SaleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;

    private EntityMapper<Sale, SaleDTO>  entityMapper = (EntityMapper<Sale, SaleDTO>) EntityMapper.getInstance();

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    @Override
    public SaleDTO getById(Long id) {
        return entityMapper.toDTO(saleRepository.findById(id), SaleDTO.class);
    }

    @Override
    public List<SaleDTO> getByCategories(List<String> categories) {
        List<SaleDTO> saleDTOList = new ArrayList<>();
        saleRepository.findByCategories(categories).forEach(sale -> saleDTOList.add(entityMapper.toDTO(sale, SaleDTO.class)));
        return saleDTOList;
    }

    @Override
    public List<SaleDTO> getByDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<SaleDTO> saleDTOList = new ArrayList<>();
        saleRepository.findByDateOffAfterAndDateOnBefore(startDate, endDate).forEach(sale -> saleDTOList.add(entityMapper.toDTO(sale, SaleDTO.class)));
        return saleDTOList;
    }

    @Override
    public List<SaleDTO> getAll() {
        List<SaleDTO> saleDTOList = new ArrayList<>();
        saleRepository.findAll().forEach(sale -> saleDTOList.add(entityMapper.toDTO(sale, SaleDTO.class)));
        return saleDTOList;
    }

    @Override
    public SaleDTO add(SaleDTO saleDTO) {
        Sale sale = entityMapper.toEntity(saleDTO, Sale.class);
        return entityMapper.toDTO(saleRepository.save(sale), SaleDTO.class);
    }

    @Override
    public SaleDTO update(SaleDTO saleDTO) {
        Sale saleFromDB = saleRepository.findById(saleDTO.getId());
        if (saleFromDB == null) {
            return null;
        }
        saleFromDB.setDateOn(saleDTO.getDateOn());
        saleFromDB.setDateOff(saleDTO.getDateOff());
        saleFromDB.setPercent(saleDTO.getPercent());
        saleFromDB.setCategories(saleDTO.getCategories());
        return entityMapper.toDTO(saleRepository.save(saleFromDB), SaleDTO.class);
    }
}
