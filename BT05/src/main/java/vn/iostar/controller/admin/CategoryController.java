package vn.iostar.controller.admin;

import vn.iostar.entity.Category;
import vn.iostar.service.ICategoryService;
import vn.iostar.service.impl.CategoryService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import static vn.iostar.util.Constant.UPLOAD_DIRECTORY;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {
        "/admin/categories", "/admin/category/add", "/admin/category/insert",
        "/admin/category/edit", "/admin/category/update",
        "/admin/category/delete", "/admin/category/search"
})
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();

        if (url.contains("categories")) {
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = cateService.findByID(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                cateService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } else if (url.contains("search")) {
            String keyword = req.getParameter("keyword");
            List<Category> list = cateService.findByCategoryname(keyword);
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();

        if (url.contains("insert")) {
            Category category = new Category();
            category.setCategoryname(req.getParameter("categoryname"));
            category.setStatus(Integer.parseInt(req.getParameter("status")));

            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    String fname = System.currentTimeMillis() + "." + ext;
                    File uploadDir = new File(UPLOAD_DIRECTORY);
                    if (!uploadDir.exists()) uploadDir.mkdirs();
                    part.write(UPLOAD_DIRECTORY + "/" + fname);
                    category.setImages(fname);
                } else {
                    category.setImages("default.png");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } else if (url.contains("update")) {
            int id = Integer.parseInt(req.getParameter("categoryid"));
            Category category = cateService.findByID(id);
            category.setCategoryname(req.getParameter("categoryname"));
            category.setStatus(Integer.parseInt(req.getParameter("status")));

            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    String fname = System.currentTimeMillis() + "." + ext;
                    File uploadDir = new File(UPLOAD_DIRECTORY);
                    if (!uploadDir.exists()) uploadDir.mkdirs();
                    part.write(UPLOAD_DIRECTORY + "/" + fname);
                    category.setImages(fname);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cateService.update(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }
}
