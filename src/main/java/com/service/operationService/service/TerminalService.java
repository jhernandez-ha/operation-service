package com.service.operationService.service;

import com.service.operationService.entity.MerchantEntity;
import com.service.operationService.entity.OperationEntity;
import com.service.operationService.entity.TerminalEntity;
import com.service.operationService.repository.MerchantRepository;
import com.service.operationService.repository.OperationRepository;
import com.service.operationService.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public TerminalEntity createTerminal(TerminalEntity terminal, Long merchantId) {
        terminal.setMerchantId(merchantId);
        return terminalRepository.save(terminal);
    }


    public List<TerminalEntity> getAllTerminals() {
        return terminalRepository.findAll();
    }

    public List<TerminalEntity> getTerminalsByMerchantId(Long merchantId) {
        MerchantEntity merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant with id " + merchantId + " not found"));

        return merchant.getTerminals();
    }
}
