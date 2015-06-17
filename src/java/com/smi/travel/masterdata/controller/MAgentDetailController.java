package com.smi.travel.masterdata.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MBranch;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class MAgentDetailController extends SMITravelController {
    private static final ModelAndView MAgentDetail = new ModelAndView("MAgentDetail");
    private static final ModelAndView Agent = new ModelAndView("Agent");
    private static final String ACCPAYLIST="accpay_list";
    private static final String ACCTERMLIST="accterm_list";
    private static final String BRANCHLIST="branch_list";
    
    private static final String DataLap = "agentLap";
    private static final String TransectionResult = "result";
    private static final String DATALIST = "agent_list";
    
    private AgentService agentservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("agentcode");
        String name = request.getParameter("agentname");
        String Detail = request.getParameter("Detail");
        String Address = request.getParameter("Address");
        String Tax = request.getParameter("Tax");
        String Fax = request.getParameter("Fax");
        String Email = request.getParameter("Email");
        String Web = request.getParameter("Web");
        String paybyId = request.getParameter("payby");
        String termpayId = request.getParameter("termpay");
        String Branch = request.getParameter("Branch");
        String BranchNo = request.getParameter("BranchNo");
        String Tel = request.getParameter("Tel");
        String warning = request.getParameter("warning");
        String remark = request.getParameter("remark");
        String AgentID = request.getParameter("AgentID");
        String tempCode = request.getParameter("tempagentcode");
        MBranch mBranch = new MBranch();
        MAccterm Mterm = new MAccterm();
        MAccpay Mpay = new MAccpay();
        
        if((code == null)||("null").equalsIgnoreCase(tempCode)){
            code = tempCode;
        }
        
        Agent agent = new Agent();
        agent.setCode((String.valueOf(code)).toUpperCase());
        agent.setName((String.valueOf(name)).toUpperCase());
        agent.setAddress((String.valueOf(Address)).toUpperCase());
        agent.setTaxNo(Tax);
        agent.setFax(Fax);
        agent.setEmail(Email);
        agent.setWarning(warning);
        agent.setWeb(Web);
        if(!"0".equalsIgnoreCase(paybyId)){
            Mpay.setId(paybyId);
            agent.setMAccpay(Mpay);
        }
        if(!"0".equalsIgnoreCase(termpayId)){
            Mterm.setId(termpayId);
            agent.setMAccterm(Mterm);
        }
        if(!"0".equalsIgnoreCase(Branch)){
            mBranch.setId(Branch);
            agent.setMBranch(mBranch);
        }
        
        agent.setRemark(remark);
        agent.setId(AgentID);
        agent.setBranchNo(BranchNo);
        
        agent.setTel(Tel);
        agent.setDetail(Detail);
        String resultValidate ="";
        int result =0;
        String operation ="";
        
        if ("save".equalsIgnoreCase(action)) {
            if((AgentID == null)||("".equalsIgnoreCase(AgentID))){
                operation = "add";
            }else{
                operation = "update";
            }
            resultValidate = agentservice.validateAgent(agent, operation);
            System.out.println("resultValidate  :" + resultValidate);
            System.out.println("operation  :" + operation);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                if("add".equalsIgnoreCase(operation)){
                     result = agentservice.insertAgent(agent);
                }else if("update".equalsIgnoreCase(operation)){
                     result = agentservice.updateAgent(agent);
                }
               
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DATALIST, agentservice.getListAgent(agent, 1));
                    return Agent;
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        }else if("edit".equalsIgnoreCase(action)){
            Agent agentdetail = agentservice.getAgentFromID(request.getParameter("agentid").toString());  
            request.setAttribute("disableAgentCode", "readonly");
            code = (String.valueOf(agentdetail.getCode())).toUpperCase();
            name = (String.valueOf(agentdetail.getName())).toUpperCase(); 
            Address = (String.valueOf(agentdetail.getAddress())).toUpperCase(); 
            Tax = agentdetail.getTaxNo();
            Fax = agentdetail.getFax();
            Email = agentdetail.getEmail();
            warning = agentdetail.getWarning();
            Web = agentdetail.getWeb();
            BranchNo = agentdetail.getBranchNo();
            
            if(agentdetail.getMAccterm() != null){
                termpayId = agentdetail.getMAccterm().getId();
            }
            if(agentdetail.getMAccpay() != null){
                paybyId = agentdetail.getMAccpay().getId();
            }
            if(agentdetail.getMBranch() != null){
                Branch = agentdetail.getMBranch().getId();
            }

            remark = agentdetail.getRemark();
            AgentID = agentdetail.getId();
            Tel = agentdetail.getTel();
            Detail = agentdetail.getDetail();

        }
        request.setAttribute(ACCPAYLIST, agentservice.getListMAccpay());
        request.setAttribute(ACCTERMLIST, agentservice.getListMAccterm());
        request.setAttribute(BRANCHLIST, agentservice.getListMBranch());
        request.setAttribute("agentcode", code);
        request.setAttribute("agentname", name);
        request.setAttribute("Address", Address);
        request.setAttribute("Tax", Tax);
        request.setAttribute("Fax", Fax);
        request.setAttribute("Email", Email);
        request.setAttribute("Web", Web);
        request.setAttribute("termpay", termpayId);
        request.setAttribute("payby", paybyId);
        request.setAttribute("Branch", Branch);
        request.setAttribute("BranchNo", BranchNo);
        request.setAttribute("Tel", Tel);
        request.setAttribute("warning", warning);
        request.setAttribute("remark", remark);
        request.setAttribute("AgentID", AgentID);
        request.setAttribute("Detail", Detail);
        return MAgentDetail;
    }

    public AgentService getAgentservice() {
        return agentservice;
    }

    public void setAgentservice(AgentService agentservice) {
        this.agentservice = agentservice;
    }
    
    
}
