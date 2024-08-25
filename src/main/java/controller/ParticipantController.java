package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import bean.Participant;
import dao.ParticipantDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/participants/*")
public class ParticipantController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ParticipantDAO participantDAO;

    public void init() {
        participantDAO = new ParticipantDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("Action: " + action);
        System.out.println("Servlet doGet method called for path: " + request.getServletPath());

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertParticipant(request, response);
                    break;
                case "/delete":
                    deleteParticipant(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateParticipant(request, response);
                    break;
                case "/list":
                    listParticipants(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listParticipants(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Participant> participant = participantDAO.selectAllParticipants();
        System.out.println("Size:" + participant.size());
        //request.setAttribute("participant", participant);
        request.getSession().setAttribute("participantList", participant);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("participants-list.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect(request.getContextPath() + "/participants-list.jsp");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //RequestDispatcher dispatcher = request.getRequestDispatcher("participant-form.jsp");
        //dispatcher.forward(request, response);
    	request.getSession().removeAttribute("participant");
    	response.sendRedirect(request.getContextPath() + "/participant-form.jsp");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Participant existingParticipant = participantDAO.selectParticipant(id);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("participant-form.jsp");
        //request.setAttribute("participant", existingParticipant);
        //dispatcher.forward(request, response);
        request.getSession().setAttribute("participant", existingParticipant);
        response.sendRedirect(request.getContextPath() + "/participant-form.jsp");
    }

    private void insertParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String participantName = request.getParameter("participantName");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        Participant newParticipant = new Participant(participantName, age, gender, batchId);
        participantDAO.insertParticipant(newParticipant);
        response.sendRedirect("list");
    }

    private void updateParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String participantName = request.getParameter("participantName");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        Participant participant = new Participant(id, participantName, age, gender, batchId);
        participantDAO.updateParticipant(participant);
        response.sendRedirect("list");
    }

    private void deleteParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        participantDAO.deleteParticipant(id);
        response.sendRedirect("list");
    }
}