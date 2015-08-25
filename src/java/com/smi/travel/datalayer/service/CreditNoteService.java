package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.CreditNoteDao;
import com.smi.travel.datalayer.entity.CreditNote;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;

/**
 *
 * @author nanthachaip
 */
public class CreditNoteService {
    
    private CreditNoteDao creditNoteDao;

    public CreditNote getCreditNote(String creditNo) {
        return creditNoteDao.getCreditNoteFromCNNo(creditNo);
    }

    /**
     * @return the creditNoteDao
     */
    public CreditNoteDao getCreditNoteDao() {
        return creditNoteDao;
    }

    /**
     * @param creditNoteDao the creditNoteDao to set
     */
    public void setCreditNoteDao(CreditNoteDao creditNoteDao) {
        this.creditNoteDao = creditNoteDao;
    }

    public String saveCreditNote(CreditNote cn) {
        String status = "";
        String cnNo = "";
        if(cn.getId() != null && !cn.getId().equals("")){
            status = this.creditNoteDao.updateCreditNote(cn);
            cnNo = cn.getCnNo();
        }else{
            cn.setCnNo(this.creditNoteDao.gennarateTaxInvoiceNo());
            cnNo = cn.getCnNo();
            MFinanceItemstatus itemStatus = new MFinanceItemstatus();
            itemStatus.setId("1");
            cn.setMFinanceItemstatus(itemStatus);
            status = this.creditNoteDao.insertCreditNote(cn);
        }
        if ("fail".equals(status)) {
            return status;
        } else {
            return cnNo;
        }
    }

    public String UpdateFinanceStatusCreditNote(String cnId, int status) {
        return this.creditNoteDao.UpdateFinanceStatusCreditNote(cnId, status);
    }
    
}