package com.wenrong.boutique.headernfooter;

import com.wenrong.boutique.dao.HelpersTypeDAO;
import com.wenrong.boutique.utils.DBConnection;
import com.wenrong.boutique.utils.GlobalClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenro on 6/26/2017.
 */

@Controller
public class HeaderAndFooter {
    @RequestMapping("/importwebpagelibrary")
    public ModelAndView importwebpagelibrary(){
        ModelAndView mv = new ModelAndView("ImportWebPageLibrary");
        return mv;
    }

    @RequestMapping("/header")
    public ModelAndView header(HttpSession session){
        if(session.getAttribute("ci_username") != null){
            ModelAndView mv = new ModelAndView("HeaderPageForLogin");
            mv.addObject("companyname", GlobalClass.companyname);
            mv.addObject("helpersTypeDAOList", getHelperType());
            mv.addObject("ci_username", session.getAttribute("ci_username"));
            return mv;
        }else{
            ModelAndView mv = new ModelAndView("HeaderPage");
            mv.addObject("companyname", GlobalClass.companyname);
            mv.addObject("helpersTypeDAOList", getHelperType());
            return mv;
        }
    }

    @RequestMapping("/headerforadmin")
    public ModelAndView headerforadmin(HttpSession session){
        ModelAndView mv = new ModelAndView("HeaderPageForAdminLogin");
        mv.addObject("companyname", GlobalClass.companyname);

        if(session.getAttribute("ai_username") != null){
            mv.addObject("ci_username", session.getAttribute("ci_username"));
        }
        return mv;
    }

    public static List<HelpersTypeDAO> getHelperType(){
        List<HelpersTypeDAO> helpersTypeDAOList = new ArrayList<HelpersTypeDAO>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT `ht_id`, `ht_type` FROM `helperstype_tbl` WHERE 1");
            rs = stmt.executeQuery();

            while (rs.next()) {
                HelpersTypeDAO helpersTypeDAO = new HelpersTypeDAO();
                helpersTypeDAO.setHt_id(rs.getString("ht_id"));
                helpersTypeDAO.setHt_type(rs.getString("ht_type"));
                helpersTypeDAOList.add(helpersTypeDAO);
            }

            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return helpersTypeDAOList;
    }

    @RequestMapping("/footer")
    public ModelAndView footer(){
        ModelAndView mv = new ModelAndView("FooterPage");
        mv.addObject("companyname", GlobalClass.companyname);
        mv.addObject("email", GlobalClass.emailAddress);
        return mv;
    }

    @RequestMapping("/contactusprocess")
    public ModelAndView contactusprocess(@RequestParam("email") String email, @RequestParam("message") String message, HttpServletRequest request){
        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `feedback_tbl`(`f_id`, `f_email`, `f_message`, `f_status`) VALUES (NULL, ?, ?, ?)");

            stmt.setString(1, email);
            stmt.setString(2, message);
            stmt.setString(3, "0");


            try {
                System.out.print("1");
                stmt.execute();

            } catch (SQLException se) {
                se.printStackTrace();
            }

            if (stmt != null) {
                stmt = null;
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(new RedirectView("index?msg=Your%20feedback%20has%20been%20submitted."));
    }


}
