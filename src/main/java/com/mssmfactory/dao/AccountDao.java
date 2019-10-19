package com.mssmfactory.dao;

import com.google.inject.Inject;
import com.mssmfactory.domainmodel.Account;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDao implements GenericDao<Account> {

    @Inject
    private Connection connection;

    public Optional<Account> findById(Integer id) {
        DSLContext dslContext = DSL.using(this.connection, SQLDialect.POSTGRES);

        Record record = dslContext.select()
                .from(this.getTableName()).where(this.getIdColumnName() + " = " + id).fetchOne();

        if (record != null)
            return Optional.of(this.fromRecord(record));

        return Optional.empty();
    }

    public List<Account> findAll() {
        DSLContext dslContext = DSL.using(this.connection, SQLDialect.POSTGRES);
        Result<Record> result = dslContext.select().from(this.getTableName()).fetch();

        List<Account> accounts = new ArrayList<>(result.size());

        for (Record record : result)
            accounts.add(this.fromRecord(record));

        return accounts;
    }

    public void save(Account account) {
        DSLContext dslContext = DSL.using(this.connection, SQLDialect.POSTGRES);
        dslContext.insertInto(DSL.table(this.getTableName()),
                DSL.field(this.getIdColumnName()),
                DSL.field(this.getEmailColumnName()),
                DSL.field(this.getPasswordColumnName()),
                DSL.field(this.getCreditCardNumberColumnName())
        ).values(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.getCreditCardNumber()
        ).execute();
    }

    @Override
    public String getTableName() {
        return "ACCOUNT";
    }

    @Override
    public String getIdColumnName() {
        return "ID";
    }

    public String getEmailColumnName() {
        return "EMAIL";
    }

    public String getPasswordColumnName() {
        return "PASSWORD";
    }

    public String getCreditCardNumberColumnName() {
        return "CREDIT_CARD_NUMBER";
    }

    @Override
    public Account fromRecord(Record record) {
        Account account = new Account();
        account.setId((Integer) record.getValue(0));
        account.setEmail((String) record.getValue(1));
        account.setPassword((String) record.getValue(2));
        account.setCreditCardNumber((String) record.getValue(3));

        return account;
    }
}
