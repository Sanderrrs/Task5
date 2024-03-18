package com.example.task5;

import com.example.task5.controllers.ProductController;
import com.example.task5.models.responses.CreateAccountResponse;
import com.example.task5.repositories.ProductRegisterTypeRepo;
import com.example.task5.servicies.CreateAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
public class MvcAccountTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CreateAccountService accountService;

    @MockBean
    ProductRegisterTypeRepo registerTypeRepo;

    @MockBean
    ProductController productController;

    @Test
    public void accountCreateIncorrectRequestTest() throws Exception{
        mockMvc.perform(post("/corporate-settlement-account/create")
                        .contentType("application/json")
                        .content(Constants.incorrectAccountRequest()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void accountCreateCorrectRequestTest() throws Exception {
        var accountResponse = new CreateAccountResponse() ;
        accountResponse.getData().setAccountId("1");
        Mockito.when(this.accountService.createAccount(Mockito.any()))
                .thenReturn(accountResponse);

        mockMvc.perform(post("/corporate-settlement-account/create")
                        .contentType("application/json")
                        .content(Constants.correctAccountRequest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accountId").value(1));
    }

}
