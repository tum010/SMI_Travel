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

public class MStaffController extends SMITravelController {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private static final ModelAndView Staff = new ModelAndView("MStaff");
    private static final ModelAndView Staff_REFRESH = new ModelAndView(new RedirectView("MStaff.smi", true));
    private MStaffService mStaffService;
    private MRoleService mRoleService;
    private MDepartmentService mDepartmentService;
    private static final String DataList = "Staff_List";
    private static final String DataLap = "staffLap";
    private static final String TransactionResult = "result";
    private static final String StaffSearch = "StaffSearch";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String name = request.getParameter("StaffName");
        String username = request.getParameter("UserName");
        String password = request.getParameter("Password");
        String position = request.getParameter("Position");
        String tel = request.getParameter("Tel");
        String car = request.getParameter("Car");
        String departmentId = request.getParameter("Department");
        String status = request.getParameter("Status");
        String createBy = request.getParameter("createBy");
        String roleId = request.getParameter("Role");
        String StaffID = request.getParameter("StaffID");
        String email = request.getParameter("Email");

        System.out.println("MStaffController - action  :" + action + " RoldId = " + roleId);
        int result = 0;
        List<SystemUser> listStaffs = null;

        SystemUser staff = new SystemUser();
        staff.setName((String.valueOf(name)).toUpperCase());
        staff.setUsername(username);
        staff.setPassword(password);
        staff.setPosition(position);
        staff.setTel(tel);
        staff.setCar(car);
        staff.setStatus(status);
        staff.setEmail(email);
        if (StringUtils.isNotEmpty(roleId)) {
            staff.setRole(mRoleService.getroleFromID(roleId));
        }
        if (StringUtils.isNotEmpty(departmentId)) {
            staff.setMDepartment(mDepartmentService.getDepartmentById(departmentId));
        }
        staff.setCreateBy(createBy);

        List<Role> listRoles = mRoleService.getRole();
        request.setAttribute("Role_List", listRoles);
        List<MDepartment> listDepartments = mDepartmentService.getDepartment();
        request.setAttribute("Dept_List", listDepartments);

        if ("search".equalsIgnoreCase(action)) {
            log.info("Staff searching...");
            request.setAttribute(StaffSearch, staff);
            listStaffs = mStaffService.searchSystemUser(staff, 2);
            request.setAttribute(DataList, listStaffs);

        } else if ("add".equalsIgnoreCase(action)) {
            log.info("Staff add function");
            SystemUser validateStaff = new SystemUser();
            validateStaff.setUsername(username);
            String validateList = mStaffService.validateSystemUser(validateStaff, "add");
            if ("".equalsIgnoreCase(validateList)) {
                staff.setMDepartment(mDepartmentService.getDepartmentById(departmentId));
                staff.setRole(mRoleService.getroleFromID(roleId));
                result = mStaffService.insertSystemUser(staff);
                if (result == 1) {
                    request.setAttribute(TransactionResult, "save successful");
                    listStaffs = mStaffService.searchSystemUser(staff, result);
                    System.out.println("Search in add " + listStaffs.size());
                    request.setAttribute(DataList, listStaffs);
                } else {
                    request.setAttribute(TransactionResult, "save unsuccessful");
                }
            } else {
                request.setAttribute(TransactionResult, validateList);
            }

        } else if ("update".equalsIgnoreCase(action)) {
            log.info("Staff update function");
            StaffID = request.getParameter("StaffID");
            SystemUser validateStaff = new SystemUser();
            validateStaff.setUsername(username);
            validateStaff.setId(StaffID);
            System.out.println("staff id :" + StaffID);
            String validateList = mStaffService.validateSystemUser(validateStaff, "update");
            if (!"".equalsIgnoreCase(validateList)) {
                request.setAttribute(TransactionResult, validateList);
            } else {
                staff.setId(StaffID);
                staff.setMDepartment(mDepartmentService.getDepartmentById(departmentId));
                staff.setRole(mRoleService.getroleFromID(roleId));
                result = mStaffService.updateSystemUser(staff);
                if (result == 1) {
                    request.setAttribute(TransactionResult, "save successful");
                    request.setAttribute(DataList, mStaffService.searchSystemUser(staff, 1));
                } else {
                    request.setAttribute(TransactionResult, "save unsuccessful");
                }
                
                // Update Menu in case of update role.
                String sessionUsername = (String) session.getAttribute("username");
                if (sessionUsername.equalsIgnoreCase(staff.getUsername())) {
                    analyzeRoleMap(staff.getRole(), session);
                }
            }
        } else if ("delete".equalsIgnoreCase(action)) {
            log.info("Delete Staff is not supported. Please check with Administrator");
        } else {
            staff.setName(name);
            staff.setUsername(username);
            staff.setPassword(password);
            staff.setPosition(position);
            staff.setTel(tel);
            staff.setCar(car);
            staff.setStatus(status);
            staff.setCreateBy(createBy);
            staff.setRole(null);
            staff.setMDepartment(null);
        }

        request.setAttribute("staffName", name);
        request.setAttribute("position", position);
        request.setAttribute("roleId", roleId);
        request.setAttribute("departmentId", departmentId);
        request.setAttribute("status", status);

        return Staff;
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

    public MStaffService getMStaffService() {
        return mStaffService;
    }

    public void setMStaffService(MStaffService mStaffService) {
        this.mStaffService = mStaffService;
    }

    public MRoleService getmRoleService() {
        return mRoleService;
    }

    public void setmRoleService(MRoleService mRoleService) {
        this.mRoleService = mRoleService;
    }

    public MDepartmentService getmDepartmentService() {
        return mDepartmentService;
    }

    public void setmDepartmentService(MDepartmentService mDepartmentService) {
        this.mDepartmentService = mDepartmentService;
    }

}
