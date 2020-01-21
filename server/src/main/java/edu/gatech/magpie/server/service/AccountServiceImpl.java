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
  public boolean authenticate(String username, String password) {
    Account account = accountRepository.findById(username).orElse(null);
    if (account == null) {
      return false;
    }
    return passwordEncoder.matches(password, account.getPassword());
  }

  @Override
  public boolean changePassword(String username, String oldPassword, String newPassword) {
    Account account = accountRepository.findById(username).orElse(null);
    if (account == null) {
      return false;
    }
    if (!passwordEncoder.matches(oldPassword, account.getPassword())) {
      return false;
    }
    account.setPassword(passwordEncoder.encode(newPassword));
    accountRepository.saveAndFlush(account);
    return true;
  }
}
