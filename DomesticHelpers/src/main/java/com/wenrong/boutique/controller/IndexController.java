package com.wenrong.boutique.controller;

import com.wenrong.boutique.dao.HelpersInfoDAO;
import com.wenrong.boutique.dao.HelpersTypeDAO;
import com.wenrong.boutique.utils.DBConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
public class IndexController {
    @RequestMapping("/")
    public ModelAndView firstpage() {
        return new ModelAndView(new RedirectView("index"));
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        List<HelpersInfoDAO> helpersInfoDAOList = new ArrayList<HelpersInfoDAO>();

        ModelAndView mv = new ModelAndView("IndexPage");
        mv.addObject("helpersInfoDAOList", getHelperInfo("", ""));
        return mv;
    }

    @RequestMapping("/helperscategories")
    public ModelAndView helperscategories(@RequestParam("s") String s) {

        ModelAndView mv = new ModelAndView("IndexPage");
        mv.addObject("title", "Search Result: " + s);
        mv.addObject("helpersInfoDAOList", getHelperInfo(s, "hi_category"));
        return mv;
    }

    public static List<HelpersInfoDAO> getHelperInfo(String search, String type) {
        List<HelpersInfoDAO> helpersInfoDAOList = new ArrayList<HelpersInfoDAO>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            if (search.equals("")) {
                stmt = conn.prepareStatement("SELECT `hi_id`, `hi_username`, `hi_password`, " +
                        "`hi_fullname`, `hi_image`, `hi_dateofbirth`, `hi_placeofbirth`, " +
                        "`hi_languageknown`, `hi_jobexperience`, `hi_races`, `hi_religion`, `hi_category`, `hi_servicecharge` " +
                        "FROM `helpersinfo_tbl` WHERE 1");
            } else {
                stmt = conn.prepareStatement("SELECT `hi_id`, `hi_username`, `hi_password`, " +
                        "`hi_fullname`, `hi_image`, `hi_dateofbirth`, `hi_placeofbirth`, " +
                        "`hi_languageknown`, `hi_jobexperience`, `hi_races`, `hi_religion`, `hi_category`, `hi_servicecharge`  " +
                        "FROM `helpersinfo_tbl` WHERE " + type + " = ?");
                stmt.setString(1, search);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                HelpersInfoDAO helpersInfoDAO = new HelpersInfoDAO();
                helpersInfoDAO.setHi_id(rs.getString("hi_id"));
                helpersInfoDAO.setHi_username(rs.getString("hi_username"));
                helpersInfoDAO.setHi_password(rs.getString("hi_password"));
                helpersInfoDAO.setHi_fullname(rs.getString("hi_fullname"));
                helpersInfoDAO.setHi_image(rs.getString("hi_image"));
                helpersInfoDAO.setHi_dateofbirth(rs.getString("hi_dateofbirth"));
                helpersInfoDAO.setHi_placeofbirth(rs.getString("hi_placeofbirth"));
                helpersInfoDAO.setHi_languageknown(rs.getString("hi_languageknown"));
                helpersInfoDAO.setHi_jobexperience(rs.getString("hi_jobexperience"));
                helpersInfoDAO.setHi_races(rs.getString("hi_races"));
                helpersInfoDAO.setHi_religion(rs.getString("hi_religion"));
                helpersInfoDAO.setHi_category(rs.getString("hi_category"));
                helpersInfoDAO.setHi_servicecharge(new java.text.DecimalFormat("0.00").format(Double.parseDouble(rs.getString("hi_servicecharge"))));
                helpersInfoDAOList.add(helpersInfoDAO);
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
        return helpersInfoDAOList;
    }

    @RequestMapping(value = "/viewbiodata", method = RequestMethod.GET)
    public ModelAndView viewbiodata(@RequestParam("id") String id) {
        List<HelpersInfoDAO> helpersInfoDAOList = getHelperInfo(id, "hi_id");
        HelpersInfoDAO helpersInfoDAO = helpersInfoDAOList.get(0);

        ModelAndView mv = new ModelAndView("ViewBiodataPage");
        mv.addObject("helpersInfoDAO", helpersInfoDAO);
        return mv;
    }

    @RequestMapping(value = "/bookdomestichelpers", method = RequestMethod.POST)
    public ModelAndView bookdomestichelpers(
            @RequestParam("id") String id,
            @RequestParam("startdate") String startdate,
            @RequestParam("totaldays") String totaldays, HttpSession session) {

        if (session.getAttribute("ci_username") == null) {
            return new ModelAndView(new RedirectView("index?msg=Please%20login%20before%20booking."));
        }

        List<HelpersInfoDAO> helpersInfoDAOList = getHelperInfo(id, "hi_id");
        HelpersInfoDAO helpersInfoDAO = helpersInfoDAOList.get(0);

        ModelAndView mv = new ModelAndView("BillingNPaymentPage");
        mv.addObject("id", id);
        mv.addObject("startdate", startdate);
        mv.addObject("totaldays", totaldays);
        mv.addObject("helpersInfoDAO", helpersInfoDAO);
        mv.addObject("total", new java.text.DecimalFormat("0.00").format(Double.parseDouble(String.valueOf(Double.parseDouble(totaldays) * Double.parseDouble(helpersInfoDAO.getHi_servicecharge())))));
        return mv;
    }

    @RequestMapping(value = "/reviewnconfirmation", method = RequestMethod.POST)
    public ModelAndView reviewnconfirmation(
            @RequestParam("id") String id,
            @RequestParam("startdate") String startdate,
            @RequestParam("totaldays") String totaldays,
            @RequestParam("email") String email, @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("address") String address, @RequestParam("country") String country,
            @RequestParam("state") String state, @RequestParam("city") String city,
            @RequestParam("zipcode") String zipcode, HttpSession session) {

        if (session.getAttribute("ci_username") == null) {
            return new ModelAndView(new RedirectView("index?msg=Please%20login%20before%20booking."));
        }

        List<HelpersInfoDAO> helpersInfoDAOList = getHelperInfo(id, "hi_id");
        HelpersInfoDAO helpersInfoDAO = helpersInfoDAOList.get(0);

        ModelAndView mv = new ModelAndView("ReviewNConfirmationPage");
        mv.addObject("id", id);
        mv.addObject("startdate", startdate);
        mv.addObject("totaldays", totaldays);
        mv.addObject("description", "<b>1. </b>" + helpersInfoDAO.getHi_fullname() + "(" + helpersInfoDAO.getHi_category() + ", can communicate with " + helpersInfoDAO.getHi_languageknown() + ") x " + totaldays + " day(s)<br>(Start date: " + startdate + ")");
        mv.addObject("total", new java.text.DecimalFormat("0.00").format(Double.parseDouble(String.valueOf(Double.parseDouble(totaldays) * Double.parseDouble(helpersInfoDAO.getHi_servicecharge())))));
        mv.addObject("email", email);
        mv.addObject("personalname", firstname + " " + lastname);
        mv.addObject("address", address);
        mv.addObject("country", country);
        mv.addObject("state", state);
        mv.addObject("city", city);
        mv.addObject("zipcode", zipcode);
        return mv;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ModelAndView checkout(
            @RequestParam("id") String id,
            @RequestParam("startdate") String startdate,
            @RequestParam("totaldays") String totaldays,
            @RequestParam("email") String email, @RequestParam("personalname") String personalname,
            @RequestParam("address") String address, @RequestParam("country") String country,
            @RequestParam("state") String state, @RequestParam("city") String city,
            @RequestParam("zipcode") String zipcode, HttpSession session) {

        if (session.getAttribute("ci_username") == null) {
            return new ModelAndView(new RedirectView("index?msg=Please%20login%20before%20booking."));
        }

        List<HelpersInfoDAO> helpersInfoDAOList = getHelperInfo(id, "hi_id");
        HelpersInfoDAO helpersInfoDAO = helpersInfoDAOList.get(0);

        ModelAndView mv = new ModelAndView("CheckoutPage");
        mv.addObject("id", id);
        mv.addObject("startdate", startdate);
        mv.addObject("totaldays", totaldays);
        mv.addObject("total", new java.text.DecimalFormat("0.00").format(Double.parseDouble(String.valueOf(Double.parseDouble(totaldays) * Double.parseDouble(helpersInfoDAO.getHi_servicecharge())))));
        mv.addObject("email", email);
        mv.addObject("personalname", personalname);
        mv.addObject("address", address);
        mv.addObject("country", country);
        mv.addObject("state", state);
        mv.addObject("city", city);
        mv.addObject("zipcode", zipcode);
        return mv;
    }

    @RequestMapping(value = "/checkoutprocess", method = RequestMethod.POST)
    public ModelAndView checkoutprocess(
            @RequestParam("id") String id,
            @RequestParam("startdate") String startdate,
            @RequestParam("totaldays") String totaldays,
            @RequestParam("email") String email, @RequestParam("personalname") String personalname,
            @RequestParam("address") String address, @RequestParam("country") String country,
            @RequestParam("state") String state, @RequestParam("city") String city,
            @RequestParam("zipcode") String zipcode,
            @RequestParam("cardno") String cardno,
            @RequestParam("expiredate") String expiredate,
            @RequestParam("cvc") String cvc, HttpSession session) {

        if (session.getAttribute("ci_username") == null) {
            return new ModelAndView(new RedirectView("index?msg=Please%20login%20before%20booking."));
        }

        List<HelpersInfoDAO> helpersInfoDAOList = getHelperInfo(id, "hi_id");
        HelpersInfoDAO helpersInfoDAO = helpersInfoDAOList.get(0);

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `booking_tbl`(`b_id`, `b_ci_id`, `b_hi_id`, " +
                    "`b_startdate`, `b_enddate`, `b_duration`, `b_totalamount`, `b_paymentstatus`, " +
                    "`b_personalname`, `b_email`, `b_address`, `b_country`, `b_state`, `b_city`, `b_zipcode`, `b_cardno`) " +
                    "VALUES (NULL, ?, ?, ?, DATE_ADD(?, INTERVAL ? DAY), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, session.getAttribute("ci_id").toString());
            stmt.setString(2, id);
            stmt.setString(3, startdate);
            stmt.setString(4, startdate);
            stmt.setString(5, totaldays);
            stmt.setString(6, totaldays);
            stmt.setString(7, new java.text.DecimalFormat("0.00").format(Double.parseDouble(String.valueOf(Double.parseDouble(totaldays) * Double.parseDouble(helpersInfoDAO.getHi_servicecharge())))));
            stmt.setString(8, "SUCCESS");
            stmt.setString(9, personalname);
            stmt.setString(10, email);
            stmt.setString(11, address);
            stmt.setString(12, country);
            stmt.setString(13, state);
            stmt.setString(14, city);
            stmt.setString(15, zipcode);
            stmt.setString(16, cardno);

            try {
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

        ModelAndView mv = new ModelAndView("CheckoutSuccessPage");
        return mv;
    }
}

