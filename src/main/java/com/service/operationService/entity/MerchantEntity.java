package com.service.operationService.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="merchant")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MerchantEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "merchantId", cascade = CascadeType.ALL)
    private List<OperationEntity> operations;

    @OneToMany(mappedBy = "merchantId", cascade = CascadeType.ALL)
    private List<TerminalEntity> terminals;
}
