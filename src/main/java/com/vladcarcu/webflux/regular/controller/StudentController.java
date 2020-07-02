package com.vladcarcu.webflux.regular.controller;

import com.vladcarcu.webflux.regular.model.Student;
import com.vladcarcu.webflux.regular.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Mono<Student> getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/name/{name}")
    public Flux<Student> getByName(@PathVariable String name) {
        return studentService.getByName(name);
    }

    @PostMapping
    public Mono<Student> create(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping
    public Mono<Student> update(@RequestBody Student student) {
        Objects.requireNonNull(student);
        Objects.requireNonNull(student.getId());
        return studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return studentService.delete(id);
    }
}
