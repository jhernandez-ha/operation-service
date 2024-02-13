package com.service.operationService.service;

import com.service.operationService.entity.MerchantEntity;
import com.service.operationService.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public MerchantEntity createMerchant(MerchantEntity merchant) {
        return merchantRepository.save(merchant);
    }

    public List<MerchantEntity> getAllMerchants() {
        return merchantRepository.findAll();
    }
}
