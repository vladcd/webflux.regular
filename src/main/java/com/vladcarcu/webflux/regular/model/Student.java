package com.vladcarcu.webflux.regular.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    private Long id;

    private String name;

    private String location;

    @Column("address_id")
    private Long addressId;

    @Transient
    private Address address;

}
