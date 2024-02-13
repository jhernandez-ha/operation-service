package com.service.operationService.controller;

import com.service.operationService.entity.OperationEntity;
import com.service.operationService.repository.MerchantRepository;
import com.service.operationService.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private OperationService operationService;

    @Autowired
    private MerchantRepository merchantRepository;


    @PostMapping("/createOperation")
    @ResponseBody
    public ResponseEntity<OperationEntity> createOperation(@RequestBody OperationEntity operation, @RequestParam(name = "merchantId") Long merchantId, @RequestParam(name = "terminalId") Long terminalId) {
        OperationEntity createdOperation = operationService.createOperation(operation, merchantId, terminalId);
        messagingTemplate.convertAndSend("/operations", createdOperation);
        return new ResponseEntity<>(createdOperation, HttpStatus.CREATED);
    }

    @GetMapping("/listOperations")
    @ResponseBody
    public ResponseEntity<List<OperationEntity>> getAllOperations() {
        List<OperationEntity> operations = operationService.getAllOperations();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @GetMapping("/listTodayOperations")
    @ResponseBody
    public ResponseEntity<List<OperationEntity>> getAllOperationsFromToday() {
        List<OperationEntity> operations = operationService.getOperationsForToday();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @GetMapping("/getByMerchantId")
    @ResponseBody
    public ResponseEntity<List<OperationEntity>> getOperationsByMerchant(@RequestParam(name = "merchantId") Long merchantId) {
        List<OperationEntity> operations = operationService.getOperationsByMerchantId(merchantId);
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
}
