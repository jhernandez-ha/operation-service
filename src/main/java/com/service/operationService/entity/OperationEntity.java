package com.service.operationService.entity;

import com.service.operationService.utils.OperationBrand;
import com.service.operationService.utils.OperationType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="operation")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OperationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Double quantity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;

    private String authorizationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_brand")
    private OperationBrand operationBrand;

    @Column(name = "merchant_id", nullable = false)
    private Long merchantId;

    @Column(name = "terminal_id", nullable = false)
    private Long terminalId;
}
