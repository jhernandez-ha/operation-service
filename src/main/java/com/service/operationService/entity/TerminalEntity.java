package com.service.operationService.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="terminal")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TerminalEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String terminalNumber;

    @Column(name = "merchant_id", nullable = false)
    private Long merchantId;

    @OneToMany(mappedBy = "terminalId", cascade = CascadeType.ALL)
    private List<OperationEntity> operations;


}
