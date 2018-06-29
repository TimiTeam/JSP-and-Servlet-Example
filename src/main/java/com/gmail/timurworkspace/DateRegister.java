package com.gmail.timurworkspace;


import com.gmail.timurworkspace.components.Guest;
import com.gmail.timurworkspace.dao.ConnectorToDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class DateRegister extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = null;
        String lastName = null;
        int age = 0;
        try {
            name = req.getParameter("firstName");
            lastName = req.getParameter("lastName");
            age = Integer.parseInt(req.getParameter("age"));
        }catch (Exception e){
            e.getMessage();
        }

        String sendRes = name+" "+lastName;
        req.setAttribute("str",sendRes);



        RequestDispatcher requestDispatcher = null;
        if (name != null && lastName != null && age != 0){
            ConnectorToDb connection = new ConnectorToDb();
            connection.saveGuest(new Guest(age, lastName, name));
            for (Guest g: connection.findByAll()){
                System.out.println(g);
            }
            requestDispatcher = req.getRequestDispatcher("jsps/display.jsp");
        }else {
            requestDispatcher = req.getRequestDispatcher("jsps/error.jsp");
        }
        requestDispatcher.forward(req,resp);

    }
}
