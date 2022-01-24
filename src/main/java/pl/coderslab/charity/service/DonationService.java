package pl.coderslab.charity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> getAll() {
        return donationRepository.findAll();
    }

    public Optional<Donation> get(Long id) {
        return donationRepository.findById(id);
    }

    public void add(Donation donation) {
        donationRepository.save(donation);
    }

    public void delete(Long id) {
        donationRepository.deleteById(id);
    }

    public void update(Donation donation) {
        donationRepository.save(donation);
    }

    public Long countBags() { return donationRepository.countAllBagsQuantity();}

    public int numberOfDonations() { return  donationRepository.countAll();}

    public List<Donation> getUserDonationList(User user) {
        return donationRepository.findAllByUser(user);
    }

    public void changePickUpStatus(Donation donation) {
        if(donation.isPickUpStatus()) {
            donation.setPickUpStatus(false);
        } else {
            donation.setPickUpStatus(true);
            donation.setPickUpDate(LocalDate.now());
        }
       donationRepository.save(donation);
    }

    public Page<Donation> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return this.donationRepository.findAll(pageable);
    }
}
