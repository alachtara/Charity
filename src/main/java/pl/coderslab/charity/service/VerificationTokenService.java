package pl.coderslab.charity.service;

import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.repository.VerificationTokenRepository;
import java.util.List;

@Repository
public class VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    public VerificationTokenService(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public List<VerificationToken> getAll() {
        return tokenRepository.findAll();
    }

    public void add(VerificationToken token) {
        tokenRepository.save(token);
    }

    public void delete(String token) { tokenRepository.deleteByToken(token); }

    public void update(VerificationToken token) {
        tokenRepository.save(token);
    }

    VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    VerificationToken findByUser(User user) {
        return tokenRepository.findByUser(user);
    }
}
