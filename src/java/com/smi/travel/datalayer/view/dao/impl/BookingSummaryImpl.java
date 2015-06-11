/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.BookingSummaryDao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class BookingSummaryImpl implements BookingSummaryDao{
    private SessionFactory sessionFactory;
    
    @Override
    public List<BookSummary> getListBookSummary(String refno) {
        Session session = this.sessionFactory.openSession();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `booking_summary_view` where ref_no = "+refno)
                .addScalar("type",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("date_tour",Hibernate.DATE)
                .addScalar("price",Hibernate.INTEGER)
                .addScalar("book_date",Hibernate.DATE)
                .list();
                
        List<BookSummary> BookSummaryList =  new LinkedList<BookSummary>();
        for(Object[] B : QueryList){
            BookSummary book = new BookSummary();
            book.setRefNo(refno);
            book.setType(String.valueOf(B[0]));
            book.setDescription(String.valueOf(B[1]));
            book.setDateTour((Date) B[2]);
            book.setPrice(String.valueOf(B[3]));
            book.setBookdate((Date) B[4]);
            BookSummaryList.add(book);
            
        }
       
        if (BookSummaryList.isEmpty()) {
            return null;
        }
        session.close();
        return BookSummaryList;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
