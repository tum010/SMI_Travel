/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.entity.Function;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.MBranch;
import com.smi.travel.datalayer.entity.MCreditBank;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDepartment;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MMeal;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.MProductType;
import com.smi.travel.datalayer.entity.MStockStatus;
import com.smi.travel.datalayer.entity.MTicketType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class MListItemImpl implements MListItemDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Override
    public List<MAccpay> getListMAccpay() {
        String query = "from MAccpay pay";
        Session session = this.sessionFactory.openSession();

        List<MAccpay> AccpaylList = session.createQuery(query).list();

        if (AccpaylList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return AccpaylList;
    }

    @Override
    public List<MAccterm> getListMAccterm() {
        String query = "from MAccterm";
        Session session = this.sessionFactory.openSession();

        List<MAccterm> AcctermList = session.createQuery(query).list();

        if (AcctermList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return AcctermList;
    }

    @Override
    public List<MProductType> getListMasterProductType() {
        String query = "from MProductType";
        Session session = this.sessionFactory.openSession();
        List<MProductType> ProductTypeList = session.createQuery(query).list();
        if (ProductTypeList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return ProductTypeList;
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
    public List<Function> getListFunction() {
        String query = "from Function F order by F.mainMenu.id ,  F.name";
        Session session = this.sessionFactory.openSession();
        List<Function> FunctionList = session.createQuery(query).list();
        if (FunctionList.isEmpty()) {
            return null;
        }
       // session.close();
       // this.sessionFactory.close();
        return FunctionList;
    }

    @Override
    public List<MDepartment> getListDepartment() {
        String query = "from MDepartment ";
        Session session = this.sessionFactory.openSession();

        List<MDepartment> DepartmentList = session.createQuery(query).list();

        if (DepartmentList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return DepartmentList;
    }

    public List<MBranch> getListMBranch() {
        String query = "from MBranch ";
        Session session = this.sessionFactory.openSession();
        List<MBranch> BranchList = session.createQuery(query).list();
        if (BranchList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return BranchList;
    }

    public List<MBookingstatus> getListMBookingstatus() {
        String query = "from MBookingstatus ";
        Session session = this.sessionFactory.openSession();
        List<MBookingstatus> BookStatusList = session.createQuery(query).list();
        if (BookStatusList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return BookStatusList;
    }

    @Override
    public MBookingstatus getMBookingstatusFromName(String name) {
        String query = "from MBookingstatus s where s.name = :name";
        Session session = this.sessionFactory.openSession();
        List<MBookingstatus> BookStatusList = session.createQuery(query).setParameter("name", name).list();
        if (BookStatusList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return BookStatusList.get(0);
    }

    @Override
    public List<MItemstatus> getListMItemstatus() {
        String query = "from MItemstatus ";
        Session session = this.sessionFactory.openSession();
        List<MItemstatus> ItemStatusList = session.createQuery(query).list();
        if (ItemStatusList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return ItemStatusList;
    }

    @Override
    public MItemstatus getMItemstatusFromName(String name) {
        String query = "from MItemstatus s where s.name = :name";
        Session session = this.sessionFactory.openSession();
        List<MItemstatus> List = session.createQuery(query).setParameter("name", name).list();
        if (List.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return List.get(0);
    }

    @Override
    public List<MCurrency> getListMCurrency() {
        String query = "from MCurrency cur";
        Session session = this.sessionFactory.openSession();

        List<MCurrency> CurrencyList = session.createQuery(query).list();

        if (CurrencyList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return CurrencyList;
    }

    @Override
    public List<MPricecategory> getListMPricecategory() {
        String query = "from MPricecategory Mprice";
        Session session = this.sessionFactory.openSession();

        List<MPricecategory> PriceCategoryList = session.createQuery(query).list();

        if (PriceCategoryList.isEmpty()) {
            return null;
        }

        return PriceCategoryList;
    }

    @Override
    public MPricecategory getMPricecategoryFromCode(String code) {
        String query = "from MPricecategory Mprice where Mprice.code = :code";
        Session session = this.sessionFactory.openSession();
        List<MPricecategory> list = session.createQuery(query).setParameter("code", code).list();
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<MFlight> getListMFlightClass() {
        String query = "from MFlight flight";
        Session session = this.sessionFactory.openSession();

        List<MFlight> MFlightList = session.createQuery(query).list();

        if (MFlightList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MFlightList;
    }

    @Override
    public List<MTicketType> getListMTicketType() {
        String query = "from MTicketType ticket";
        Session session = this.sessionFactory.openSession();

        List<MTicketType> MTicketTypeList = session.createQuery(query).list();

        if (MTicketTypeList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MTicketTypeList;
    }

    @Override
    public List<MMeal> getListMMeal() {
        String query = "from MMeal ticket";
        Session session = this.sessionFactory.openSession();

        List<MMeal> MMealList = session.createQuery(query).list();

        if (MMealList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MMealList;
    }

    @Override
    public MMeal getMMealFromName(String name) {
        String query = "from MMeal s where s.name = :name";
        Session session = this.sessionFactory.openSession();
        List<MMeal> list = session.createQuery(query).setParameter("name", name).list();
        if (list.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list.get(0);
    }

    @Override
    public List<MInitialname> getListMInitialname() {
        String query = "from MInitialname";
        Session session = this.sessionFactory.openSession();

        List<MInitialname> MInitialnameList = session.createQuery(query).list();

        if (MInitialnameList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MInitialnameList;

    }

    @Override
    public List<PackageTour> getListMPackage() {
        String query = "from PackageTour";
        Session session = this.sessionFactory.openSession();

        List<PackageTour> MPackageList = session.createQuery(query).list();

        if (MPackageList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MPackageList;
    }

    @Override
    public MInitialname getMInitialnameFromId(String id) {
        String query = "from MInitialname i where i.id =:id";
        Session session = this.sessionFactory.openSession();
        List<MInitialname> List = session.createQuery(query).setParameter("id", id).list();
        if (List.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return List.get(0);
    }

    @Override
    public MInitialname getMInitialnameFromName(String name) {
        String query = "from MInitialname i where i.name LIKE '"+name+"%'";
        Session session = this.sessionFactory.openSession();
        List<MInitialname> List = session.createQuery(query).list();
        if (List.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return List.get(0);

    }

    @Override
    public MBilltype getMBilltypeFromName(String name) {
        String query = "from MBilltype m where m.name =:name";
        Session session = this.sessionFactory.openSession();
        List<MBilltype> List = session.createQuery(query).setParameter("name", name).list();
        if (List.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return List.get(0);
    }
    
    @Override
    public List<MBilltype> getListMBilltype(){
        String query = "from MBilltype m";
        Session session = this.sessionFactory.openSession();
        List<MBilltype> List = session.createQuery(query).list();
        if (List.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return List;
    }

    @Override
    public List<MPaymentDoctype> getListMpaymentDocType(String department) {
        String query = "from MPaymentDoctype doc  where doc.department = :department";
        Session session = this.sessionFactory.openSession();

        List<MPaymentDoctype> MPayDocList = session.createQuery(query).setParameter("department", department).list();

        if (MPayDocList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MPayDocList;
    }

    @Override
    public List<MAirlineAgent> getListMAirLineAgent() {
         String query = "from MAirlineAgent";
        Session session = this.sessionFactory.openSession();

        List<MAirlineAgent> MAirlineAgentList = session.createQuery(query).list();

        if (MAirlineAgentList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MAirlineAgentList;
    }

    @Override
    public List<MPaytype> getListMPayType() {
        String query = "from MPaytype";
        Session session = this.sessionFactory.openSession();

        List<MPaytype> MPaytypeList = session.createQuery(query).list();

        if (MPaytypeList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MPaytypeList; 
    }

    @Override
    public List<MStockStatus> getListMStockStatus() {
        String query = "from MStockStatus";
        Session session = this.sessionFactory.openSession();

        List<MStockStatus> MStockStatusList = session.createQuery(query).list();

        if (MStockStatusList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MStockStatusList; 
    }

    @Override
    public List<MCreditBank> getListCreditBank() {
        String query = "from MCreditBank";
        Session session = this.sessionFactory.openSession();
        List<MCreditBank> MCreditBankList = session.createQuery(query).list();
        System.out.print("MCreditBankList " + MCreditBankList.size());
        if (MCreditBankList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MCreditBankList; 
    }

    @Override
    public List<MAirline> getListMAirlineCode() {
        String query = "from MAirline ma where ma.code3Letter  IS NOT NULL";
        Session session = this.sessionFactory.openSession();
        List<MAirline> MAirline = session.createQuery(query).list();
        if (MAirline.isEmpty()) {
            return null;
        }else{
            for (int i = 0; i < MAirline.size(); i++) {
                System.out.println("Code Airline : " + MAirline.get(i).getCode());
            }
        }
        session.close();
        this.sessionFactory.close();
        return MAirline; 
    }

    @Override
    public List<MFinanceItemstatus> getListMFinanceItemstatus() {
        String query = "from MFinanceItemstatus";
        Session session = this.sessionFactory.openSession();
        List<MFinanceItemstatus> mFinanceItemstatusList = session.createQuery(query).list();
        System.out.print("mFinanceItemstatusList " + mFinanceItemstatusList.size());
        if (mFinanceItemstatusList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return mFinanceItemstatusList; 
    }

    
  
}
