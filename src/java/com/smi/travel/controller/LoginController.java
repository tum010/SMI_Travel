package com.smi.travel.controller;

import com.smi.travel.datalayer.dao.DaytourBookingDao;
import com.smi.travel.datalayer.entity.Role;
import com.smi.travel.datalayer.entity.RoleMapping;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingDaytourService;
import com.smi.travel.datalayer.service.LoginService;
import com.smi.travel.datalayer.service.MTourCommissionService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
 * @author Surachai
 */
public class LoginController extends SMITravelController {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private static final ModelAndView MAIN = new ModelAndView(new RedirectView("Book.smi", true));
    private static final ModelAndView LOG_IN = new ModelAndView("login");
    private LoginService loginService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("process running");
        
        if (action != null) {
            if (action.equalsIgnoreCase("login")) {
                SystemUser user = new SystemUser();
                user.setUsername(username);
                user.setPassword(password);

                SystemUser UserAuthen = getLoginService().getSystemUser(user);
                if (UserAuthen != null) {
                    session.setAttribute("USER", UserAuthen);
                }
                if (session.getAttribute("USER") != null) {
                    log.info("Login Successful with " + UserAuthen.getUsername());
                    
                    if(UserAuthen.getRole() == null){
                        request.setAttribute("ResultLogin", "Role is null.Please contact admin.");
                        log.info("Login fail!!");
                        return LOG_IN;
                    }
                    session.setAttribute("username", UserAuthen.getName());
                    session.setAttribute("rolename", UserAuthen.getRole().getName());
                    session.setAttribute("id", UserAuthen.getId());
                    Role role = UserAuthen.getRole();
                    if (UserAuthen.getStatus().equalsIgnoreCase("inactive")) {
                        request.setAttribute("ResultLogin", "Your status is inactive.Please contact admin.");
                        log.info("Login fail!!");
                        return LOG_IN;
                    }
                    
                    if (UserAuthen.getMDepartment() == null) {
                        request.setAttribute("ResultLogin", "Department is null.Please contact admin.");
                        log.info("Login fail!!");
                        return LOG_IN;
                    }
                    if (role != null) {
                        Set menuActivate = role.getRoleMappings();
                        System.out.println("menuActivate size : " + menuActivate.size());
                        String[] masterMenu = null;
                        String[] bookingMenu = null;
                        String[] daytourMenu = null;
                        String[] financeMenu = null;
                        String[] reportMenu = null;
                        String[] accountmenu = null;
                        String[] checkingMenu = null;
                        if (menuActivate != null) {
                            masterMenu = activateMenu(menuActivate, 1);
                            bookingMenu = activateMenu(menuActivate, 2);
                            daytourMenu = activateMenu(menuActivate, 3);
                            financeMenu = activateMenu(menuActivate, 4);
                            reportMenu = activateMenu(menuActivate, 5);
                            checkingMenu = activateMenu(menuActivate, 6);
                            accountmenu = activateMenu(menuActivate, 7);
                        }
                        Arrays.sort(masterMenu);
                        Arrays.sort(reportMenu);
                        
                        session.setAttribute("mastermenu", masterMenu);
                        session.setAttribute("bookingmenu", bookingMenu);
                        session.setAttribute("daytourmenu", daytourMenu);
                        session.setAttribute("financemenu", financeMenu);
                        session.setAttribute("reportmenu", reportMenu);
                        session.setAttribute("accountmenu", accountmenu);
                        session.setAttribute("checkingMenu", checkingMenu);
                    } else {
                        request.setAttribute("ResultLogin", "User have not role.Please contact admin to assign role.");
                        log.info("Login fail!!");
                        return LOG_IN;
                    }
                    return MAIN;
                } else {
                    request.setAttribute("ResultLogin", "Login fail!!");
                    log.info("Login fail!!");
                    return LOG_IN;
                }
            }
        }
        if (session.getAttribute("USER") != null) {
            return MAIN;
        } else {
            return LOG_IN;
        }
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

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

 
    
}
