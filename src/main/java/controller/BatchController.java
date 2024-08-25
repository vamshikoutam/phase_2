package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import bean.Batch;
import dao.BatchDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/batches/*")
public class BatchController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BatchDAO batchDAO;

    public void init() {
        batchDAO = new BatchDAO();
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
                    insertBatch(request, response);
                    break;
                case "/delete":
                    deleteBatch(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBatch(request, response);
                    break;
                case "/list":
                    listBatches(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBatches(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Batch> batches = batchDAO.selectAllBatches();
        System.out.println("Size:" + batches.size());
        request.getSession().setAttribute("batchList", batches);
        response.sendRedirect(request.getContextPath() + "/batches-list.jsp");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("batch");
        response.sendRedirect(request.getContextPath() + "/batch-form.jsp");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Batch existingBatch = batchDAO.selectBatch(id);
        request.getSession().setAttribute("batch", existingBatch);
        response.sendRedirect(request.getContextPath() + "/batch-form.jsp");
    }

    private void insertBatch(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	System.out.println("insert");
        String batchName = request.getParameter("batchName");
        String schedule = request.getParameter("schedule");
        String instructor = request.getParameter("instructor");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
System.out.println(request.getParameter("schedule"));
        Batch newBatch = new Batch();
        newBatch.setSchedule(schedule);
        newBatch.setInstructor(instructor);
        newBatch.setCategoryId(categoryId);
        batchDAO.insertBatch(newBatch);
        response.sendRedirect("list");
    }

    private void updateBatch(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String batchName = request.getParameter("batchName");
        String schedule = request.getParameter("schedule");
        String instructor = request.getParameter("instructor");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        Batch batch = new Batch();
        batch.setCategoryId(categoryId);
        batch.setInstructor(instructor);
        batch.setSchedule(schedule);
        batch.setBatchId(id);
        batchDAO.updateBatch(batch);
        
        response.sendRedirect("list");
    }

    private void deleteBatch(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        batchDAO.deleteBatch(id);
        response.sendRedirect("list");
    }
}