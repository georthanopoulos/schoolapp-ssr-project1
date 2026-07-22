package gr.aueb.cf.schoolapp.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private String vat;


    private String firstname;                                                      //Αν δεν βαλουμε τίποτα. εννοούνται τα default settings του JPA, δηλαδή: @Column(name = "first_name", nullable = true, unique = false, updatable = true)
    private String lastname;                                                       //As above.

    @Setter(AccessLevel.PACKAGE)
    @ManyToOne(fetch = FetchType.LAZY)                                             //Note that Teacher is the owning side. The side that controls the Foreign key (RULE: Always this side is the "Many"-side and ALSO the owning-side).
    @JoinColumn(name = "region_id")                                                //At "teachers" table a column named "region_id" will exist (FK towards "regions" table).
    private Region region;

    @PrePersist                                                                    //JPA lifecycle callback: this method runs automatically, just before the entity gets inserted (insert) for the first time. ("If it has no UUID, create a random new now").
    public void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Teacher teacher)) return false;
        return Objects.equals(getVat(), teacher.getVat());                         //NullPointerException-safe, if a Vat == null.
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getVat());
    }
}
