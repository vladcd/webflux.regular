package com.vladcarcu.webflux.regular.repository;

import com.vladcarcu.webflux.regular.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    Flux<Student> findByNameContainingAllIgnoreCase(String name);
}
