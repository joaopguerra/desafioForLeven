package com.forleven.desafioforleven.tests.service;

import com.forleven.desafioforleven.model.dto.StudentRequest;
import com.forleven.desafioforleven.model.dto.StudentResponse;
import com.forleven.desafioforleven.model.entity.Student;
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
import static org.mockito.Mockito.doNothing;
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
                "João", "Guerra", null);
        StudentResponse studentResponse = new StudentResponse(1L, "123ABC",
                "João", "Guerra", null);

        Student student = new Student("123ABC", "João", "Guerra", null);
        Student student1 = new Student(1L, "123ABC", "João", "Guerra", null);

        when(studentRepository.save(student))
                .thenReturn(student1);

        StudentResponse insert = studentService.insert(studentRequest);

        verify(studentRepository, times(1)).save(student);

        Assertions.assertEquals(studentResponse, insert);
    }

    @Test
    void studentInsertFail() {

        StudentRequest studentRequest = new StudentRequest("123ABC",
                "João", "Guerra", null);

        Student student = new Student(1L, "123ABC", "João", "Guerra", null);

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
        Student student = new Student(1L, "123ABC", "João", "Guerra", null);
        StudentRequest studentRequest = new StudentRequest("123ABC",
                "João", "Guerra", null);

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
                 "João", "Guerra", null);

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

        doNothing().when(studentRepository).deleteById(1L);

        Assertions.assertDoesNotThrow(() -> {
            studentService.delete(1L);
        });

        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteFail() {

        doThrow(ResponseStatusException.class)
                .when(studentRepository)
                .deleteById(1000L);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            studentService.delete(1000L);
        });

        verify(studentRepository, times(1)).deleteById(1000L);
    }

}
