package ru.kpfu.itis.paramonov.util;

import ru.kpfu.itis.paramonov.dao.AppointmentDao;
import ru.kpfu.itis.paramonov.dao.MasterDao;
import ru.kpfu.itis.paramonov.dao.ServiceDao;
import ru.kpfu.itis.paramonov.model.Appointment;
import ru.kpfu.itis.paramonov.model.Master;
import ru.kpfu.itis.paramonov.model.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name="dataSubmission", urlPatterns = "/submit")
public class DataSubmissionServletUtil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Master master = (new MasterDao()).get(Integer.parseInt(req.getParameter("master")));
        Service service = (new ServiceDao()).get(Integer.parseInt(req.getParameter("service")));
        Appointment appointment = new Appointment(master.getId(), service.getId(),
                Timestamp.valueOf(req.getParameter("date") + " " + req.getParameter("time") + ":00"), req.getParameter("number"));

        AppointmentDao appointmentDao = new AppointmentDao();
        List<Appointment> appointments = appointmentDao.getMasterAppointments(master.getId());

        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();

        if (!checkIsWorking(appointment, master, service)) {
            writer.write("fail_not_working");
        }
        else if (checkBusy(appointments, appointment, service)) {
            writer.write("fail_collision");
        } else {
            appointmentDao.save(appointment);
            writer.write("ok");
        }
    }

    private boolean checkBusy(List<Appointment> appointments, Appointment saveAppointment, Service service) {
        long saveAppointmentMilliSec = saveAppointment.getTime().getTime();
        for (Appointment appointment : appointments) {
            long beginMilliSec = appointment.getTime().getTime();
            long finishMilliSec = appointment.getTime().getTime() + service.getDuration() * 1000L;
            if (saveAppointmentMilliSec >= beginMilliSec && saveAppointmentMilliSec <= finishMilliSec) return true;
            if (saveAppointmentMilliSec + service.getDuration() * 1000L >= beginMilliSec &&
                    saveAppointmentMilliSec + service.getDuration() * 1000L <= finishMilliSec) return true;
        }
        return false;
    }

    private boolean checkIsWorking(Appointment saveAppointment, Master master, Service service) {
        String[] beginTime = master.getBeginWorkTime().split(":");
        long beginTimeMilliSec = (Integer.parseInt(beginTime[0]) * 60L + Integer.parseInt(beginTime[1])) * 60 * 1000;
        String[] finishTime = master.getFinishWorkTime().split(":");
        long finishTimeMilliSec = (Integer.parseInt(finishTime[0]) * 60L + Integer.parseInt(finishTime[1])) * 60 * 1000;
        long saveAppointmentMilliSec = (saveAppointment.getTime().getHours() * 60L +
                saveAppointment.getTime().getSeconds()) * 60 * 1000;

        return saveAppointmentMilliSec >= beginTimeMilliSec &&
                saveAppointmentMilliSec + service.getDuration() * 1000L <= finishTimeMilliSec;
    }
}
