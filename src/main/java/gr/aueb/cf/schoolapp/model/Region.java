package gr.aueb.cf.schoolapp.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)                 //Note that this relationship doesn't control the FK. The FK (region_id) is already being controlled by the field "region" within the Teacher class. (Note: mappedBy = "region" refers to the JAVA class field, while on the other side (Teacher) the @JoinColumn(...) refers to the DB column).
    private Set<Teacher> teachers = new HashSet<>();                          //Set because we don't want duplicates, and we don't mind the order. Immediately initializes with new HashSet<>() in order ot avoid potential NullPointerException.

    public Set<Teacher> getAllTeachers() {
        return Collections.unmodifiableSet(teachers);
    }


    //Helper Methods
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.setRegion(this);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
        teacher.setRegion(null);
    }
}
