package me.wane.jpa.relationship_mapping.many_to_many;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
public class EnrollmentId implements Serializable {

    private Long student;
    private Long course;

}
