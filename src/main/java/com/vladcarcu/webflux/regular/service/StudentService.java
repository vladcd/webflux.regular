package com.vladcarcu.webflux.regular.service;

import com.vladcarcu.webflux.regular.model.Student;
import com.vladcarcu.webflux.regular.repository.StudentCustomRepository;
import com.vladcarcu.webflux.regular.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentCustomRepository studentCustomRepository;

    public Mono<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public Flux<Student> getAllStudents() {
        return studentCustomRepository.findAll();
    }

    public Mono<Student> save(Student student) {
        return studentRepository.save(student)
                .flatMap(s -> studentRepository.findById(s.getId()));
    }

    public Mono<Void> delete(Long id) {
        return studentRepository.deleteById(id);
    }

    public Flux<Student> getByName(String name) {
        return studentCustomRepository.findByNameContainingAllIgnoreCase(name);
    }
}
