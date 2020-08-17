package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.SaleDTO;
import store.mapper.EntityMapper;
import store.model.Sale;
import store.model.Sale_;
import store.repository.SaleRepository;
import store.service.SaleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;

    @PersistenceContext
    private EntityManager entityManager;


    private EntityMapper<Sale, SaleDTO>  entityMapper = (EntityMapper<Sale, SaleDTO>) EntityMapper.getInstance();

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<SaleDTO> get(Long id, LocalDate saleDate, List<String> categories) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Sale> saleCriteriaQuery = criteriaBuilder.createQuery(Sale.class);
        Root<Sale> saleRoot = saleCriteriaQuery.from(Sale.class);

        saleCriteriaQuery.select(saleRoot);

        List<Predicate> predicates = new ArrayList<>();

        if (id != null){
            predicates.add(criteriaBuilder.equal(saleRoot.get(Sale_.id), id));
        }
        if (saleDate != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(saleRoot.get(Sale_.dateOn), saleDate));
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(saleRoot.get(Sale_.dateOff), saleDate));
        }
        if (categories != null) {
            predicates.add(criteriaBuilder.and(saleRoot.join("categories").in(categories)));
        }

        if (!predicates.isEmpty()){
            saleCriteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        saleCriteriaQuery.groupBy(saleRoot.get(Sale_.id));

        List<Sale> list = entityManager.createQuery(saleCriteriaQuery).getResultList();
        return list.stream().map(sale -> entityMapper.toDTO(sale, SaleDTO.class)).collect(Collectors.toList());
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
