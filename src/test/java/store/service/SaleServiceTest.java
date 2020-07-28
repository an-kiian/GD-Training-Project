package store.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import store.dto.SaleDTO;
import store.mapper.EntityMapper;
import store.model.Sale;
import store.repository.SaleRepository;
import store.service.impl.SaleServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private SaleRepository saleRepository;

    private Sale sale;

    private SaleDTO saleDTO;

    private List<Sale> saleList;

    private EntityMapper<Sale, SaleDTO> mapper;

    private List<String> categories;

    private static final Long ID = 1L;

    private static final int YEAR = 2020;
    private static final int DAY = 1;
    private static final int HOUR = 0;
    private static final int MINUTE = 0;

    @Before
    public void setUp(){
        //given
        LocalDateTime dateOn = LocalDateTime.of(YEAR, 3, DAY, HOUR, MINUTE);
        LocalDateTime dateOff = LocalDateTime.of(YEAR, 5, DAY, HOUR, MINUTE);

        categories = new ArrayList<>();
        categories.add("First Category");
        categories.add("Second Category");

        sale = new Sale();
        sale.setId(ID);
        sale.setDateOn(dateOn);
        sale.setDateOff(dateOff);
        sale.setPercent(30);
        sale.setCategories(categories);

        saleList = Collections.singletonList(sale);

        //given saleDTO from mapper
        mapper = (EntityMapper<Sale, SaleDTO>) EntityMapper.getInstance();
        saleDTO = mapper.toDTO(sale, SaleDTO.class);
    }

    @Test
    public void testGetById(){
        //when
        when(saleRepository.findById(ID)).thenReturn(sale);

        //then
        SaleDTO resultSaleDTO = saleService.getById(ID);

        assertEquals(sale, mapper.toEntity(resultSaleDTO, Sale.class));
        assertEquals(saleDTO, resultSaleDTO);
    }

    @Test
    public void testGetByDate(){
        //when
        when(saleRepository.findByDateOffAfterAndDateOnBefore(sale.getDateOn(), sale.getDateOff())).thenReturn(saleList);

        //then
        List<SaleDTO> resultListDTO = saleService.getByDates(sale.getDateOn(), sale.getDateOff());

        //convert list of saleDTO into list of sale
        List<Sale> resultList = resultListDTO.stream().map(saleDto -> mapper.toEntity(saleDto, Sale.class)).collect(Collectors.toList());

        assertEquals(saleList, resultList);
        assertEquals(1, resultList.size());

    }

    @Test
    public void testGetByCategories(){
        //when
        when(saleRepository.findByCategories(categories)).thenReturn(saleList);

        //then
        List<SaleDTO> resultListDTO = saleService.getByCategories(categories);

        //convert list of saleDTO into list of sale
        List<Sale> resultList = resultListDTO.stream().map(saleDto -> mapper.toEntity(saleDto, Sale.class)).collect(Collectors.toList());

        assertEquals(saleList, resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    public void testGetAll(){
        LocalDateTime dateOn = LocalDateTime.of(YEAR, 4, DAY, HOUR, MINUTE);
        LocalDateTime dateOff = LocalDateTime.of(YEAR, 12, DAY, HOUR, MINUTE);

        categories.add("Third Category");
        Sale sale2 = new Sale();
        sale2.setId(2L);
        sale2.setDateOn(dateOn);
        sale2.setDateOff(dateOff);
        sale2.setCategories(categories);
        sale2.setPercent(40);

        List<Sale> list = Arrays.asList(sale, sale2);

        when(saleRepository.findAll()).thenReturn(list);

        List<SaleDTO> resultListDTO = saleService.getAll();

        List<Sale> resultList = resultListDTO.stream().map(saleDTO -> mapper.toEntity(saleDTO, Sale.class)).collect(Collectors.toList());

        assertEquals(list, resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    public void testAdd(){
        LocalDateTime dateOn = LocalDateTime.of(YEAR, 1, DAY, HOUR, MINUTE);
        LocalDateTime dateOff = LocalDateTime.of(YEAR, 2, DAY, HOUR, MINUTE);
        Sale newSale = new Sale();
        newSale.setId(3L);
        newSale.setDateOn(dateOn);
        newSale.setDateOff(dateOff);
        newSale.setPercent(50);
        newSale.setCategories(categories);

        //when
        when(saleRepository.save(sale)).thenReturn(sale);

        //then
        SaleDTO resultSaleDTO = saleService.add(saleDTO);
        //convert saleDTO into sale
        Sale resultSale = mapper.toEntity(resultSaleDTO, Sale.class);
        assertEquals(sale, resultSale);
    }

    @Test
    public void testUpdate(){
        //given
        Sale updateSale = sale;
        updateSale.setPercent(35);

        //when
        when(saleRepository.save(updateSale)).thenReturn(sale);
        when(saleRepository.findById(ID)).thenReturn(sale);

        //then
        SaleDTO resultSaleDTO = saleService.update(mapper.toDTO(updateSale, SaleDTO.class));
        assertEquals(updateSale, mapper.toEntity(resultSaleDTO, Sale.class));
    }

    @Test
    public void testUpdateForIncorrectId(){
        //given
        Sale updateSale = sale;
        updateSale.setId(5L);

        //when
        when(saleRepository.save(updateSale)).thenReturn(sale);

        //then
        SaleDTO nullSaleDTO = saleService.update(mapper.toDTO(updateSale, SaleDTO.class));
        assertNull(nullSaleDTO);
    }
}
