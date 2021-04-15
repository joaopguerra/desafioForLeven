package com.forleven.desafioforleven.tests.service;

import com.forleven.desafioforleven.model.dto.StudentRequest;
import com.forleven.desafioforleven.model.dto.StudentResponse;
import com.forleven.desafioforleven.model.entity.Phone;
import com.forleven.desafioforleven.model.entity.Student;
import com.forleven.desafioforleven.repository.StudentRepository;
import com.forleven.desafioforleven.service.StudentService;
import com.forleven.desafioforleven.specifications.StudentSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class StudentServiceTests {

    private StudentService studentService;
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void studentInsertSuccess() {
        Phone p1 = new Phone("123465");
        Phone p2 = new Phone("987654");
        List<Phone> phones = Arrays.asList(p1, p2);

        StudentRequest studentRequest = new StudentRequest("123ABC",
                "João", "Guerra", phones);
        StudentResponse studentResponse = new StudentResponse(1L, "123ABC",
                "João", "Guerra", phones);

        Student student = new Student("123ABC", "João", "Guerra", phones);
        Student student1 = new Student(1L, "123ABC", "João", "Guerra", phones);

        when(studentRepository.save(student))
                .thenReturn(student1);

        StudentResponse insert = studentService.insert(studentRequest);

        verify(studentRepository, times(1)).save(student);

        Assertions.assertEquals(studentResponse, insert);
    }

    @Test
    public void studentInsertFail() {
        Phone p1 = new Phone("123465");
        Phone p2 = new Phone("987654");
        List<Phone> phones = Arrays.asList(p1, p2);

        StudentRequest studentRequest = new StudentRequest("123ABC","João",
                "Guerra", phones);
        StudentResponse studentResponse = new StudentResponse(1L, "123ABC","João",
                "Guerra", phones);


        Student student = new Student("123ABC", "João", "Guerra", phones);
        Student student1 = new Student(1L, "123ABC", "João", "Guerra", phones);

        when(studentRepository.save(student))
                .thenReturn(student1);

        Specification<Student> spec = StudentSpecification.withRegistration(student.getRegistration());

        studentRepository.findOne(spec).isPresent();


    }

    @Test
    public void deleteSuccess() {
        Phone p1 = new Phone("123465");
        Phone p2 = new Phone("987654");
        List<Phone> phones = Arrays.asList(p1, p2);

        Student student = new Student(1L, "123ABC", "João", "Guerra", phones);

        studentService.delete(student.getId());


    }


}
