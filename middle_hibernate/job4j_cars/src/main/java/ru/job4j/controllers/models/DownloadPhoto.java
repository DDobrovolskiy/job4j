package ru.job4j.controllers.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/models/downloadphoto")
public class DownloadPhoto extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadPhoto.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String photo = req.getParameter("photo");
        LOG.debug("Load id image: {}", photo);
        File downloadFile = null;
        for (File file : new File("C:\\images\\cars\\").listFiles()) {
            if (file.getName().startsWith(photo)) {
                downloadFile = file;
                LOG.debug("Search image complite: {}", file.getName());
                break;
            }
        }
        if (downloadFile == null) {
            downloadFile = new File("C:\\images\\cars\\0.png");
        }

        LOG.debug(downloadFile.getName());
        String mime = getServletContext().getMimeType(downloadFile.getName());
        LOG.debug(mime);

        if (mime == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        resp.setContentType(mime);
        resp.setHeader("Content-Disposition", "attachment; filename=\""
                + downloadFile.getName() + "\"");

        try (FileInputStream stream = new FileInputStream(downloadFile)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
