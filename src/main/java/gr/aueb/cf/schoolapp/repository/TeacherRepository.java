package gr.aueb.cf.schoolapp.repository;

import gr.aueb.cf.schoolapp.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository                                                                // If we don't "extends" the JpaRepository for some reason, then the @Repository is Obligatory! SOS!
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByVat(String vat);                // In order to avoid NullPointerException in case the vat does not exist.

    Optional<Teacher> findByUuid(UUID uuid);

    @EntityGraph(attributePaths = {"region"})                  // Εππειδη στο model του  Teacher  ειναι LAZY δεν φερνει και το region! Οταν όμως θέλουμε για κάποιο λόγο να φερουμε και το region τοτε η λύση είναι μέσα στο repository το @EntityGraph.
    Page<Teacher> findAllByDeletedFalse(Pageable pageable);    // when page is returned then the use of Pageable is mandatory in order for it to work and return the page.

    Optional<Teacher> findByVatDeletedFalse(String vat);

    Optional<Teacher> findByUuidAndDeletedFalse(UUID uuid);
}
