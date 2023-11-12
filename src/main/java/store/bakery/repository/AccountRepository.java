package store.bakery.repository;

import store.bakery.model.entity.Account;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Long> {
    Optional<Account> findUserByUsernameAndDeletedFalse(String username);
    Optional<Account> findUserByUsernameAndPasswordAndDeletedFalse(String username, String password);
    boolean existsByUsernameAndDeletedFalse(String username);
}
