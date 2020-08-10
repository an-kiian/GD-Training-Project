package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.SaleDTO;
import store.mapper.EntityMapper;
import store.model.Sale;
import store.repository.SaleRepository;
import store.service.SaleService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;

    private EntityMapper<Sale, SaleDTO>  entityMapper = (EntityMapper<Sale, SaleDTO>) EntityMapper.getInstance();

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<SaleDTO> get(Long id, LocalDateTime saleDate, List<String> categories) {

        if(id == null & saleDate == null & categories == null){
            List<SaleDTO> saleDTOList = new ArrayList<>();
            saleRepository.findAll().forEach(sale -> saleDTOList.add(entityMapper.toDTO(sale, SaleDTO.class)));
            return saleDTOList;
        } else {
//            return saleRepository.findByIdOrDateOnAfterOrDateOffBefore(id ,saleDate,categories).stream().map(sale -> entityMapper.toDTO(sale, SaleDTO.class)).collect(Collectors.toList());
            return saleRepository.findByIdOrDateOnAfterOrDateOffBefore(id ,saleDate,categories).stream().map(sale -> entityMapper.toDTO(sale, SaleDTO.class)).collect(Collectors.toList());
        }
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
