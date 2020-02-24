package historian.checker.service;

import historian.checker.model.Historian;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface HistorianService {
    @Transactional
    Boolean checkSpecificAccess(String firstName, String lastName);

    @Transactional
    Set<Historian> findAllWithAccess();

    @Transactional
    Historian add(Historian historian);

    @Transactional
    Set<Historian> findAll();

    Historian findById(Long id);

    @Transactional
    void deleteById(Long id);

    @Transactional
    Historian update(Historian historian);
}
