package vn.iostar.controller;

import vn.iostar.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet(urlPatterns = "/image")
public class DownloadImageController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        if (fname == null) return;
        File file = new File(Constant.UPLOAD_DIRECTORY, fname);
        if (!file.exists()) return;
        resp.setContentType(getServletContext().getMimeType(fname));
        resp.setContentLength((int) file.length());
        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) out.write(buffer, 0, length);
        }
    }
}
