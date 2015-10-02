package com.smi.travel.masterdata.controller;
import com.smi.travel.datalayer.entity.MHost;
import com.smi.travel.datalayer.service.MHostService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class MHostController extends SMITravelController {
    private static final ModelAndView MHost = new ModelAndView("MHost");
    private static final ModelAndView MHost_REFRESH = new ModelAndView(new RedirectView("MHost.smi", true));
    private static final String DataList = "Host_List";
    private static final String DataLap = "hostLap";
    private static final String TransectionResult = "result";
    private MHostService MHostService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("HostCode");
        String name = request.getParameter("HostName");
        String address = request.getParameter("HostAddress");
        String tel = request.getParameter("HostTel");
        String fax = request.getParameter("HostFax");
        String HostID = request.getParameter("HostID");
        int result = 0;
        System.out.println("action  :" + action);
        String resultValidate = "";
        MHost host = new MHost();
        host.setCode((String.valueOf(code)).toUpperCase());
        host.setName((String.valueOf(name)).toUpperCase());
        host.setAddress((String.valueOf(address)).toUpperCase());
        host.setTel((String.valueOf(tel)).toUpperCase());
        host.setFax((String.valueOf(fax)).toUpperCase());
        host.setId(HostID);
        
        if ("search".equalsIgnoreCase(action)) {
            List<MHost> list = MHostService.searchHost(host); 
            System.out.println("Size Search host : " + list.size());
            if(list != null){
                request.setAttribute(DataList, list);
            }else{   
                request.setAttribute(DataList, null);
            }
        } else if ("save".equalsIgnoreCase(action)) {
            String resultText = "";
            
            resultValidate = MHostService.validateHost(host);
            System.out.println("Validate : " + resultValidate);
            if("".equals(resultValidate)){
                resultText = MHostService.saveHost(host);
                if("success".equals(resultText)){
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, MHostService.searchHost(host));
                }else{
                    request.setAttribute(TransectionResult, "save unsuccessful");
                    request.setAttribute(DataList, null);
                }
            }else{
                request.setAttribute(DataLap, "exist");
            }
        } else if ("delete".equalsIgnoreCase(action)) {
            resultValidate = MHostService.DeleteHost(host);
            if ("success".equals(resultValidate)) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
            List<MHost> list = MHostService.searchHost(host);
            System.out.println("Size Search host Delete : " + list.size());
            if(list != null){
                request.setAttribute(DataList, list);
            }else{   
                request.setAttribute(DataList, null);
            }
        }
        
        request.setAttribute("hostCode", code);
        request.setAttribute("hostName", name);
        return MHost;
    }

    public MHostService getMHostService() {
        return MHostService;
    }

    public void setMHostService(MHostService MHostService) {
        this.MHostService = MHostService;
    }
    
    
}
