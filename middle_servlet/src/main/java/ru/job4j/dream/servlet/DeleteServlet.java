package ru.job4j.dream.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            LOG.debug("Удаление кандидата id: {}", id);
            Store.instOf().deleteCandidate(Integer.parseInt(id));
            for (File file : new File("C:\\images\\").listFiles()) {
                if (file.getName().startsWith(id)) {
                    LOG.debug("Файл удален: {}", file.getName());
                    file.delete();
                    break;
                }
            }
        } else {
            LOG.debug("Ошибка удаления кандидата id: {}", id);
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
