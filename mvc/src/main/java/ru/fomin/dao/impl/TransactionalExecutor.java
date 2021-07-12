package ru.fomin.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TransactionalExecutor implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public <T> T executeTransaction(Function<Session, T> function) {
        Session session = applicationContext.getBean(Session.class);
        Transaction transaction = session.beginTransaction();
        T result = function.apply(session);
        transaction.commit();
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
