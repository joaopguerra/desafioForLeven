package com.forleven.desafioforleven.service;

import com.forleven.desafioforleven.exception.ConflictException;
import com.forleven.desafioforleven.exception.StudentNotFound;
import com.forleven.desafioforleven.model.dto.StudentRequest;
import com.forleven.desafioforleven.model.dto.StudentResponse;
import com.forleven.desafioforleven.model.entity.Student;
import com.forleven.desafioforleven.model.enums.StudentEnum;
import com.forleven.desafioforleven.repository.StudentRepository;
import com.forleven.desafioforleven.specifications.StudentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() ->
                        new StudentNotFound("Student not found"));
    }

    public StudentResponse insert(StudentRequest studentRequest) {
        Student student = Student.builder()
                .name(studentRequest.getName())
                .registration(studentRequest.getRegistration())
                .lastName(studentRequest.getLastName())
                .status(studentRequest.getStatus())
                .phones(studentRequest.getPhones())
                .build();

        Specification<Student> spec = StudentSpecification.withRegistration(student.getRegistration());
        Optional<Student> student1 = repository.findOne(spec);

        if (student1.isPresent()) {
            throw new ConflictException("Please change registration");
        }

        Student newEstudante = repository.save(student);

        return new StudentResponse(newEstudante.getId(),
                newEstudante.getRegistration(),
                newEstudante.getName(),
                newEstudante.getLastName(),
                newEstudante.getStatus(),
                newEstudante.getPhones());
    }

    public void update(Long id, StudentRequest studentRequest) {
        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFound("Student not found"));;

        Student updatedStudent = Student.builder()
                .id(student.getId())
                .registration(studentRequest.getRegistration())
                .name(studentRequest.getName())
                .lastName(studentRequest.getLastName())
                .status(studentRequest.getStatus())
                .phones(studentRequest.getPhones())
                .build();

        repository.save(updatedStudent);
    }

    public void delete(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFound("Student not found"));

        if (student.getStatus() == StudentEnum.DELETED) {
            throw new ConflictException("This student is already deleted");
        }

        student.setStatus(StudentEnum.DELETED);
        repository.save(student);
    }

}
