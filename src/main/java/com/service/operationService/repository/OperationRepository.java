package com.service.operationService.repository;

import com.service.operationService.entity.MerchantEntity;
import com.service.operationService.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

    List<OperationEntity> findByDateBetween(Date startDate, Date endDate);

}
