package com.forleven.desafioforleven.specifications;

import com.forleven.desafioforleven.model.entity.Student;
import com.forleven.desafioforleven.model.entity.Student_;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> withRegistration(String registration){
        return ((root, criteriaQuery, builder) ->
                builder.equal(root.get(Student_.registration), registration));
    }
}
