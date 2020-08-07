package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import store.dto.SaleDTO;
import store.model.Sale;
import store.service.SaleService;
import store.service.impl.SaleServiceImpl;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class SaleController {

    private SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/store/sales")
    public List<SaleDTO> get(
            @RequestParam(value = "id", required = false)
                    Long id,
            @RequestParam(value = "startDate",required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime startDate,
            @RequestParam(value = "finishDate",required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime finishDate,
            @RequestParam(value = "categories", required = false)
                    List<String> categories){

        return saleService.get(id, startDate, finishDate, categories);

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
