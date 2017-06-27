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
    public ModelAndView firstpage(){
        return new ModelAndView(new RedirectView("index"));
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        List<HelpersInfoDAO> helpersInfoDAOList = new ArrayList<HelpersInfoDAO>();

        ModelAndView mv = new ModelAndView("IndexPage");
        mv.addObject("helpersInfoDAOList", getHelperInfo("", ""));
        return mv;
    }

    @RequestMapping("/helperscategories")
    public ModelAndView helperscategories(@RequestParam("s") String s){

        ModelAndView mv = new ModelAndView("IndexPage");
        mv.addObject("title", "Search Result: " + s);
        mv.addObject("helpersInfoDAOList", getHelperInfo(s, "hi_category"));
        return mv;
    }

    public static List<HelpersInfoDAO> getHelperInfo(String search, String type){
        List<HelpersInfoDAO> helpersInfoDAOList = new ArrayList<HelpersInfoDAO>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            if (search.equals("")){
                stmt = conn.prepareStatement("SELECT `hi_id`, `hi_username`, `hi_password`, " +
                        "`hi_fullname`, `hi_image`, `hi_dateofbirth`, `hi_placeofbirth`, " +
                        "`hi_languageknown`, `hi_jobexperience`, `hi_races`, `hi_religion`, `hi_category`, `hi_servicecharge` " +
                        "FROM `helpersinfo_tbl` WHERE 1");
            }else{
                stmt = conn.prepareStatement("SELECT `hi_id`, `hi_username`, `hi_password`, " +
                        "`hi_fullname`, `hi_image`, `hi_dateofbirth`, `hi_placeofbirth`, " +
                        "`hi_languageknown`, `hi_jobexperience`, `hi_races`, `hi_religion`, `hi_category`, `hi_servicecharge`  " +
                        "FROM `helpersinfo_tbl` WHERE "+type+" = ?");
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
    public ModelAndView viewbiodata(@RequestParam("id") String id){
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
            @RequestParam("totaldays") String totaldays){

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
}
