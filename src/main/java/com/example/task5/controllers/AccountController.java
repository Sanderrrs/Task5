package com.example.task5.controllers;

import com.example.task5.models.requests.AccountRequest;
import com.example.task5.models.responses.CreateAccountResponse;
import com.example.task5.servicies.CreateAccountService;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;


@RestController
@RequestMapping("/corporate-settlement-account")
public class AccountController {


    private CreateAccountService accountService;

    @Autowired
    public void setAccountService(CreateAccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create")
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest,
                                                               BindingResult bindingResult) {

        var accountResponse = new CreateAccountResponse() ;
        HttpStatus httpStatus;

        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getFieldErrors() ) {
                accountResponse.setErrorMsg(accountResponse.getErrorMsg() + " " + error.getField() + " - " + error.getDefaultMessage() +'\n');
            }
            httpStatus = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(httpStatus).body(accountResponse);
        }

        try {
            accountResponse = accountService.createAccount(accountRequest);
            httpStatus = HttpStatus.OK;
        }
        catch (IllegalArgumentException e) {
            accountResponse.setErrorMsg(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        catch (NoResultException e) {
            accountResponse.setErrorMsg(e.getMessage());
            httpStatus = HttpStatus.NOT_FOUND;
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            accountResponse.setErrorMsg(e.getMessage() +'\n'+ sw);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        // Возвращаем ответ на поступивший запрос
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(accountResponse);

    }

}
