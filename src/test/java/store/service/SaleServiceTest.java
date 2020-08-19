package store.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import store.dto.SaleDTO;
import store.exception.SaleNotFoundException;
import store.mapper.EntityMapper;
import store.model.Sale;
import store.model.Sale_;
import store.repository.SaleRepository;
import store.service.impl.SaleServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Sale> saleCriteriaQuery;

    @Mock
    private Root<Sale> saleRoot;

    @Mock
    private Path<Long> path;

    @Mock
    private TypedQuery<Sale> typedQuery;

    @Mock
    private Join<Object, Object> joinCat;

    @Rule
    public final ExpectedException EXCEPTION = ExpectedException.none();

    private Sale sale;

    private List<Sale> saleList;

    private EntityMapper<Sale, SaleDTO> mapper;

    private List<String> categories;

    private static final Long ID = 1L;

    private static final int YEAR = 2020;

    private static final int DAY = 1;

    private static final Long idParameter = null;

    private static final LocalDate saleDateParameter = null;

    private static final List<String> categoriesParameter = null;

    @Before
    public void setUp(){
        //given
        LocalDate dateOn = LocalDate.of(YEAR, 3, DAY);
        LocalDate dateOff = LocalDate.of(YEAR, 5, DAY);

        categories = new ArrayList<>();
        categories.add("First");
        categories.add("Second");

        sale = new Sale();
        sale.setId(ID);
        sale.setDateOn(dateOn);
        sale.setDateOff(dateOff);
        sale.setPercent(30);
        sale.setCategories(categories);

        saleList = Collections.singletonList(sale);

        //given saleDTO from mapper
        mapper = (EntityMapper<Sale, SaleDTO>) EntityMapper.getInstance();

        ReflectionTestUtils.setField(saleService, "entityManager", entityManager);
    }

    @Test
    public void testGetIfAllParameterMissing(){
        //given
        LocalDate dateOn = LocalDate.of(YEAR, 4, DAY);
        LocalDate dateOff = LocalDate.of(YEAR, 12, DAY);
        categories.add("Third");
        Sale sale2 = new Sale();
        sale2.setId(2L);
        sale2.setDateOn(dateOn);
        sale2.setDateOff(dateOff);
        sale2.setPercent(20);
        sale2.setCategories(categories);

        List<Sale> list = Arrays.asList(sale, sale2);

        //when
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Sale.class)).thenReturn(saleCriteriaQuery);
        when(saleCriteriaQuery.from(Sale.class)).thenReturn(saleRoot);
        when(saleRoot.get(Sale_.id)).thenReturn(path);
        when(entityManager.createQuery(saleCriteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(list);

        //then
        List<SaleDTO> resultListDTO = saleService.get(idParameter, saleDateParameter, categoriesParameter);
        List<Sale> resultList = resultListDTO.stream().map(saleDTO1 -> mapper.toEntity(saleDTO1, Sale.class)).collect(Collectors.toList());

        //testing the method is calling
        verify(typedQuery).getResultList();

        //testing correct data
        assertEquals(list, resultList);
        assertEquals(2, resultListDTO.size());
    }

    @Test
    public void testGetIfPassIdParameter(){
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Sale.class)).thenReturn(saleCriteriaQuery);
        when(saleCriteriaQuery.from(Sale.class)).thenReturn(saleRoot);
        when(saleRoot.get(Sale_.id)).thenReturn(path);
        when(entityManager.createQuery(saleCriteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(saleList);

        List<SaleDTO> resultListDTO = saleService.get(ID, saleDateParameter, categoriesParameter);
        //convert saleDTO into sale
        List<Sale> resultList = resultListDTO.stream().map(saleDTO -> mapper.toEntity(saleDTO, Sale.class)).collect(Collectors.toList());

        //testing the method is calling
        verify(typedQuery).getResultList();

        //testing correct data
        assertEquals(saleList, resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    public void testIfPassSaleDateParameter(){
        //given
        LocalDate date = LocalDate.of(YEAR, 4, DAY);

        //when
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Sale.class)).thenReturn(saleCriteriaQuery);
        when(saleCriteriaQuery.from(Sale.class)).thenReturn(saleRoot);
        when(saleRoot.get(Sale_.id)).thenReturn(path);
        when(entityManager.createQuery(saleCriteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(saleList);

        //then
        List<SaleDTO> resultListDTO = saleService.get(idParameter, date, categoriesParameter);
        List<Sale> resultList = resultListDTO.stream().map(saleDTO -> mapper.toEntity(saleDTO, Sale.class)).collect(Collectors.toList());

        //testing the method is calling
        verify(typedQuery).getResultList();

        //checking correct data
        assertEquals(saleList, resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    public void testIfPassCategoriesParameter(){
        //given
        List<String> cat = Arrays.asList("First", "Second");

        //when
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Sale.class)).thenReturn(saleCriteriaQuery);
        when(saleCriteriaQuery.from(Sale.class)).thenReturn(saleRoot);
        when(saleRoot.get(Sale_.id)).thenReturn(path);
        when(entityManager.createQuery(saleCriteriaQuery)).thenReturn(typedQuery);
        when(saleRoot.join(anyString())).thenReturn(joinCat);
        when(typedQuery.getResultList()).thenReturn(saleList);

        //then
        List<SaleDTO> resultListDTO = saleService.get(idParameter, saleDateParameter, cat);
        List<Sale> resultList = resultListDTO.stream().map(saleDTO -> mapper.toEntity(saleDTO, Sale.class)).collect(Collectors.toList());

        //testing the method is calling
        verify(typedQuery).getResultList();

        //checking correct data
        assertEquals(saleList, resultList);
        assertEquals(1, resultList.size());
    }



    @Test
    public void testAdd(){
        //given
        LocalDate dateOn = LocalDate.of(YEAR, 1, DAY);
        LocalDate dateOff = LocalDate.of(YEAR, 2, DAY);
        Sale newSale = new Sale();
        newSale.setId(3L);
        newSale.setDateOn(dateOn);
        newSale.setDateOff(dateOff);
        newSale.setPercent(50);
        newSale.setCategories(categories);

        //when
        when(saleRepository.save(newSale)).thenReturn(newSale);

        //then
        SaleDTO resultSaleDTO = saleService.add(mapper.toDTO(newSale, SaleDTO.class));

        //testing the method is calling
        verify(saleRepository).save(newSale);

        //convert saleDTO into sale
        Sale resultSale = mapper.toEntity(resultSaleDTO, Sale.class);

        //checking correct data
        assertEquals(newSale, resultSale);
    }

    @Test
    public void testUpdate(){
        //given
        Sale updateSale = sale;
        updateSale.setPercent(35);

        //when
        when(saleRepository.save(updateSale)).thenReturn(updateSale);
        when(saleRepository.findById(ID)).thenReturn(java.util.Optional.of(updateSale));

        //then
        SaleDTO resultSaleDTO = saleService.update(mapper.toDTO(updateSale, SaleDTO.class));

        //testing the method is calling
        verify(saleRepository).save(updateSale);

        //checking correct data
        assertEquals(updateSale, mapper.toEntity(resultSaleDTO, Sale.class));
    }

    @Test
    public void testUpdateForIncorrectId(){
        //given
        Long testID = 5L;
        Sale updateSale = sale;
        updateSale.setId(testID);

        //when
        when(saleRepository.findById(testID)).thenThrow(new SaleNotFoundException(testID));
        when(saleRepository.save(updateSale)).thenReturn(updateSale);

        EXCEPTION.expect(SaleNotFoundException.class);
        //then
        SaleDTO nullSaleDTO = saleService.update(mapper.toDTO(updateSale, SaleDTO.class));

        //testing that the method is not called if the sale being updated does not exist in the database
        verify(saleRepository, times(0)).save(updateSale);

        //checking  correct data (if sale that we want update not existing, need get null)
        assertNull(nullSaleDTO);
    }
}
