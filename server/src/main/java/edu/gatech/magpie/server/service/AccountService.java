package edu.gatech.magpie.server.service;

import edu.gatech.magpie.server.dto.AccountDto;

public interface AccountService {

  /**
   * Perform registration with given sign up information
   *
   * @param accountDto AccountDto containing new user credentials
   * @return true if registration successful, false if failed (e.g. duplicate accounts)
   */
  boolean signUp(AccountDto accountDto);

  /**
   * Perform authenticate credential check.
   *
   * @param username String
   * @param password String
   * @return true if valid credential, false if otherwise.
   */
  boolean authenticate(String username, String password);

  boolean changePassword(String username, String oldPassword, String newPassword);
}
