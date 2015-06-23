package com.smi.travel.masterdata.controller;
import com.smi.travel.controller.LoginController;
import com.smi.travel.datalayer.entity.MDepartment;
import com.smi.travel.datalayer.entity.Role;
import com.smi.travel.datalayer.entity.RoleMapping;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.MDepartmentService;
import com.smi.travel.datalayer.service.MRoleService;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/**
 *
 * @author chonnasith
 */
public class MBankController extends SMITravelController {
    
    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private static final ModelAndView Bank = new ModelAndView("MBank");
    private static final ModelAndView Bank_REFRESH = new ModelAndView(new RedirectView("MBank.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return Bank;
    }
    
}
