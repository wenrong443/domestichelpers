package com.wenrong.boutique.controller;

import com.wenrong.boutique.utils.DBConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
 * Created by wenro on 7/1/2017.
 */
@Controller
public class ChitChatController {
    @RequestMapping("displaychitchat")
    public static ModelAndView displaychitchat(HttpSession session) {

        if (session.getAttribute("ci_id") == null && session.getAttribute("hi_id") == null){
            return new ModelAndView(new RedirectView("index?msg=Please%20login%20before%20messaging."));
        }


        if (session.getAttribute("ci_id") != null && session.getAttribute("hi_id") == null){
            ModelAndView mv = new ModelAndView("DisplayChitChatPage");
            mv.addObject("sender_id", "c" +session.getAttribute("ci_id"));
            return mv;
        }else{
            ModelAndView mv = new ModelAndView("DisplayChitChatPageForHelper");
            mv.addObject("sender_id", "m" +session.getAttribute("hi_id"));
            return mv;
        }


    }

    @RequestMapping("chitchatdescription")
    public static ModelAndView chitchatdescription(HttpSession session) {
            ModelAndView mv = new ModelAndView("ChitChatDescriptionPage");
            return mv;
    }

    @RequestMapping("retrievechitchatlist")
    @ResponseBody
    public static String retrievechitchatlist(@RequestParam("id") String id, HttpSession session) {
        String msg = "";
        List<String> nameList = new ArrayList<String>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT `cc_sender_id`, `cc_receiver_id` FROM `chitchat_tbl` WHERE `cc_sender_id` = ? OR `cc_receiver_id` = ? ORDER BY cc_datetime DESC");
            stmt.setString(1, id);
            stmt.setString(2, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("cc_sender_id").equals(id)) {
                    if (!nameList.contains(rs.getString("cc_receiver_id"))){
                        msg += "<button class=\"btn btn-primary col-md-12\" onclick=\"changeReceiverId('" + rs.getString("cc_receiver_id") + "')\">";
                        msg += checkReceiverName(rs.getString("cc_receiver_id"));
                        msg += "</button><br><br>";
                        nameList.add(rs.getString("cc_receiver_id"));
                    }

                }

                if (rs.getString("cc_receiver_id").equals(id)) {
                    if (!nameList.contains(rs.getString("cc_sender_id"))){
                    msg += "<button class=\"btn btn-primary col-md-12\" onclick=\"changeReceiverId('" + rs.getString("cc_sender_id") + "')\">";
                    msg += checkReceiverName(rs.getString("cc_sender_id"));
                    msg += "</button><br><br>";
                    nameList.add(rs.getString("cc_sender_id"));
                    }
                }
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

        return msg;
    }

    public static String checkReceiverName(String id) {
        System.out.println("Check receiver name:" + id);
        String msg = "";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            if (id.contains("m")) {
                stmt = conn.prepareStatement("SELECT `hi_fullname` FROM `helpersinfo_tbl` WHERE `hi_id` = ?");
            } else {
                stmt = conn.prepareStatement("SELECT `ci_fullname` FROM `customerinfo_tbl` WHERE `ci_id` = ?");
            }

            stmt.setString(1, id.replaceAll("c", "").replaceAll("m", ""));

            rs = stmt.executeQuery();

            while (rs.next()) {
                if (id.contains("m")) {
                    msg = rs.getString("hi_fullname");
                } else {
                    msg = rs.getString("ci_fullname");
                }
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

        return msg;
    }

    @RequestMapping("retrievechitchathistory")
    public static ModelAndView retrievechitchathistory(@RequestParam("sender_id") String sender_id, @RequestParam("receiver_id") String receiver_id) {
        String msg = "";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT `cc_sender_id`, `cc_receiver_id`, `cc_message`, `cc_datetime` FROM `chitchat_tbl` WHERE (`cc_sender_id` = ? AND `cc_receiver_id` = ?) " +
                    "OR (`cc_sender_id` = ? AND `cc_receiver_id` = ?)  ORDER BY cc_datetime ");
            stmt.setString(1, sender_id);
            stmt.setString(2, receiver_id);
            stmt.setString(3, receiver_id);
            stmt.setString(4, sender_id);

            rs = stmt.executeQuery();

            while (rs.next()) {

                if (rs.getString("cc_sender_id").equals(sender_id)) {
                    msg += "<tr><td style=\"float: right;\"><small>" + checkReceiverName(rs.getString("cc_sender_id")) + " " + rs.getString("cc_datetime") + "</small><br>" +
                            rs.getString("cc_message") + "</tr>";
                } else {
                    msg += "<tr><td style=\"float: left;\"><small>" + checkReceiverName(rs.getString("cc_sender_id")) + " " + rs.getString("cc_datetime") + "</small><br>" +
                            rs.getString("cc_message") + "</tr>";
                }
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

        ModelAndView mv = new ModelAndView("DisplayChitChatHistoryPage");
        mv.addObject("msg", msg);
        return mv;
    }

    @RequestMapping(value = "sendchitchat", method = RequestMethod.GET)
    public static ModelAndView sendchitchat(
            @RequestParam("sender") String sender,
            @RequestParam("receiver") String receiver,
            @RequestParam("message") String message, HttpSession session) {

        if (session.getAttribute("ci_id") == null && session.getAttribute("hi_id") == null){
            return new ModelAndView(new RedirectView("index?msg=Please%20login%20before%20messaging."));
        }

        System.out.print("Sender is:" + sender);

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `chitchat_tbl`(`cc_sender_id`, `cc_receiver_id`, `cc_message`) VALUES (?, ?, ?)");

            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            stmt.setString(3, message);

            int last_inserted_id = 0;

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

        return new ModelAndView(new RedirectView("displaychitchat"));
    }

}
