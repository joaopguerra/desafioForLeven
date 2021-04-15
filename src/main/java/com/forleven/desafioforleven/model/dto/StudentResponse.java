package com.forleven.desafioforleven.model.dto;

import com.forleven.desafioforleven.model.entity.Student;
import com.forleven.desafioforleven.model.entity.Phone;
import com.forleven.desafioforleven.model.enums.StudentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentResponse {

    private Long id;
    private String registration;
    private String name;
    private String lastName;
    private StudentEnum status;
    private List<Phone> phones;

    public StudentResponse(Long id) {
        this.id = id;
    }

    public static StudentResponse valueOf(Student student) {
        return new StudentResponse(student.getId(),
                student.getRegistration(),
                student.getName(),
                student.getLastName(),
                student.getStatus(),
                student.getPhones());
    }
}
