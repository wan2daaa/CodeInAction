package me.wane.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

//@Entity(name = "MemberEntity")
@Table(
//        name = "member-entity",
//        catalog = "jpa",
//        schema ="jpa",
        uniqueConstraints = { //alter table member_entity add constraint name_age_unique unique (name, age);
                @UniqueConstraint(
                        name = "name_age_unique",
                        columnNames = {"name", "age"}
                )
        }
)
public class MemberEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //id bigint not null auto_increment
    @GeneratedValue(strategy = GenerationType.AUTO)  // insert into member_entity_seq values ( 1 )
    private Long id;

//    @Column(name = "name", nullable = false, length = 10) //name varchar(10) not null
    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //created_date datetime(6)
    private Date createdDate;

    private LocalDate lastModifiedDate; //last_modified_date date

    @Lob
    private String description;


    public enum RoleType {
        USER, ADMIN
    }

}
