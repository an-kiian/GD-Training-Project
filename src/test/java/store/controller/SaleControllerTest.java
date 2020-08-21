package store.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import store.dto.SaleDTO;
import store.service.SaleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaleControllerTest {

    @InjectMocks
    private SaleController saleController;

    @Mock
    private SaleService saleService;

    private SaleDTO saleDTO;

    private List<String> categories;

    private List<SaleDTO> saleDTOList;

    private static final Long ID = 1L;

    private static final Long idParameter = null;

    private static final LocalDate saleDateParameter = null;

    private static final List<String> categoriesParameter = null;

    @Before
    public void setUp(){
        //given
        LocalDate dateOn = LocalDate.of(2020, 3, 1);
        LocalDate dateOff = LocalDate.of(2020, 5, 1);
        categories = new ArrayList<>();
        categories.add("First Category");
        categories.add("Second Category");
        saleDTO = new SaleDTO(dateOn, dateOff, 25, categories);
        saleDTO.setId(ID);

        saleDTOList = Collections.singletonList(saleDTO);
    }

    @Test
    public void testGetIfAllParametersMissing(){
        //given
        LocalDate dateOn = LocalDate.of(2020, 2, 1);
        LocalDate dateOff = LocalDate.of(2020, 6, 1);
        categories.add("Third Category");
        SaleDTO saleDTO2 = new SaleDTO(dateOn, dateOff, 30, categories);
        saleDTO2.setId(2L);
        List<SaleDTO> list = Arrays.asList(saleDTO, saleDTO2);

        //when
        when(saleService.get(idParameter, saleDateParameter, categoriesParameter)).thenReturn(list);

        //then
        List<SaleDTO> resultList = saleController.get(idParameter, saleDateParameter, categoriesParameter);

        //testing the method is calling
        verify(saleService).get(idParameter, saleDateParameter, categoriesParameter);

        //checking correct data
        assertEquals(list, resultList);
    }
    @Test
    public void testGetIfPassIdParameter(){
        //when
        when(saleService.get(ID, saleDateParameter, categoriesParameter)).thenReturn(Collections.singletonList(saleDTO));

        //then
        List<SaleDTO> resultSaleDTO = saleController.get(ID, saleDateParameter, categoriesParameter);

        //testing the method is calling
        verify(saleService).get(ID, saleDateParameter, categoriesParameter);

        //checking correct data
        assertEquals(Collections.singletonList(saleDTO), resultSaleDTO);
    }

    @Test
    public void testGetIfPassSaleDateParameter(){
        //given
        LocalDate localDate = LocalDate.of(2020, 3, 1);

        //when
        when(saleService.get(idParameter, localDate, categoriesParameter)).thenReturn(saleDTOList);

        //then
        List<SaleDTO> resultSaleDTO = saleController.get(idParameter, localDate, categoriesParameter);

        //testing the method is calling
        verify(saleService).get(idParameter, localDate, categoriesParameter);

        //checking correct data
        assertEquals(saleDTOList, resultSaleDTO);
    }

    @Test
    public void testGetIfPassCategoriesParameter(){
        //when
        when(saleService.get(idParameter, saleDateParameter, categories)).thenReturn(saleDTOList);

        //then
        List<SaleDTO> resultSaleDTO = saleController.get(idParameter, saleDateParameter, categories);

        //testing the method is calling
        verify(saleService).get(idParameter, saleDateParameter, categories);

        //checking correct data
        assertEquals(saleDTOList, resultSaleDTO);
    }

    @Test
    public void testGetIfPassIdAndSaleDateParameters(){
        //given
        LocalDate localDate = LocalDate.of(2020, 3, 1);

        //when
        when(saleService.get(ID, localDate, categoriesParameter)).thenReturn(saleDTOList);

        //then
        List<SaleDTO> resultSaleDTO = saleController.get(ID, localDate, categoriesParameter);

        //testing the method is calling
        verify(saleService).get(ID, localDate, categoriesParameter);

        //checking correct data
        assertEquals(saleDTOList, resultSaleDTO);
    }


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

    @Test
    public void testAdd(){
        //when
        when(saleService.add(saleDTO)).thenReturn(saleDTO);

        //then
        SaleDTO resultSaleDTO = saleController.add(saleDTO);

        //testing the method is calling
        verify(saleService).add(saleDTO);

        //checking correct data
        assertEquals(saleDTO, resultSaleDTO);
    }

    @Test
    public void testUpdate(){
        //given
        SaleDTO updateSaleDTO = new SaleDTO();
        updateSaleDTO.setId(ID);
        updateSaleDTO.setPercent(15);

        //when
        when(saleService.update(updateSaleDTO)).thenReturn(updateSaleDTO);

        //then
        SaleDTO resultSaleDTO = saleController.update(updateSaleDTO);

        //testing the method is calling
        verify(saleService).update(updateSaleDTO);

        //checking correct data
        assertEquals(updateSaleDTO, resultSaleDTO);
    }
}
