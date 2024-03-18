package com.example.task5.controllers;

import com.example.task5.models.requests.ProductRequest;
import com.example.task5.models.responses.ProductResponse;
import com.example.task5.servicies.ProductService;
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
@RequestMapping("/corporate-settlement-instance")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest,
                         BindingResult bindingResult)
    {
        var productResponse = new ProductResponse();
        HttpStatus httpStatus;

        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getFieldErrors() ) {
                productResponse.setErrorMsg(productResponse.getErrorMsg() + " " + error.getField() + " - " + error.getDefaultMessage() +'\n');
            }
            httpStatus = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(httpStatus).body(productResponse);
        }

        try {
            productResponse = productService.saveRequest(productRequest);
            httpStatus = HttpStatus.OK;
        }catch (IllegalArgumentException e) {
            productResponse.setErrorMsg(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        catch (NoResultException e) {
            productResponse.setErrorMsg(e.getMessage());
            httpStatus = HttpStatus.NOT_FOUND;
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            productResponse.setErrorMsg(e.getMessage() +'\n'+ sw);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }


        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(productResponse);

    }


}
