package edu.gatech.magpie.server.service;

import edu.gatech.magpie.server.dto.AccountDto;
import edu.gatech.magpie.server.model.Account;
import edu.gatech.magpie.server.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final PasswordEncoder passwordEncoder;

  public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public boolean signUp(AccountDto accountDto) {

    if (accountRepository.existsById(accountDto.getUsername())) {
      return false;
    }

    accountRepository.saveAndFlush(
        new Account()
            .setUsername(accountDto.getUsername())
            .setPassword(passwordEncoder.encode(accountDto.getPassword())));

    return true;
  }

  @Override
  public boolean login(AccountDto accountDto) {
    Account account = accountRepository.findById(accountDto.getUsername()).orElse(null);
    if (account == null) {
      return false;
    }
    return passwordEncoder.matches(accountDto.getPassword(), account.getPassword());
  }

  @Override
  public boolean changePassword(AccountDto accountDto) {
    Account account = accountRepository.findById(accountDto.getUsername()).orElse(null);
    if (account == null) {
      return false;
    }
    account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
    accountRepository.saveAndFlush(account);
    return true;
  }
}
