package com.wenrong.boutique.admin;

import com.wenrong.boutique.headernfooter.HeaderAndFooter;
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

/**
 * Created by wenro on 6/27/2017.
 */
@Controller
public class AdminRelated {
    @RequestMapping("/adddnewdomestichelper")
    public ModelAndView adddnewdomestichelper(){
        ModelAndView mv = new ModelAndView("AddNewDomesticHelperPage");
        mv.addObject("helpersTypeDAOList", HeaderAndFooter.getHelperType());
        return mv;
    }

    @RequestMapping(value = "/addnewdomestichelperprocess", method = RequestMethod.POST)
    public ModelAndView addnewdomestichelperprocess(
            @RequestParam("ht_fullname") String ht_fullname,
            @RequestParam("ht_dateofbirth") String ht_dateofbirth,
            @RequestParam("ht_placeofbirth") String ht_placeofbirth,
            @RequestParam("ht_languageknown") String ht_languageknown,
            @RequestParam("ht_jobexperience") String ht_jobexperience,
            @RequestParam("ht_races") String ht_races,
            @RequestParam("ht_religion") String ht_religion,
            @RequestParam("ht_servicecharge") String ht_servicecharge,
            @RequestParam("ht_image") String ht_image,
            @RequestParam("ht_username") String ht_username,
            @RequestParam("ht_password") String ht_password,
            @RequestParam("ht_category") String ht_category
            ){

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `helpersinfo_tbl`(`hi_id`, `hi_username`, `hi_password`, " +
                    "`hi_fullname`, `hi_image`, `hi_dateofbirth`, `hi_placeofbirth`, `hi_languageknown`, " +
                    "`hi_jobexperience`, `hi_races`, `hi_religion`, `hi_category`, `hi_servicecharge`) " +
                    "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, ht_username);
            stmt.setString(2, ht_password);
            stmt.setString(3, ht_fullname);
            stmt.setString(4, ht_image);
            stmt.setString(5, ht_dateofbirth);
            stmt.setString(6, ht_placeofbirth);
            stmt.setString(7, ht_languageknown);
            stmt.setString(8, ht_jobexperience);
            stmt.setString(9, ht_races);
            stmt.setString(10, ht_religion);
            stmt.setString(11, ht_category);
            stmt.setString(12, ht_servicecharge);

//            int last_inserted_id = 0;

            try {
                stmt.execute();

//                ResultSet rs = stmt.getGeneratedKeys();
//                if (rs.next()) {
//                    last_inserted_id = rs.getInt(1);
//                }

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


        return new ModelAndView(new RedirectView("adddnewdomestichelper"));
    }
}
