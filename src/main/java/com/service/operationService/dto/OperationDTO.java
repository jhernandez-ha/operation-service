package com.service.operationService.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OperationDTO {

    private Double quantity;
    private Long merchantId;
}
