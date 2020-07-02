package com.vladcarcu.webflux.regular.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    private Long id;

    private String street;
}
