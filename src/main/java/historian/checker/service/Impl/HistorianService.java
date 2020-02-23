package historian.checker.service.Impl;

import historian.checker.repository.HistorianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorianService {

    private final HistorianRepository historianRepository;

    @Autowired
    public HistorianService(HistorianRepository historianRepository) {
        this.historianRepository = historianRepository;
    }

    public Boolean checkLibraryAccess(String firstName, String lastName) {
        return historianRepository.existsByFirstNameAndLastNameAndSpecificLibraryAccess(
                firstName, lastName, true);
    }
}



