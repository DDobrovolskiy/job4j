package ru.job4j.dream.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.token.FactoryToken;
import ru.job4j.dream.token.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UploadServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.debug("doGet upload image for id : {}", req.getAttribute("id"));
        req.getRequestDispatcher("photoUpload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.debug("doGet upload image for id : {}", req.getAttribute("id"));
        String uploadFilePath = "C:\\images\\";
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        LOG.debug("Upload = {}", fileSaveDir.getAbsolutePath());
        for (Part part : req.getParts()) {
            Token token = FactoryToken.produce(part);
            if (token.fileContentType() != null) {
                LOG.debug("File load = {}", token.filename());
                LOG.debug("Save as = {}{}", token.name(), token.fileContentType());
                part.write(uploadFilePath + File.separator
                        + token.name() + token.fileContentType());
            }
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
