/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.mysql.jdbc.StringUtils;
import com.smi.travel.datalayer.dao.CustomerDao;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.Master;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class CustomerImpl implements CustomerDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final int maxrow = 5000;
    private static final int Firstrow = 0;
    @Override
    public List<Customer> getListCustomer() {
        String query = "from Customer c order by c.firstName";
        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery(query);
        q.setFirstResult(Firstrow);
        q.setMaxResults(maxrow);
        List<Customer> customerList = q.list();
        session.close();
        if (customerList.isEmpty()) {
            return null;
        }
        return customerList;
    }

    @Override
    public String generateCustomerCode(String FirstCharName) {
        String query = "from Customer c where c.code like '" + FirstCharName + "%' order by c.code desc";
        String cusCode = "";
        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery(query);
        q.setFirstResult(0);
        q.setMaxResults(1);
        List<Customer> customerList = q.list();
        if (customerList.isEmpty()) {
            cusCode = FirstCharName.toUpperCase() + "0000001";
        } else {
            System.out.println("code :" + customerList.get(0).getCode());
            int nextCode = Integer.parseInt(customerList.get(0).getCode().substring(1)) + 1;
            cusCode = String.valueOf(nextCode);
            while (cusCode.length() < 7) {
                cusCode = "0" + cusCode;
            }
            cusCode = FirstCharName.toUpperCase() + cusCode;
        }
        session.close();
        this.sessionFactory.close();
        System.out.println(cusCode);
        return cusCode;
    }

    @Override
    public String insertCustomer(Customer customer) {
        int result = 0;
        customer.setCode(generateCustomerCode(customer.getFirstName().substring(0, 1)));

        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        if (result == 1) {
            return customer.getCode();
        } else {
            return null;
        }

    }

    @Override
    public String insertCustomerAjax(Customer customer) {
        String result;
        MInitialname m = null;
        String initial = null;
        Session session = this.sessionFactory.openSession();
        customer.setCode(generateCustomerCode(customer.getFirstName().substring(0, 1)));
        try {
           
            session.save(customer);
            m = (MInitialname) session.load(MInitialname.class, customer.getMInitialname().getId());
            initial = m.getName();

        } catch (Exception ex) {
            ex.printStackTrace();
           

        }
        session.close();
        String id = customer.getId();
        String code = customer.getCode();
        String fname = customer.getFirstName();
        String lname = customer.getLastName();
        String address = customer.getAddress();
        String tel = customer.getTel();
        result = id + "|" + code + "|" + initial + "|" + fname + "|" + lname + "|" + address + "|" + tel;
        return result;
    }

    @Override
    public int updateCustomer(Customer customer) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(customer);
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
    public Customer getCustomerFromID(String customerID) {
        Customer customer = new Customer();
        String query = "from Customer c where c.id= :customerID";
        Session session = this.sessionFactory.openSession();

        List<Customer> customerList = session.createQuery(query).setParameter("customerID", customerID).list();
        session.close();
        if (customerList.isEmpty()) {
            return null;
        } else {
            customer = customerList.get(0);
        }
        return customer;
    }

    @Override
    public boolean isExistCustomer(String initialID, String firstname, String lastname) {
        String query = "from Customer c where c.MInitialname.id= :initial and c.firstName = :first and c.lastName = :last";
        Session session = this.sessionFactory.openSession();

        List<Customer> customerList = session.createQuery(query)
                .setParameter("initial", initialID)
                .setParameter("first", firstname)
                .setParameter("last", lastname)
                .list();

        session.close();
        if (customerList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public List<Customer> searchCustomer(Customer customer, int option) {
        String query = "from Customer c where ";
        String queryOperation = "";
        String Prefix_Subfix = "";
        int check = 0;
        if (option == 1) {
            queryOperation = " = ";
            Prefix_Subfix = "";
        } else if (option == 2) {
            queryOperation = " Like ";
            Prefix_Subfix = "%";
        }

        if (!StringUtils.isNullOrEmpty(customer.getFirstName())) {
            query += " c.firstName " + queryOperation + " '" + Prefix_Subfix + customer.getFirstName() + Prefix_Subfix + "'";
            check = 1;
        }

        if (!StringUtils.isNullOrEmpty(customer.getLastName())) {
            if (check == 1) {
                query += " and ";
            }
            query += " c.lastName " + queryOperation + " '" + Prefix_Subfix + customer.getLastName() + Prefix_Subfix + "'";
            check = 1;
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("searchCustomer query : " + query);
        //  List<SystemUser> list = getHibernateTemplate().find(query);
        Session session = this.sessionFactory.openSession();
        List<Customer> list = session.createQuery(query).list();

        return list;

    }

    @Override
    public List<Customer> FiterCustomer(Customer customer, int filter) {
        String query = "from Customer c where ";
        String queryOperation = " Like ";
        String Prefix_Subfix = "%";
        int check = 0;
        if (filter == 1) {
            query += " c.firstName " + queryOperation + " '"  + customer.getFirstName() + Prefix_Subfix + "'";
            query += " or ";
            query += " c.lastName " + queryOperation + " '"  + customer.getLastName() + Prefix_Subfix + "'";
            query += " or ";
            query += " c.code " + queryOperation + " '"  + customer.getCode() + Prefix_Subfix + "'";

            check = 1;
        } else {
            if (!StringUtils.isNullOrEmpty(customer.getFirstName())) {
                if (check == 1) {
                    query += " and ";
                }
                query += " c.firstName " + queryOperation + " '"  + customer.getFirstName() + Prefix_Subfix + "'";
                check = 1;
            }

            if (!StringUtils.isNullOrEmpty(customer.getLastName())) {
                if (check == 1) {
                    query += " and ";
                }
                query += " c.lastName " + queryOperation + " '"  + customer.getLastName() + Prefix_Subfix + "'";
                check = 1;
            }
            
            if (!StringUtils.isNullOrEmpty(customer.getFirstName())) {
                if (check == 1) {
                    query += " or ";
                }
                query += " c.code " + queryOperation + " '"  + customer.getCode() + Prefix_Subfix + "'";
                check = 1;
            }
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("searchCustomer query : " + query);
        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery(query);
        q.setMaxResults(200);
        List<Customer> list = q.list();
        return list;

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

  

}
