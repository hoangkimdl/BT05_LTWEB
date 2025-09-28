package vn.iostar.controller.admin;

import vn.iostar.entity.User;
import vn.iostar.service.IUserService;
import vn.iostar.service.impl.UserService;
import vn.iostar.util.Constant;
import vn.iostar.util.UploadUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {
    "/admin/users", "/admin/user/add", "/admin/user/insert",
    "/admin/user/edit", "/admin/user/update", "/admin/user/delete", "/admin/user/search"
})
public class UserController extends HttpServlet {
    IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("users")) {
            List<User> list = userService.findAll();
            req.setAttribute("listuser", list);
            req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/views/admin/user-add.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            User user = userService.findById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            userService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/users");
        }
        else if (url.contains("search")) {
            String keyword = req.getParameter("keyword");
            List<User> list = userService.findByUsername(keyword);
            req.setAttribute("listuser", list);
            req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        try {
            if (url.contains("insert")) {
                User user = new User();
                user.setUsername(req.getParameter("username"));
                user.setPassword(req.getParameter("password"));
                user.setFullname(req.getParameter("fullname"));
                user.setEmail(req.getParameter("email"));
                user.setRole(req.getParameter("role"));
                user.setStatus(Integer.parseInt(req.getParameter("status")));
                String avatar = UploadUtil.uploadFile(req.getPart("avatar"), Constant.UPLOAD_DIRECTORY);
                user.setAvatar(avatar != null ? avatar : "default.png");
                userService.insert(user);
                resp.sendRedirect(req.getContextPath() + "/admin/users");
            } else if (url.contains("update")) {
                int id = Integer.parseInt(req.getParameter("userid"));
                User user = userService.findById(id);
                user.setFullname(req.getParameter("fullname"));
                user.setEmail(req.getParameter("email"));
                user.setRole(req.getParameter("role"));
                user.setStatus(Integer.parseInt(req.getParameter("status")));
                String avatar = UploadUtil.uploadFile(req.getPart("avatar"), Constant.UPLOAD_DIRECTORY);
                if (avatar != null) user.setAvatar(avatar);
                userService.update(user);
                resp.sendRedirect(req.getContextPath() + "/admin/users");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
