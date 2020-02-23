package historian.checker.repository;

import historian.checker.model.Historian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorianRepository extends CrudRepository<Historian, Long> {

    Boolean existsByFirstNameAndLastNameAndSpecificLibraryAccess(
            String firstName,
            String lastName,
            Boolean access);
}
