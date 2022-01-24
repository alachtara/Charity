package pl.coderslab.charity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT COUNT(d) FROM Donation d")
    int countAll();

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Long countAllBagsQuantity();

    List<Donation> findAllByUser(User user);

//    Page<Donation> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);

}
