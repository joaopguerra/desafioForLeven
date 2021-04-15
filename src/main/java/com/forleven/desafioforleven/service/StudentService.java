package com.forleven.desafioforleven.service;

import com.forleven.desafioforleven.exception.EntityNotFound;
import com.forleven.desafioforleven.model.dto.StudentRequest;
import com.forleven.desafioforleven.model.dto.StudentResponse;
import com.forleven.desafioforleven.model.entity.Student;
import com.forleven.desafioforleven.repository.StudentRepository;
import com.forleven.desafioforleven.specifications.StudentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(StudentResponse::valueOf)
                .collect(Collectors.toList());
    }

    public StudentResponse findById(Long id) {
        return repository.findById(id)
                .map(StudentResponse::valueOf)
                .orElseThrow(() -> new EntityNotFound("Student not found"));
    }

    public StudentResponse insert(StudentRequest studentRequest) {
        Student student = Student.builder()
                .name(studentRequest.getName())
                .registration(studentRequest.getRegistration())
                .lastName(studentRequest.getLastName())
                .phones(studentRequest.getPhones())
                .build();

        Specification<Student> spec = StudentSpecification.withRegistration(student.getRegistration());
        Optional<Student> student1 = repository.findOne(spec);

        if (student1.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "");
        }

        Student newEstudante = repository.save(student);

        return new StudentResponse(newEstudante.getId(),
                newEstudante.getRegistration(),
                newEstudante.getName(),
                newEstudante.getLastName(),
                newEstudante.getPhones());
    }

    public void update(Long id, StudentRequest studentRequest) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Student not found"));

        Specification<Student> spec = StudentSpecification.
                withRegistration(studentRequest.getRegistration());

        Student updatedStudent = Student.builder()
                .id(student.getId())
                .registration(studentRequest.getRegistration())
                .name(studentRequest.getName())
                .lastName(studentRequest.getLastName())
                .phones(studentRequest.getPhones())
                .build();

        repository.save(updatedStudent);
    }

    public void delete(Long id) {
        repository.findById(id)
                .ifPresent(student -> repository.deleteById(student.getId()));
    }

}
