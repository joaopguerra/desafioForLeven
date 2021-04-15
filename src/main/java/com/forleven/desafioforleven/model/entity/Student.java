package com.forleven.desafioforleven.model.entity;

import com.forleven.desafioforleven.model.enums.StudentEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity(name = "student")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "registration", unique = true)
    private String registration;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "status")
    private StudentEnum status;

    @ElementCollection
    @CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "student_id"))
    @Fetch(FetchMode.JOIN)
    private List<Phone> phones;

    public Student(String registration, String name, String lastName, StudentEnum status, List<Phone> phones) {
        this.registration = registration;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.phones = phones;
    }


}
