package ru.kpfu.itis.paramonov.servlet;

import ru.kpfu.itis.paramonov.dao.MasterDao;
import ru.kpfu.itis.paramonov.dao.ServiceDao;
import ru.kpfu.itis.paramonov.model.Master;
import ru.kpfu.itis.paramonov.model.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "mainServlet", urlPatterns = "/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Master> masters = (new MasterDao()).getAll();
        req.setAttribute("masters", masters);
        List<Service> services = (new ServiceDao()).getAll();
        req.setAttribute("services", services);

        req.getRequestDispatcher("main.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
