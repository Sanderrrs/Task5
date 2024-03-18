package com.example.task5.models.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CreateAccountResponse {

    private CsiData data = new CsiData();
    private String errorMsg;

    @ToString
    public class CsiData {
        @Getter
        @Setter
        private String accountId;
    }
}
