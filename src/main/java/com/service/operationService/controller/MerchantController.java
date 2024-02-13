package com.service.operationService.controller;

import com.service.operationService.entity.MerchantEntity;
import com.service.operationService.repository.MerchantRepository;
import com.service.operationService.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/createMerchant")
    @ResponseBody
    public ResponseEntity<MerchantEntity> createMerchant(@RequestBody MerchantEntity merchant) {
        MerchantEntity createdMerchant = merchantService.createMerchant(merchant);
        return new ResponseEntity<>(createdMerchant, HttpStatus.CREATED);
    }

    @GetMapping("/listAllMerchants")
    @ResponseBody
    public ResponseEntity<List<MerchantEntity>> getAllMerchants() {
        List<MerchantEntity> merchants = merchantService.getAllMerchants();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }
}
