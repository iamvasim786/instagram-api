package com.sachinhandiekar.examples;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.users.basicinfo.UserInfoData;


public class InstagramTokenHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String code = request.getParameter("code");


        InstagramService service = (InstagramService) request.getServletContext().getAttribute(Constants.INSTAGRAM_SERVICE);
        //String authorizationUrl = service.getAuthorizationUrl();
        Verifier verifier = new Verifier(code);

        Token accessToken = service.getAccessToken(verifier);
        Instagram instagram = new Instagram(accessToken);

        HttpSession session = request.getSession();

        session.setAttribute(Constants.INSTAGRAM_OBJECT, instagram);
        UserInfoData userInfoData = instagram.getCurrentUserInfo().getData(); 
        // Decrepted
        //List<MediaFeedData> mediaList = instagram.getUserFeeds().getData();
        System.out.println(userInfoData.getUsername());
        System.out.println(request.getContextPath());
        // Redirect to User Profile page.
        response.sendRedirect(request.getContextPath() + "/profile.jsp");

    }


}
