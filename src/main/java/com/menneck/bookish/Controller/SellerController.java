package com.menneck.bookish.Controller;

import com.menneck.bookish.DTO.SellerDTO;
import com.menneck.bookish.Model.Seller;
import com.menneck.bookish.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Seller> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(sellerService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Seller>> findAll() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSeller(@RequestBody @Valid SellerDTO sellerDTO) {
        sellerService.create(sellerDTO);
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSeller(@PathVariable Integer id, @RequestBody @Valid SellerDTO sellerDTO) {
        sellerService.updateSeller(id, sellerDTO);
    }
}
