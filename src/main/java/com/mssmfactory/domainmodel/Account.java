package com.mssmfactory.domainmodel;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Integer id;

    private String email;
    private String password;
    private String creditCardNumber;
}
