package historian.checker.repository;

import historian.checker.model.Historian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HistorianRepository extends CrudRepository<Historian, Long> {

    Boolean existsByFirstNameAndLastNameAndSpecificLibraryAccess(
            String firstName,
            String lastName,
            Boolean access);

    Set<Historian> findAllBySpecificLibraryAccessEquals(Boolean access);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);

    Set<Historian> findAll();
 }
