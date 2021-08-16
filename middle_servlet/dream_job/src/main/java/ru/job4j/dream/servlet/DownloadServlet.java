package ru.job4j.dream.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        LOG.debug("Load name image: {}", name);
        File downloadFile = null;
        for (File file : new File("C:\\images\\").listFiles()) {
            if (file.getName().startsWith(name)) {
                downloadFile = file;
                LOG.debug("Search image complite: {}", file.getName());
                break;
            }
        }
        if (downloadFile == null) {
            downloadFile = new File("C:\\images\\0.png");
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\""
                + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
