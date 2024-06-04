package com.aegon.SpringBootStudentCrud.Entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentIdentity implements Serializable {
    private int regId;
    private int rollNo;

}
