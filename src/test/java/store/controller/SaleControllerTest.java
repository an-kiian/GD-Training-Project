//package store.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import store.dto.SaleDTO;
//import store.service.SaleService;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class SaleControllerTest {
//
//    @InjectMocks
//    private SaleController saleController;
//
//    @Mock
//    private SaleService saleService;
//
//    private SaleDTO saleDTO;
//
//    private List<String> categories;
//
//    private List<SaleDTO> saleDTOList;
//
//    public static final Long ID = 1L;
//
//    @Before
//    public void setUp(){
//        //given
//        LocalDateTime dateOn = LocalDateTime.of(2020, 3, 1, 0, 0);
//        LocalDateTime dateOff = LocalDateTime.of(2020, 5, 1, 0, 0);
//        categories = new ArrayList<>();
//        categories.add("First Category");
//        categories.add("Second Category");
//        saleDTO = new SaleDTO(dateOn, dateOff, 25, categories);
//        saleDTO.setId(ID);
//
//        saleDTOList = Collections.singletonList(saleDTO);
//    }
//
//    @Test
//    public void testGetById(){
//        //when
//        when(saleService.getById(ID)).thenReturn(saleDTO);
//
//        //then
//        SaleDTO resultSaleDTO = saleController.getById(ID);
//
//        //testing the method is calling
//        verify(saleService).getById(ID);
//
//        //checking correct data
//        assertEquals(saleDTO, resultSaleDTO);
//    }
//
//    @Test
//    public void testGetByCategories(){
//        //when
//        when(saleService.getByCategories(categories)).thenReturn(saleDTOList);
//
//        //then
//        List<SaleDTO> resultList = saleController.getByCategories(categories);
//
//        //testing the method is calling
//        verify(saleService).getByCategories(categories);
//
//        //checking correct data
//        assertEquals(saleDTOList, resultList);
//
//    }
//
//    @Test
//    public void testGetByDate(){
//        //when
//        when(saleService.getByDates(saleDTO.getDateOn(), saleDTO.getDateOff())).thenReturn(saleDTOList);
//
//        //then
//        List<SaleDTO> resultList = saleController.getByDate(saleDTO.getDateOn(), saleDTO.getDateOff());
//
//        //testing the method is calling
//        verify(saleService).getByDates(saleDTO.getDateOn(), saleDTO.getDateOff());
//
//        //checking correct data
//        assertEquals(saleDTOList, resultList);
//    }
//
//    @Test
//    public void testGetAll(){
//        //given
//        LocalDateTime dateOn = LocalDateTime.of(2020, 2, 1, 0, 0);
//        LocalDateTime dateOff = LocalDateTime.of(2020, 6, 1, 0, 0);
//        categories.add("Third Category");
//        SaleDTO saleDTO2 = new SaleDTO(dateOn, dateOff, 30, categories);
//        List<SaleDTO> list = Arrays.asList(saleDTO, saleDTO2);
//
//        //when
//        when(saleService.getAll()).thenReturn(list);
//
//        //then
//        List<SaleDTO> resultList = saleController.getAll();
//
//        //testing the method is calling
//        verify(saleService).getAll();
//
//        //checking correct data
//        assertEquals(list, resultList);
//    }
//
//    @Test
//    public void testAdd(){
//        //when
//        when(saleService.add(saleDTO)).thenReturn(saleDTO);
//
//        //then
//        SaleDTO resultSaleDTO = saleController.add(saleDTO);
//
//        //testing the method is calling
//        verify(saleService).add(saleDTO);
//
//        //checking correct data
//        assertEquals(saleDTO, resultSaleDTO);
//    }
//
//    @Test
//    public void testUpdate(){
//        //given
//        SaleDTO updateSaleDTO = new SaleDTO();
//        updateSaleDTO.setId(ID);
//        updateSaleDTO.setPercent(15);
//
//        //when
//        when(saleService.update(updateSaleDTO)).thenReturn(updateSaleDTO);
//
//        //then
//        SaleDTO resultSaleDTO = saleController.update(updateSaleDTO);
//
//        //testing the method is calling
//        verify(saleService).update(updateSaleDTO);
//
//        //checking correct data
//        assertEquals(updateSaleDTO, resultSaleDTO);
//    }
//}
