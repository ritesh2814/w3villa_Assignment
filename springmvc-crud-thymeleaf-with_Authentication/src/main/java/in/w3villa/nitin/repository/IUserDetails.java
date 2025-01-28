package in.w3villa.nitin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.w3villa.nitin.model.UserDetails;


public interface IUserDetails extends JpaRepository<UserDetails, Integer> {
	Optional<UserDetails> findByUsername(String username);


}
