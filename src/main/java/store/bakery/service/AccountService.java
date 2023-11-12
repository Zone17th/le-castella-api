package store.bakery.service;

import store.bakery.model.entity.Account;
import store.bakery.model.entity.Store;

import java.util.Optional;

public interface AccountService {
    boolean checkExistUsername(String username);
    void createNew(Account account);
    void update(Account account);
    void delete(Account account);
    Optional<Account> findByUsernameAndPassword(String username, String password);
    Optional<Account> findByUsername(String username);
}
