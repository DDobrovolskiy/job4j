package ru.job4j.controllers.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.users.User;
import ru.job4j.store.DataAccessObject;
import ru.job4j.token.file.FactoryToken;
import ru.job4j.token.file.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class UploadPhoto extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UploadPhoto.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.debug("doGet upload image for id : {}", req.getParameter("id"));
        req.getSession().setAttribute("uploadPhotoCarId", req.getParameter("id"));
        req.getRequestDispatcher("/upload.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt((String) req.getSession().getAttribute("uploadPhotoCarId"));
        LOG.debug("doGet upload image for id : {}", id);
        String uploadFilePath = "C:\\images\\cars\\";
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        LOG.debug("Upload = {}", fileSaveDir.getAbsolutePath());
        for (Part part : req.getParts()) {
            Token token = FactoryToken.produce(part);
            if (token.fileContentType() != null) {
                LOG.debug("File load = {}", token.filename());
                LOG.debug("Save as = {}{}", id, token.fileContentType());
                part.write(uploadFilePath + File.separator
                        + id + token.fileContentType());
                DataAccessObject.instOf().insertPhotoInCar(id, id + token.fileContentType());
            }
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
