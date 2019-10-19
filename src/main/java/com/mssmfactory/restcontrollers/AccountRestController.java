package com.mssmfactory.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.mssmfactory.dao.AccountDao;
import com.mssmfactory.domainmodel.Account;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

@Getter
public class AccountRestController implements RestController {

    @Inject
    private AccountDao accountDao;

    @Inject
    private ObjectMapper objectMapper;

    public void initEndPoints() {
        Spark.get("account/findAll", (request, response) -> {
            List<Account> accounts = this.accountDao.findAll();
            StringWriter stringWriter = new StringWriter();

            this.objectMapper.writeValue(stringWriter, accounts);

            return stringWriter.toString();
        });

        Spark.get("account/findById", (request, response) -> {
            String accountId = request.queryParams("accountId");
            Optional<Account> accountOptional = this.accountDao.findById(Integer.parseInt(accountId));

            if (accountOptional.isPresent()) {
                StringWriter stringWriter = new StringWriter();
                this.objectMapper.writeValue(stringWriter, accountOptional.get());

                return stringWriter;
            } else
                response.redirect("/error?message=No account found");

            return null;
        });

        Spark.post("account/", "application/json", (request, response) -> {
            Account account = this.objectMapper.readValue(request.body(), Account.class);
            this.accountDao.save(account);
            response.status(HttpStatus.OK_200);
            return this.objectMapper.writeValueAsString(account);
        });
    }
}
