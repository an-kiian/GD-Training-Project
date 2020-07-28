package store.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import store.dto.SaleDTO;
import store.service.SaleService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class SaleController {

    private SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/store/sales/{id}")
    public SaleDTO getById(@PathVariable Long id){
        return saleService.getById(id);
    }

    @GetMapping("/store/sales/categories")
    public List<SaleDTO> getByCategories(@RequestParam List<String> categories){
        return saleService.getByCategories(categories);
    }

    @GetMapping("/store/sales/dates")
    public List<SaleDTO> getByDate(
            @RequestParam("startDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime startDate,
            @RequestParam("finishDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime finishDate ){
        return saleService.getByDates(startDate, finishDate);
    }

    @GetMapping("/store/sales")
    public List<SaleDTO> getAll(){
        return saleService.getAll();
    }

    @PostMapping("/store/sales")
    @ResponseStatus(HttpStatus.CREATED)
    public SaleDTO add(@RequestBody SaleDTO saleDTO){
        return saleService.add(saleDTO);
    }

    @PutMapping("/store/sales")
    @ResponseStatus(HttpStatus.OK)
    public SaleDTO update(@RequestBody SaleDTO saleDTO){
        return saleService.update(saleDTO);
    }

}
