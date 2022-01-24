package pl.coderslab.charity.service;

import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class InstitutionService {


    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }


    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }

    public Optional<Institution> get(Long id) {
        return institutionRepository.findById(id);
    }

    public void add(Institution institution) {
        institutionRepository.save(institution);
    }

    public void delete(Long id) {
        institutionRepository.deleteById(id);
    }
    public void delete(Institution institution) { institutionRepository.delete(institution);
    }


    public void update(Institution institution) {
        institutionRepository.save(institution);
    }
}
