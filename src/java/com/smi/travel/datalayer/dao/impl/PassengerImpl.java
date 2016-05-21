/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;
 
import com.smi.travel.datalayer.dao.PassengerDao;
import com.smi.travel.datalayer.dao.impl.CustomerImpl;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Passenger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class PassengerImpl implements PassengerDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Override
    public List<Passenger> getPassengerFromRefno(String refno) {
        String query = "from Passenger pass where pass.master.referenceNo = :refno order by orderNo ";
        Session session = this.sessionFactory.openSession();

        List<Passenger> PassengerList = session.createQuery(query).setParameter("refno", refno).list();
        if (PassengerList.isEmpty()) {
            return null;
        }
        return PassengerList;
    }

    @Override
    public Passenger getPassengerFromID(String PassengerID) {
        String query = "from Passenger pass where pass.id = :passengerid";
        Session session = this.sessionFactory.openSession();
        Passenger passenger = new Passenger();
        List<Passenger> PassengerList = session.createQuery(query).setParameter("passengerid", PassengerID).list();
        if (PassengerList.isEmpty()) {
            return null;
        } else {
            passenger = PassengerList.get(0);
        }
        return passenger;
    }

    @Override
    public String saveBookingPassenger(Passenger passenger, String cuscode) {

        String customercode = "";
        int result = 0;
        try {
            System.out.println("master :" + passenger.getMaster());
            if (passenger.getMaster() != null) {
                System.out.println(passenger.getMaster().getId());
            } else {
                System.out.println("master is null");
            }
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ((passenger.getCustomer().getId() != null)) {
                session.update(passenger.getCustomer());
                customercode = passenger.getCustomer().getCode();
            } else {

                passenger.getCustomer().setCode(cuscode);
                session.save(passenger.getCustomer());
            }

            if (passenger.getId() == null) {
                session.save(passenger);
            } else {
                session.update(passenger);
            }

            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        if (result == 1) {
            return customercode;
        } else {
            return null;
        }
    }

    @Override
    public String saveFamilyleader(Passenger passenger, String cuscode) {
        String customercode = "";
        String hqlUpdateCustomer = "UPDATE Customer cus set cus.firstName = :firstName , cus.lastName = :lastName , cus.address = :address , cus.tel = :tel" + " WHERE cus.id = :cusid";
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();

            transaction = session.beginTransaction();
            if ((passenger.getCustomer() != null) && (passenger.getCustomer().getId() != null)) {
                System.out.println("update customer ");
                Customer cus = passenger.getCustomer();
                Query query = session.createQuery(hqlUpdateCustomer).setParameter("firstName", cus.getFirstName())
                        .setParameter("lastName", cus.getLastName())
                        .setParameter("address", cus.getAddress())
                        .setParameter("tel", cus.getTel())
                        .setParameter("cusid", cus.getId());
                query.executeUpdate();
                customercode = passenger.getCustomer().getCode();
            } else {

                System.out.println("save customer");
                passenger.getCustomer().setCode(cuscode);
                session.save(passenger.getCustomer());
            }

            if (passenger.getId() == null) {
                System.out.println("save passenger");
                passenger.setIsLeader(1);
                session.save(passenger);
            } else {
                System.out.println("update passenger");
                passenger.setIsLeader(1);
                session.update(passenger);
            }

            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        if (result == 1) {
            return customercode;
        } else {
            return null;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int DeletePassenger(Passenger passenger) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Passenger p = (Passenger)session.load(Passenger.class, passenger.getId());
            session.delete(p);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;

    }

    @Override
    public List<Passenger> checkPassenger(Passenger passenger) {
        String query = "from Passenger pass where pass.customer.code = :code and pass.master.id = :masterId and pass.isLeader = 1 ";
        List<Passenger> passengerList = new ArrayList<Passenger>();
        Session session = this.sessionFactory.openSession();
        try {
            passengerList = session.createQuery(query)
                    .setParameter("code", passenger.getCustomer().getCode())
                    .setParameter("masterId", passenger.getMaster().getId())
                    .list();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            session.close();
        }
        
        return passengerList;
    }

}
