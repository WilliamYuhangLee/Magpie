package edu.gatech.magpie.server.controller;

import edu.gatech.magpie.server.dto.AccountDto;
import edu.gatech.magpie.server.request.AccountRequest;
import edu.gatech.magpie.server.request.PasswordChangeRequest;
import edu.gatech.magpie.server.response.Response;
import edu.gatech.magpie.server.service.AccountService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  public Response signUp(@RequestBody @Valid AccountRequest request) {
    if (accountService.signUp(
        new AccountDto().setUsername(request.getUsername()).setPassword(request.getPassword()))) {
      return Response.withStatus(HttpStatus.NO_CONTENT);
    } else {
      return Response.withStatus(HttpStatus.BAD_REQUEST).addError("Unable to register.");
    }
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Response login(@RequestBody @Valid AccountRequest request) {
    if (accountService.authenticate(request.getUsername(), request.getPassword())) {
      return Response.withStatus(HttpStatus.NO_CONTENT);
    } else {
      return Response.withStatus(HttpStatus.UNAUTHORIZED).addError("Invalid credentials.");
    }
  }

  @RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
  public Response changePassword(@RequestBody @Valid PasswordChangeRequest request) {
    if (accountService.changePassword(
        request.getUsername(), request.getOldPassword(), request.getNewPassword())) {
      return Response.withStatus(HttpStatus.NO_CONTENT);
    } else {
      return Response.withStatus(HttpStatus.BAD_REQUEST).addError("Unable to change password.");
    }
  }
}
