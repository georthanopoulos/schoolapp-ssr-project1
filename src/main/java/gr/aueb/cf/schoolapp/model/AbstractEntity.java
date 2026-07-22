package gr.aueb.cf.schoolapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
                                                                  //This class provides auto-audit trail to any other class extends it.
@MappedSuperclass                                                 //This class is not a matrix by itself BUT its fields will be inherited as columns by each class that will extend it (extends AbstractEntity).
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)                    // (ALARM SENSOR) Enables Spring Data JPA's mechanism which "listens" the events of the entity (prior to save/update actions) and auto fills the createdAt/updateAt fields. Without this, the @CreatedDate and @LastModifiedDate wouldn't work.
public abstract class AbstractEntity {                            // The class is abstract.Cannot be "new-ed" directly. It can only become extended (extends) by other classes.

    @CreatedDate                                                                                       // Audit trail (@creation)- As soon as the entity is created, place automatically the date and time here.
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME")
    private Instant createdAt;                                                                         // Instant -> JAVA type for time instance in UTC (no timezone conflicts)

    @LastModifiedDate                                                                                  // Audit Trail (@modification) - It gets updated everytime the entity gets updated.
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME")
    private Instant updatedAt;
}
