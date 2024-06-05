package me.wane.jpa.relationship_mapping.many_to_many;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@IdClass(EnrollmentId.class)
@Entity
public class Enrollment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate enrollmentDate;

    // getters and setters
}
