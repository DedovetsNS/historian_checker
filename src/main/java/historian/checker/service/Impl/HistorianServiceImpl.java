package historian.checker.service.Impl;

import historian.checker.exception.AlreadyExistException;
import historian.checker.exception.NotFoundException;
import historian.checker.model.Historian;
import historian.checker.repository.HistorianRepository;
import historian.checker.service.HistorianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class HistorianServiceImpl implements HistorianService {

    private final HistorianRepository historianRepository;

    @Autowired
    public HistorianServiceImpl(HistorianRepository historianRepository) {
        this.historianRepository = historianRepository;
    }


    @Transactional
    @Override
    public Boolean checkSpecificAccess(String firstName, String lastName) {
        return historianRepository.existsByFirstNameAndLastNameAndSpecificLibraryAccess(
                firstName, lastName, true);
    }

    @Transactional
    @Override
    public Set<Historian> findAllWithAccess() {
        return historianRepository.findAllBySpecificLibraryAccessEquals(true);
    }

    @Transactional
    @Override
    public Historian add(Historian historian) {
        if (historianRepository.existsByFirstNameAndLastName(historian.getFirstName(), historian.getLastName())) {
            throw new AlreadyExistException("Historian", "full name", historian.getFirstName() + " " + historian.getLastName());
        }
        return historianRepository.save(historian);
    }

    @Transactional
    @Override
    public Set<Historian> findAll() {
        return historianRepository.findAll();
    }


    @Override
    public Historian findById(Long id) {
        return historianRepository.findById(id).orElseThrow(() -> new NotFoundException("Historian", "id", id.toString()));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        historianRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Historian update(Historian historian) {
        Historian updatableHistorian = findById(historian.getId());
        updatableHistorian.setFirstName(historian.getFirstName());
        updatableHistorian.setLastName(historian.getLastName());
        updatableHistorian.setAddress(historian.getAddress());
        updatableHistorian.setPhone(historian.getPhone());
        updatableHistorian.setEmail(historian.getEmail());
        updatableHistorian.setSpecificLibraryAccess(historian.getSpecificLibraryAccess());
        return historianRepository.save(updatableHistorian);
    }
}



