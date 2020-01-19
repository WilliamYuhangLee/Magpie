package edu.gatech.magpie.server.repository;

import edu.gatech.magpie.server.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
