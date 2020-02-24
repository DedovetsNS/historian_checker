package historian.checker.service.Impl;

import historian.checker.model.Historian;
import historian.checker.repository.HistorianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HistorianService {

    private final HistorianRepository historianRepository;

    @Autowired
    public HistorianService(HistorianRepository historianRepository) {
        this.historianRepository = historianRepository;
    }

    public Boolean checkSpecificAccess(String firstName, String lastName) {
        return historianRepository.existsByFirstNameAndLastNameAndSpecificLibraryAccess(
                firstName, lastName, true);
    }

    public Set<Historian> findAllWithAccess() {
        return historianRepository.findAllBySpecificLibraryAccessEquals(true);
    }
}



