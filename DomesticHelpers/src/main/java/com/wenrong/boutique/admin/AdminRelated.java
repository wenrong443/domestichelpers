package com.wenrong.boutique.admin;

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
public class AdminRelated {
    @RequestMapping("/adminlogin")
    public ModelAndView adminlogin() {
        ModelAndView mv = new ModelAndView("AdminLoginPage");
        mv.addObject("helpersTypeDAOList", getHelperType());
        return mv;
    }

    @RequestMapping("/adminlogout")
    public ModelAndView adminlogout(HttpSession session) {
        session.setAttribute("ai_username", null);
        return new ModelAndView(new RedirectView("adminlogin"));
    }

    @RequestMapping("/adminloginprocess")
    public ModelAndView adminloginprocess(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT `ai_id`, `ai_fullname` FROM `admininfo_tbl` WHERE `ai_username` = ? AND  `ai_password` = SHA1(?)");
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            while (rs.next()) {
                session.setAttribute("ai_id", rs.getString("ai_id"));
                session.setAttribute("ai_username", username);
                return new ModelAndView(new RedirectView("listofdomestichelpers"));
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

        return new ModelAndView(new RedirectView("adminlogin?msg=Login%20failed."));
    }

    @RequestMapping("/listofdomestichelpers")
    public ModelAndView listofdomestichelpers(HttpSession session) {
        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        ModelAndView mv = new ModelAndView("ListDomesticHelperPage");
        mv.addObject("helpersInfoDAOList", IndexController.getHelperInfo("", ""));
        return mv;
    }

    @RequestMapping("/adddnewdomestichelper")
    public ModelAndView adddnewdomestichelper(HttpSession session) {
        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        ModelAndView mv = new ModelAndView("AddNewDomesticHelperPage");
        mv.addObject("helpersTypeDAOList", getHelperType());
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
            @RequestParam("ht_category") String ht_category,
            HttpSession session
    ) {

        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `helpersinfo_tbl`(`hi_id`, `hi_username`, `hi_password`, " +
                    "`hi_fullname`, `hi_image`, `hi_dateofbirth`, `hi_placeofbirth`, `hi_languageknown`, " +
                    "`hi_jobexperience`, `hi_races`, `hi_religion`, `hi_category`, `hi_servicecharge`) " +
                    "VALUES (NULL, ?, SHA1(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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


        return new ModelAndView(new RedirectView("listofdomestichelpers"));
    }

    @RequestMapping(value = "/deletedomestichelperbiodata", method = RequestMethod.POST)
    public ModelAndView deletedomestichelperbiodata(@RequestParam("id") String id) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("DELETE FROM `helpersinfo_tbl` WHERE `hi_id` = ?");

            stmt.setString(1, id);

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

        return new ModelAndView(new RedirectView("listofdomestichelpers"));
    }

    @RequestMapping(value = "modifydomestichelperbiodata", method = RequestMethod.POST)
    public ModelAndView modifydomestichelperbiodata(@RequestParam("id") String id) {

        ModelAndView mv = new ModelAndView("ModifyNewDomesticHelperPage");
        mv.addObject("helpersInfoDAO", IndexController.getHelperInfo(id, "hi_id").get(0));
        mv.addObject("helpersTypeDAOList", getHelperType());
        return mv;
    }

    @RequestMapping(value = "/modifydomestichelperbiodataprocess", method = RequestMethod.POST)
    public ModelAndView modifydomestichelperbiodataprocess(
            @RequestParam("ht_id") String ht_id,
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
            @RequestParam("ht_category") String ht_category,
            HttpSession session
    ) {

        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            String msg = "";
            int counter = 11;
            if (!(ht_password == null || ht_password.equals(""))) {
                msg += ",`hi_password`=SHA1(?)";
            }
            if (!(ht_image == null || ht_image.equals(""))) {
                msg += ",`hi_image`=?";
            }
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("UPDATE `helpersinfo_tbl` SET `hi_username`=?,`hi_fullname`=?,`hi_dateofbirth`=?," +
                    "`hi_placeofbirth`=?,`hi_languageknown`=?,`hi_jobexperience`=?,`hi_races`=?,`hi_religion`=?," +
                    "`hi_category`=?,`hi_servicecharge`=?" + msg + " WHERE `hi_id`= ?");

            stmt.setString(1, ht_username);
            stmt.setString(2, ht_fullname);
            stmt.setString(3, ht_dateofbirth);
            stmt.setString(4, ht_placeofbirth);
            stmt.setString(5, ht_languageknown);
            stmt.setString(6, ht_jobexperience);
            stmt.setString(7, ht_races);
            stmt.setString(8, ht_religion);
            stmt.setString(9, ht_category);
            stmt.setString(10, ht_servicecharge);

            if (!(ht_password == null || ht_password.equals(""))) {
                stmt.setString(counter++, ht_password);
            }
            if (!(ht_image == null || ht_image.equals(""))) {
                stmt.setString(counter++, ht_image);
            }

            stmt.setString(counter++, ht_id);

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


        return new ModelAndView(new RedirectView("listofdomestichelpers"));
    }

    @RequestMapping("/listoffeedback")
    public ModelAndView listoffeedback(HttpSession session) {
        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        ModelAndView mv = new ModelAndView("ListFeedbackPage");
        mv.addObject("feedbackDAOList", getFeedbackDAOList(""));
        return mv;
    }

    public static List<FeedbackDAO> getFeedbackDAOList(String id) {
        List<FeedbackDAO> feedbackDAOList = new ArrayList<FeedbackDAO>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String msg = "";
            if (!(id == null || id.equals(""))){
                msg = " AND f_id = ?";
            }

            stmt = conn.prepareStatement("SELECT `f_id`, `f_email`, `f_message` FROM `feedback_tbl` WHERE `f_status` = '0' "+msg+"");
            if (!(id == null || id.equals(""))){
                stmt.setString(1, id);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                FeedbackDAO feedbackDAO = new FeedbackDAO();
                feedbackDAO.setF_id(rs.getString("f_id"));
                feedbackDAO.setF_email(rs.getString("f_email"));
                feedbackDAO.setF_message(rs.getString("f_message"));
                feedbackDAOList.add(feedbackDAO);
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
        return feedbackDAOList;
    }

    @RequestMapping("/replyfeedback")
    public ModelAndView replyfeedback(
            @RequestParam("id") String id,
            @RequestParam("replymessage") String replymessage,
            HttpSession session) {
        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        FeedbackDAO feedbackDAO = getFeedbackDAOList(id).get(0);
        try {
            Mail.Send(feedbackDAO.getF_email(), "", "Thank you for contact us", replymessage);

            try {
                Connection conn = null;
                PreparedStatement stmt = null;

                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement("UPDATE `feedback_tbl` SET `f_status`=? WHERE `f_id`=?");

                stmt.setString(1, "1");
                stmt.setString(2, id);

                try {
                    stmt.execute();
                    return new ModelAndView(new RedirectView("listoffeedback?msg=Success%20to%20reply."));
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

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return new ModelAndView(new RedirectView("listoffeedback?msg=Failed%20to%20reply."));
    }

    @RequestMapping("/listofadvertisement")
    public ModelAndView listofadvertisement(HttpSession session) {
        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        List<AdvertisementDAO> advertisementDAOList = new ArrayList<AdvertisementDAO>();

        ModelAndView mv = new ModelAndView("ListAdvertisementPage");
        mv.addObject("advertisementDAOList", getAdvertisementDAOList(""));
        return mv;
    }

    public static List<AdvertisementDAO> getAdvertisementDAOList(String id) {
        List<AdvertisementDAO> advertisementDAOList = new ArrayList<AdvertisementDAO>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String msg = "";
            if (!(id == null || id.equals(""))){
                msg = " WHERE  `a_id` = ?";
            }

            stmt = conn.prepareStatement("SELECT `a_id`, `a_title`, `a_content`, `a_image` FROM `advertisement_tbl`"+msg+"");
            if (!(id == null || id.equals(""))){
                stmt.setString(1, id);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                AdvertisementDAO advertisementDAO = new AdvertisementDAO();
                advertisementDAO.setA_id(rs.getString("a_id"));
                advertisementDAO.setA_title(rs.getString("a_title"));
                advertisementDAO.setA_content(rs.getString("a_content"));
                advertisementDAO.setA_image(rs.getString("a_image"));
                advertisementDAOList.add(advertisementDAO);
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
        return advertisementDAOList;
    }

    @RequestMapping("/adddnewadvertisement")
    public ModelAndView adddnewdomestichelper() {

        ModelAndView mv = new ModelAndView("AddNewAdvertisementPage");
        return mv;
    }

    @RequestMapping(value = "/adddnewadvertisementprocess", method = RequestMethod.POST)
    public ModelAndView addnewdomestichelperprocess(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("ht_image") String ht_image,
            HttpSession session ) {

        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `advertisement_tbl`(`a_id`, `a_title`, `a_content`, `a_image`) VALUES (NULL, ?, ?, ?)");

            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setString(3, ht_image);

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


        return new ModelAndView(new RedirectView("listofadvertisement"));
    }

    @RequestMapping(value = "/deleteadvertisement", method = RequestMethod.POST)
    public ModelAndView deleteadvertisement(@RequestParam("id") String id) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("DELETE FROM `advertisement_tbl` WHERE `a_id` = ?");

            stmt.setString(1, id);

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

        return new ModelAndView(new RedirectView("listofadvertisement"));
    }

    @RequestMapping(value = "/modifyadvertisement", method = RequestMethod.POST)
    public ModelAndView modifyadvertisement(@RequestParam("id") String id) {

        ModelAndView mv = new ModelAndView("ModifyAdvertisementPage");
        mv.addObject("advertisementDAO", getAdvertisementDAOList(id).get(0));
        return mv;
    }

    @RequestMapping(value = "/modifyadvertisementprocess", method = RequestMethod.POST)
    public ModelAndView modifyadvertisementprocess(
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("ht_image") String ht_image,
            HttpSession session
    ) {

        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }
        System.out.print("no3");
        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            String msg = "";
            int counter = 3;

            if (!(ht_image == null || ht_image.equals(""))) {
                msg += ",`a_image`=?";
            }
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("UPDATE `advertisement_tbl` SET `a_title`=?,`a_content`=?" + msg + " WHERE `a_id`= ?");

            stmt.setString(1, title);
            stmt.setString(2, content);

            if (!(ht_image == null || ht_image.equals(""))) {
                stmt.setString(counter++, ht_image);
                System.out.print("no1");
            }

            stmt.setString(counter++, id);

            System.out.println(stmt);

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


        return new ModelAndView(new RedirectView("listofadvertisement"));
    }

    @RequestMapping("/randomadvertisement")
    public ModelAndView randomadvertisement(){
        ModelAndView mv = new ModelAndView("RandomAdvertisementPage");

        List<AdvertisementDAO> advertisementDAOList = getAdvertisementDAOList("");
        Random ran = new Random();
        int x = ran.nextInt(advertisementDAOList.size()) + 0;

        mv.addObject("advertisementDAO", getAdvertisementDAOList("").get(x));
        return mv;
    }

    @RequestMapping("/sendemailtocustomer")
    public ModelAndView sendemailtocustomer(HttpSession session) {
        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }

        ModelAndView mv = new ModelAndView("AdminSendEmailPage");
        return mv;
    }

    /**
     * This function is used to send and redirect to AdminSendEmailPage
     *
     * @return AdminSendEmailPage
     */
    @RequestMapping(value = "/sendemailtocustomerprocess", method = RequestMethod.POST)
    public ModelAndView sendemailtocustomerprocess(
            @RequestParam("title") String title,
            @RequestParam("message") String message,
            HttpSession session) {

        message = message.replaceAll("\n", "<br>");

        if (session.getAttribute("ai_username") == null) {
            return new ModelAndView(new RedirectView("adminlogin"));
        }


        List<String> emailList = getListOfEmail();

        for (int i = 0; i < emailList.size(); i++) {
            AsyncTaskSendEmail asyncTaskSendEmail = new AsyncTaskSendEmail(emailList.get(i), title, message);
            asyncTaskSendEmail.start();
        }

        return new ModelAndView(new RedirectView("sendemailtocustomer"));
    }

    /**
     * This function is used to retrieve email list based on type given
     * 1 - All User(s)
     * 2 - Almost Expiring User(s)
     * 3 - Expired User(s)
     * 4 - All Valid Purchase User(s)
     *
     * @return AdminSendEmailPage
     */
    public static List<String> getListOfEmail() {
        List<String> emailList = new ArrayList<String>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT DISTINCT `ci_email` FROM `customerinfo_tbl` WHERE 1");

            rs = stmt.executeQuery();

            while (rs.next()) {
                emailList.add(rs.getString("ci_email").toLowerCase());
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

        return emailList;
    }
}
