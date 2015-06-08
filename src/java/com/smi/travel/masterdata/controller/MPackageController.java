package com.smi.travel.masterdata.controller;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.service.PackageTourService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class MPackageController extends SMITravelController {
    private static final ModelAndView MPackage = new ModelAndView("MPackage");
    private static final String DATALIST = "package_list";
    private static final String TransectionResult = "result";
    private static final ModelAndView MPackage_REFRESH = new ModelAndView(new RedirectView("MPackage.smi", true));
    private PackageTourService packageTourservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("PackageCodeS");
        String name = request.getParameter("PackageNameS");
        String packageID = request.getParameter("PackageID");
        String status = request.getParameter("Status");
        String result = "";
        
        PackageTour pack = new PackageTour();
        pack.setCode(code);
        pack.setName(name);
        pack.setId(packageID);
        pack.setStatus(status);
        
        if ("search".equalsIgnoreCase(action)) {
            List<PackageTour> list = packageTourservice.SearchPackage(pack, 2);
            request.setAttribute(DATALIST, list);
        } else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("packid : "+ pack.getId());
            result = packageTourservice.DeletePackage(pack);
            if (result.equalsIgnoreCase("success")) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, result);
            }
        }
        
        request.setAttribute("packageCode", code);
        request.setAttribute("packageName", name);
        request.setAttribute("status", status);
        System.out.println("action  :" + action);
        return MPackage;
    }

    public PackageTourService getPackageTourservice() {
        return packageTourservice;
    }

    public void setPackageTourservice(PackageTourService packageTourservice) {
        this.packageTourservice = packageTourservice;
    }
    
    
}
