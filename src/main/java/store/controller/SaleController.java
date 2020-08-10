package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import store.dto.SaleDTO;
import store.service.SaleService;

import java.time.LocalDateTime;
import java.util.List;

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
            @RequestParam(value = "saleDate",required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime saleDate,
            @RequestParam(value = "categories", required = false)
                    List<String> categories){
        return saleService.get(id, saleDate, categories);

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
