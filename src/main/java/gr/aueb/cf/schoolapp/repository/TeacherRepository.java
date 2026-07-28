package gr.aueb.cf.schoolapp.repository;

import gr.aueb.cf.schoolapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

//@Repository                                                // If we don't "extends" the JpaRepository for some reason, then the @Repository is Obligatory! SOS!
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByVat(String vat);                // In order to avoid NullPointerException in case the vat does not exist.

    Optional<Teacher> findByUuid(UUID uuid);
}
