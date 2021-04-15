package com.forleven.desafioforleven.tests.service;

import com.forleven.desafioforleven.model.dto.StudentRequest;
import com.forleven.desafioforleven.model.dto.StudentResponse;
import com.forleven.desafioforleven.model.entity.Student;
import com.forleven.desafioforleven.model.enums.StudentEnum;
import com.forleven.desafioforleven.repository.StudentRepository;
import com.forleven.desafioforleven.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

 class StudentServiceTests {

    private StudentService studentService;
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @Test
    void studentInsertSuccess() {

        StudentRequest studentRequest = new StudentRequest("123ABC",
                "João", "Guerra", StudentEnum.ACTIVE, null);
        StudentResponse studentResponse = new StudentResponse(1L, "123ABC",
                "João", "Guerra", StudentEnum.ACTIVE, null);

        Student student = new Student("123ABC", "João", "Guerra",
                StudentEnum.ACTIVE, null);
        Student student1 = new Student(1L, "123ABC", "João",
                "Guerra", StudentEnum.ACTIVE, null);

        when(studentRepository.save(student))
                .thenReturn(student1);

        StudentResponse insert = studentService.insert(studentRequest);

        verify(studentRepository, times(1)).save(student);

        Assertions.assertEquals(studentResponse, insert);
    }

    @Test
    void studentInsertFail() {

        StudentRequest studentRequest = new StudentRequest("123ABC",
                "João", "Guerra", StudentEnum.ACTIVE, null);

        Student student = new Student(1L, "123ABC", "João",
                "Guerra", StudentEnum.ACTIVE, null);

        when(studentRepository.findOne(any(Specification.class)))
                .thenReturn(Optional.of(student));

        verify(studentRepository, times(0)).save(student);

        ResponseStatusException responseStatusException = Assertions.
                assertThrows(ResponseStatusException.class, () -> {
            studentService.insert(studentRequest);
        });

        Assertions.assertEquals(409, responseStatusException.getStatus().value());
        Assertions.assertTrue(Objects.requireNonNull(responseStatusException.getMessage())
                .contains("Please change registration"));

    }

    @Test
    void updateSuccess() {
        Student student = new Student(1L, "123ABC", "João",
                "Guerra", StudentEnum.ACTIVE, null);
        StudentRequest studentRequest = new StudentRequest("123ABC",
                "João", "Guerra", StudentEnum.ACTIVE, null);

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any())).thenReturn(Optional.of(student));

        studentService.update(1L,studentRequest);

        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(studentArgumentCaptor.capture());

        Student studentResponse = studentArgumentCaptor.getValue();

        Assertions.assertEquals(student, studentResponse);
    }

     @Test
     void updateFail() {
         StudentRequest studentRequest = new StudentRequest("123ABC",
                 "João", "Guerra", StudentEnum.ACTIVE, null);

         doThrow(ResponseStatusException.class)
                 .when(studentRepository)
                 .findById(1000L);

         Assertions.assertThrows(ResponseStatusException.class, () -> {
             studentService.update(1000L, studentRequest);
         });

         verify(studentRepository, times(1)).findById(1000L);

     }

    @Test
    void deleteSuccess() {
        Student student = new Student(1L, "123ABC", "João",
                "Guerra", StudentEnum.ACTIVE, null);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.delete(1L);
        verify(studentRepository, times(1)).findById(1L);

        Assertions.assertEquals(StudentEnum.DELETED, student.getStatus());

    }

    @Test
    void deleteFail() {

        doThrow(ResponseStatusException.class)
                .when(studentRepository)
                .findById(1000L);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            studentService.delete(1000L);
        });

        verify(studentRepository, times(1)).findById(1000L);
    }

}
