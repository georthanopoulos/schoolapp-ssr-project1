package gr.aueb.cf.schoolapp.repository;

import gr.aueb.cf.schoolapp.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findAllByOrderByNameAsc();    //Το springBoot ignores what's in between the "find...All"!!! I can write whatever I want so that it is more friendly and conprehensive for me.
}
