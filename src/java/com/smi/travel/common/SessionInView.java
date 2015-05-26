/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.common;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

/**
 *
 * @author Surachai
 */
public class SessionInView extends OpenSessionInViewFilter {

    @Override
    protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        session.setFlushMode(FlushMode.AUTO);
        return session;
    }

    @Override
    protected void closeSession(Session session, SessionFactory sessionFactory) {
        try {
            if (isSessionActive(session)) {
                session.flush();
            }
        } finally {
            super.closeSession(session, sessionFactory);
        }
    }

    boolean isSessionActive(Session session) {
        return session != null && session.isOpen() && session.isConnected();
    }

}
