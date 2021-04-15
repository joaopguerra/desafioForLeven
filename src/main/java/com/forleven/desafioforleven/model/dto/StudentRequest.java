package com.forleven.desafioforleven.model.dto;

import com.forleven.desafioforleven.model.entity.Phone;
import com.forleven.desafioforleven.model.enums.StudentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class StudentRequest {

    @NotBlank(message = "Required field")
    @Size(min = 3)
    private String registration;

    @NotBlank(message = "Required field")
    @Size(min = 3)
    private String name;

    @NotBlank(message = "Required field")
    @Size(min = 3)
    private String lastName;

    private StudentEnum status;

    private List<Phone> phones;

}
