package com.vladcarcu.webflux.regular.repository;

import com.vladcarcu.webflux.regular.model.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {
}
