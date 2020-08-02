package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.sps.dao.IUserDao;
import com.google.sps.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/login-status")
public class LoginServlet extends HttpServlet {

    public static final String LOGGED_IN = "loggedIn";
    public static final String EMAIL = "email";
    public static final String NICK = "nick";
    public static final String URL = "url";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();

        if (userService.isUserLoggedIn()) {
            writeLoggedInResponse(resp, userService);
        } else {
            writeLoggedOutResponse(resp, userService);
        }
    }

    private void writeLoggedInResponse(HttpServletResponse resp, UserService userService) throws IOException {
        IUserDao userDao = new UserDao(userService.getCurrentUser());
        String urlToRedirectToAfterUserLogsOut = "/";
        String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

        HashMap<String, String> responseMap = new HashMap<>();

        responseMap.put(EMAIL, userDao.getEmail());
        responseMap.put(NICK, userDao.getNickName());
        responseMap.put(LOGGED_IN, "true");
        responseMap.put(URL, logoutUrl);

        Gson gson = new Gson();
        String responseJson = gson.toJson(responseMap);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(responseJson);;
    }

    private void writeLoggedOutResponse(HttpServletResponse resp, UserService userService) throws IOException {
        String urlToRedirectToAfterUserLogsIn = "/";
        String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

        HashMap<String, String> responseMap = new HashMap<>();

        responseMap.put(LOGGED_IN, "false");
        responseMap.put(URL, loginUrl);

        Gson gson = new Gson();
        String responseJson = gson.toJson(responseMap);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(responseJson);
    }
}
