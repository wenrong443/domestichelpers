package com.wenrong.boutique.helper;

import com.wenrong.boutique.controller.IndexController;
import com.wenrong.boutique.dao.AdvertisementDAO;
import com.wenrong.boutique.dao.FeedbackDAO;
import com.wenrong.boutique.utils.AsyncTaskSendEmail;
import com.wenrong.boutique.utils.DBConnection;
import com.wenrong.boutique.utils.GlobalClass;
import com.wenrong.boutique.utils.Mail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.wenrong.boutique.headernfooter.HeaderAndFooter.getHelperType;

/**
 * Created by wenro on 6/27/2017.
 */
@Controller
public class HelperRelated {
    @RequestMapping("/helperlogin")
    public ModelAndView helperlogin() {
        ModelAndView mv = new ModelAndView("HelperLoginPage");
        return mv;
    }

    @RequestMapping("/helperlogout")
    public ModelAndView helperlogout(HttpSession session) {
        session.setAttribute("hi_username", null);
        session.setAttribute("hi_id", null);
        return new ModelAndView(new RedirectView("helperlogin"));
    }

    @RequestMapping("/helperloginprocess")
    public ModelAndView helperloginprocess(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT `hi_id`, `hi_username` FROM `helpersinfo_tbl` WHERE `hi_username` = ? AND  `hi_password` = SHA1(?)");
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            while (rs.next()) {
                session.setAttribute("hi_id", rs.getString("hi_id"));
                session.setAttribute("hi_username", username);
                return new ModelAndView(new RedirectView("displaychitchat"));
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

        return new ModelAndView(new RedirectView("helperlogin?msg=Login%20failed."));
    }
}
