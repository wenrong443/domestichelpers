package com.wenrong.boutique.controller;

import com.wenrong.boutique.dao.HelpersInfoDAO;
import com.wenrong.boutique.utils.DBConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wenro on 6/28/2017.
 */
@Controller
public class LoginRegisterController {
    @RequestMapping(value = "/loginprocess", method = RequestMethod.POST)
    public ModelAndView loginprocess(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session, HttpServletRequest request) {

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

                stmt = conn.prepareStatement("SELECT `ci_id`, `ci_fullname` FROM `customerinfo_tbl` WHERE `ci_username` = ? AND  `ci_password` = SHA1(?)");
                stmt.setString(1, username);
                stmt.setString(2, password);

            rs = stmt.executeQuery();

            while (rs.next()) {
                session.setAttribute("ci_id", rs.getString("ci_id"));
                session.setAttribute("ci_username", username);
                return new ModelAndView(new RedirectView("index?msg=Login%20success."));
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

        return new ModelAndView(new RedirectView("index?msg=Login%20failed."));
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView("RegisterPage");
        return mv;
    }

    @RequestMapping(value = "/registerprocess", method = RequestMethod.POST)
    public ModelAndView registerprocess(
            @RequestParam("fullname") String fullname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            HttpSession session) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `customerinfo_tbl`(`ci_id`, `ci_fullname`, `ci_username`, `ci_password`, `ci_email`) VALUES (NULL, ?, ?, SHA1(?), ?)");

            stmt.setString(1, fullname);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, email);

            int last_inserted_id = 0;

            try {
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    last_inserted_id = rs.getInt(1);
                }
                session.setAttribute("ci_id", Integer.toString(last_inserted_id));
                session.setAttribute("ci_username", username);

                return new ModelAndView(new RedirectView("index?msg=Success%20to%20create%20account."));
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

        return new ModelAndView(new RedirectView("index?msg=Failed%20to%20create%20account."));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session){
        session.setAttribute("ci_id", null);
        session.setAttribute("ci_username", null);
        return new ModelAndView(new RedirectView("index"));
    }
}
