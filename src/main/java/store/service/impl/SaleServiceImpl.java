package store.service.impl;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.h2.command.dml.Query;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.SaleDTO;
import store.mapper.EntityMapper;
import store.model.QSale;
import store.model.Sale;
import store.repository.SaleRepository;
import store.service.SaleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        QSale qSale = QSale.sale;
        if(id == null & saleDate == null & categories == null){
            List<SaleDTO> saleDTOList = new ArrayList<>();
            saleRepository.findAll().forEach(sale -> saleDTOList.add(entityMapper.toDTO(sale, SaleDTO.class)));
            return saleDTOList;
        }
//        if (categories == null) {
//            return saleRepository.findByIdOrDateOnAfterOrDateOffBefore(id, saleDate).stream().map(sale -> entityMapper.toDTO(sale, SaleDTO.class)).collect(Collectors.toList());
//        } else {
//            return saleRepository.findByIdOrDateOnAfterOrDateOffBeforeByCategories(id, saleDate, categories, categories.size()).stream().map(sale -> entityMapper.toDTO(sale, SaleDTO.class)).collect(Collectors.toList());
//        }

        BooleanBuilder where = new BooleanBuilder();
        if (id != null){
            where.and(qSale.id.eq(id));
        }
        if (saleDate != null) {
            where.and(qSale.dateOn.loe(saleDate).and(qSale.dateOff.goe(saleDate)));
        }
        if(categories != null){
            for (String category : categories) {
                where.and(qSale.categories.contains(category));
            }
        }
        List<SaleDTO> saleDTOList = Lists.newArrayList(saleRepository.findAll(where)).stream().map(sale -> entityMapper.toDTO(sale, SaleDTO.class)).collect(Collectors.toList());
        return saleDTOList;
//        List<SaleDTO> saleDTOList = new ArrayList<>();
//        saleRepository.findAll(
//                qSale.id.eq(id).
//                        and(qSale.dateOn.before(saleDate).or(qSale.sale.dateOn.eq(saleDate))).
//                        and(qSale.dateOff.after(saleDate).or(qSale.sale.dateOff.eq(saleDate)))).
//                forEach(sale1 -> saleDTOList.add(entityMapper.toDTO(sale1, SaleDTO.class)));
//        return saleDTOList;
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
