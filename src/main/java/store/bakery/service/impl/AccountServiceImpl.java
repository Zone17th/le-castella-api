package store.bakery.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.bakery.model.appEnum.RoleEnum;
import store.bakery.model.entity.Account;
import store.bakery.repository.AccountRepository;
import store.bakery.service.AccountService;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean checkExistUsername(String username) {
        return accountRepository.existsByUsernameAndDeletedFalse(username);
    }

    @Override
    public void createNew(Account account) {
        account.setRole(RoleEnum.STAFF);
        account.setDeleted(false);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public void update(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void delete(Account account) {
        account.setDeleted(true);
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsernameAndPassword(String username, String password) {
        return accountRepository.findUserByUsernameAndPasswordAndDeletedFalse(username,password);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findUserByUsernameAndDeletedFalse(username);
    }
}
