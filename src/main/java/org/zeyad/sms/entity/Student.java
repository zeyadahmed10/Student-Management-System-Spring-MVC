package org.zeyad.sms.entity;



import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "students")
//    private List<Course> courses = new ArrayList<>();

    // Other attributes like address, phone number, etc.
}
