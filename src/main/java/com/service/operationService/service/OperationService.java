package com.service.operationService.service;

import com.service.operationService.entity.MerchantEntity;
import com.service.operationService.entity.OperationEntity;
import com.service.operationService.entity.TerminalEntity;
import com.service.operationService.repository.MerchantRepository;
import com.service.operationService.repository.OperationRepository;
import com.service.operationService.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private TerminalRepository terminalRepository;


    public OperationEntity createOperation(OperationEntity operation, Long merchantId, Long terminalId) {
        MerchantEntity merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant with id " + merchantId + " not found"));

        // Obtener todas las terminales asignadas al Merchant
        List<TerminalEntity> merchantTerminals = merchant.getTerminals();

        merchantTerminals.stream()
                .filter(terminal -> terminal.getId().equals(terminalId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Terminal with id " + terminalId + " not assigned to the Merchant"));

        operation.setMerchantId(merchantId);
        operation.setTerminalId(terminalId);
        return operationRepository.save(operation);
    }


    public List<OperationEntity> getAllOperations() {
        return operationRepository.findAll();
    }

    public List<OperationEntity> getOperationsByMerchantId(Long merchantId) {
        MerchantEntity merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant with id " + merchantId + " not found"));

        return merchant.getOperations();
    }

    public List<OperationEntity> getOperationsForToday() {
        LocalDate today = LocalDate.now();
        Date startDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return operationRepository.findByDateBetween(startDate, endDate);
    }

    public List<OperationEntity> getTodayOperationsByMerchantId(Long merchantId) {
        MerchantEntity merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant with id " + merchantId + " not found"));

        LocalDate today = LocalDate.now();
        Date startDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return merchant.getOperations().stream()
                .filter(operation -> isOperationWithinDateRange(operation, startDate, endDate))
                .collect(Collectors.toList());
    }

    public List<OperationEntity> getTodayOperationsByTerminalId(Long terminalId) {
        TerminalEntity terminal = terminalRepository.findById(terminalId)
                .orElseThrow(() -> new IllegalArgumentException("Terminal with id " + terminalId + " not found"));

        LocalDate today = LocalDate.now();
        Date startDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return terminal.getOperations().stream()
                .filter(operation -> isOperationWithinDateRange(operation, startDate, endDate))
                .collect(Collectors.toList());
    }

    private boolean isOperationWithinDateRange(OperationEntity operation, Date startDate, Date endDate) {
        Date operationDate = operation.getDate();

        return operationDate != null && operationDate.after(startDate) && operationDate.before(endDate);
    }
}
