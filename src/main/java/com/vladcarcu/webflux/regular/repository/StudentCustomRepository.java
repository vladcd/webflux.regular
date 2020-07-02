package com.vladcarcu.webflux.regular.repository;

import com.vladcarcu.webflux.regular.model.Address;
import com.vladcarcu.webflux.regular.model.Student;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class StudentCustomRepository {

    private final ConnectionFactory connectionFactory;

    private static Student mapStudent(Row row, RowMetadata rowMetadata) {
        return Student.builder()
                .id(row.get("id", Long.class))
                .name(row.get("name", String.class))
                .location(row.get("location", String.class))
                .address(
                        Address.builder()
                                .id(row.get("address_id", Long.class))
                                .street(row.get("street", String.class))
                                .build()
                )
                .build();
    }

    public Flux<Student> findAll() {
        var client = DatabaseClient.create(connectionFactory);
        return client.execute("""
                select s.id, s.name, s.location, a.id as address_id, a.street 
                from student s 
                left join address a 
                    on s.address_id = a.id  
                """)
                .map(StudentCustomRepository::mapStudent)
                .all();
    }

    public Flux<Student> findByNameContainingAllIgnoreCase(String name) {
        var client = DatabaseClient.create(connectionFactory);
        return client.execute("""
                select s.id, s.name, s.location, a.id as address_id, a.street from student s 
                left join address a 
                    on s.address_id = a.id  
                where lower(s.name) like :name
                """)
                .bind("name", String.format("%%%s%%", name.toLowerCase()))
                .map(StudentCustomRepository::mapStudent)
                .all();
    }

}
