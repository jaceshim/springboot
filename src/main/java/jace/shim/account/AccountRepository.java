package jace.shim.account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jaceshim on 2017. 2. 3..
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
