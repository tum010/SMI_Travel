/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.masterdata.controller;

import com.smi.travel.controller.LoginController;
import com.smi.travel.datalayer.entity.Function;
import com.smi.travel.datalayer.entity.Role;
import com.smi.travel.datalayer.entity.RoleMapping;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.MRoleService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author wleenavo
 */
public class MRoleController extends SMITravelController {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private static final ModelAndView Role = new ModelAndView("MRole");
    private static final ModelAndView Role_REFRESH = new ModelAndView(new RedirectView("MRole.smi", true));
    private MRoleService mRoleService;
    private static final String DataList = "Role_List";
    private static final String RoleSearch = "RoleSearch";
    private static final String FuncList = "Func_List";
    private static final String TransactionResult = "result";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String name = request.getParameter("RoleName");
        String roleID = request.getParameter("RoleID");
        System.out.println("MRoleController - action  :" + action + " name การเทส " + name);

        Role role = new Role();
        role.setId(roleID);
        role.setName(name);

        String resultValidate = "";
        int result = 0;

        List<Function> listFunc = mRoleService.getListFunction();
        request.setAttribute(FuncList, listFunc);

        if ("search".equalsIgnoreCase(action)) {
            log.info("Role searching...");
            System.out.println("Search Name " + name);
            request.setAttribute(RoleSearch, role);

            //Return datalist based on search
            List<Role> listRole = mRoleService.SerchRole(name);
            request.setAttribute(DataList, listRole);

        } else if ("add".equalsIgnoreCase(action)) {
            List<Function> newFunctions = new ArrayList<Function>();
            String[] func = request.getParameterValues("checkedFunction");
            Role r = new Role();
            r.setName(name);
            String validateList = mRoleService.ValidateRoleName(r, "add");

            if ("".equalsIgnoreCase(validateList)) {
                result = mRoleService.insertRole(role);
                List<Role> listRole = mRoleService.SerchRole(name);
                if ( func != null ) {
                    for (int i = 0; i < func.length; i++) {
                        for (int j = 0; j < listFunc.size(); j++) {
                            Function f = listFunc.get(j);
                            if (f.getId().equalsIgnoreCase(func[i])) {
                                newFunctions.add(f);
                                break;
                            }
                        }
                    }
                    RoleMapping rm = new RoleMapping();
                    rm.setRole(listRole.get(0));

                    result = mRoleService.insertRolePrivilege(rm, newFunctions);
                }
            }
            if (result == 1) {
                request.setAttribute(TransactionResult, "Add successful");
                List<Role> listRole = mRoleService.SerchRole(name);
                request.setAttribute(DataList, listRole);
            } else {
                if (!("".equalsIgnoreCase(validateList))) {
                    request.setAttribute(TransactionResult, "Add unsuccessful! " + validateList);
                } else {
                    request.setAttribute(TransactionResult, "Add unsuccessful");
                }
            }

        } else if ("update".equalsIgnoreCase(action)) {
            role.setId(roleID);
            role.setName(name);
            Role validateRole = new Role();
            validateRole.setName(name);
            validateRole.setId(roleID);
            String validateList = mRoleService.ValidateRoleName(validateRole, "update");
            if ("".equalsIgnoreCase(validateList)) {
                List<Function> newFunctions = new ArrayList<Function>();
                String[] func = request.getParameterValues("checkedFunction");
                Role r = mRoleService.getroleFromID(roleID);
                for (int i = 0; i < func.length; i++) {
                    for (int j = 0; j < listFunc.size(); j++) {
                        Function f = listFunc.get(j);
                        if (f.getId().equalsIgnoreCase(func[i])) {
                            newFunctions.add(f);
                            break;
                        }
                    }
                }
                RoleMapping rm = new RoleMapping();
                rm.setRole(r);

                result = mRoleService.insertRolePrivilege(rm, newFunctions);
                if (result == 1) {
                    result = mRoleService.updateRole(role);
                }
                
                // Update Menu in case of update role.
                SystemUser user = (SystemUser) session.getAttribute("USER");
                if (user.getRole().getName().equalsIgnoreCase(role.getName())) {
                    role = mRoleService.getroleFromID(roleID);
                    analyzeRoleMap(role, session);
                }
            } else {
                request.setAttribute(TransactionResult, validateList);
            }
            if (result == 1) {
                request.setAttribute(TransactionResult, "save successful");
            } else {

                if (!("".equalsIgnoreCase(validateList))) {
                    request.setAttribute(TransactionResult, "save unsuccessful! " + validateList);
                } else {
                    request.setAttribute(TransactionResult, "save unsuccessful");
                }
            }
            List<Role> listRole = mRoleService.SerchRole(name);
            request.setAttribute(DataList, listRole);
        } else if ("delete".equalsIgnoreCase(action)) {
            int ret = mRoleService.DeleteRole(role);
            if (ret == 1) {
                request.setAttribute(TransactionResult, "Delete successful");
            } else if (ret == 2) {
                request.setAttribute(TransactionResult, "The Role cannot be deleted, User(s) in uses.");
            } else {
                request.setAttribute(TransactionResult, "The Role cannot be deleted, Please see log.");
            }
        } else {
            role.setName(name);
        }

        request.setAttribute("roleName", name);
        
        return Role;
    }

    
    private int analyzeRoleMap(Role role, HttpSession session) {
        int result = 0;
        if (role != null) {
            Set menuActivate = role.getRoleMappings();
            String[] masterMenu = null;
            String[] bookingMenu = null;
            String[] daytourMenu = null;
            String[] financeMenu = null;
            String[] reportMenu = null;
            if (menuActivate != null) {
                masterMenu = activateMenu(menuActivate, 1);
                bookingMenu = activateMenu(menuActivate, 2);
                daytourMenu = activateMenu(menuActivate, 3);
                financeMenu = activateMenu(menuActivate, 4);
                reportMenu = activateMenu(menuActivate, 5);
            }
            Arrays.sort(masterMenu);
            Arrays.sort(reportMenu);
            session.setAttribute("mastermenu", masterMenu);
            session.setAttribute("bookingmenu", bookingMenu);
            session.setAttribute("daytourmenu", daytourMenu);
            session.setAttribute("financemenu", financeMenu);
            session.setAttribute("reportmenu", reportMenu);
        }
        result=1;
        return result;

    }

    public String[] activateMenu(Set menu, int id) {
        List<String> menuActivate = new ArrayList<String>();
        int i = 0;
        Iterator<RoleMapping> mIterator = menu.iterator();
        while (mIterator.hasNext()) {
            RoleMapping roleMap = mIterator.next();
            if (roleMap.getFunction().getMainMenu() == null) {
                continue;
            }
            int activeMenuId = Integer.parseInt(roleMap.getFunction().getMainMenu().getId());
            if (activeMenuId == id) {
                menuActivate.add(roleMap.getFunction().getName());
            }
        }
        return menuActivate.toArray(new String[menuActivate.size()]);
    }
    
    public void loadcritite() {

    }

    public void saveCritite() {

    }

    public MRoleService getmRoleService() {
        return mRoleService;
    }

    public void setmRoleService(MRoleService mRoleService) {
        this.mRoleService = mRoleService;
    }

}
