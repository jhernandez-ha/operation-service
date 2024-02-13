package com.service.operationService.repository;

import com.service.operationService.entity.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<TerminalEntity, Long> {
}
