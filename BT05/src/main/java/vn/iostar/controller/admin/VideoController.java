package vn.iostar.controller.admin;

import vn.iostar.entity.Video;
import vn.iostar.entity.Category;
import vn.iostar.service.IVideoService;
import vn.iostar.service.ICategoryService;
import vn.iostar.service.impl.VideoService;
import vn.iostar.service.impl.CategoryService;
import vn.iostar.util.Constant;
import vn.iostar.util.UploadUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {
        "/admin/videos", "/admin/video/add", "/admin/video/insert",
        "/admin/video/edit", "/admin/video/update",
        "/admin/video/delete", "/admin/video/search"
})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    IVideoService videoService = new VideoService();
    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();

        if (url.contains("videos")) {
            // nếu có keyword thì search
            String keyword = req.getParameter("keyword");
            List<Video> list;
            if (keyword != null && !keyword.isEmpty()) {
                list = videoService.findByTitle(keyword);
            } else {
                list = videoService.findAll();
            }
            req.setAttribute("listvideo", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);

        } else if (url.contains("add")) {
            req.setAttribute("listcate", cateService.findAll());
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);

        } else if (url.contains("edit")) {
            String id = req.getParameter("id");
            Video video = videoService.findById(id);
            req.setAttribute("video", video);
            req.setAttribute("listcate", cateService.findAll());
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);

        } else if (url.contains("delete")) {
            String id = req.getParameter("id");
            videoService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");

        } else if (url.contains("search")) {
            String keyword = req.getParameter("keyword");
            List<Video> list = videoService.findByTitle(keyword);
            req.setAttribute("listvideo", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();

        try {
            if (url.contains("insert")) {
                Video v = new Video();
                v.setVideoId(req.getParameter("videoid"));
                v.setTitle(req.getParameter("title"));
                v.setDescription(req.getParameter("description"));
                v.setActive(Integer.parseInt(req.getParameter("active")));
                v.setViews(0);

                int cateId = Integer.parseInt(req.getParameter("categoryid"));
                Category cate = cateService.findByID(cateId);
                v.setCategory(cate);

                String poster = UploadUtil.uploadFile(req.getPart("poster"), Constant.UPLOAD_DIRECTORY);
                v.setPoster(poster != null ? poster : "default.png");

                videoService.insert(v);
                resp.sendRedirect(req.getContextPath() + "/admin/videos");

            } else if (url.contains("update")) {
                String id = req.getParameter("videoid");
                Video v = videoService.findById(id);
                v.setTitle(req.getParameter("title"));
                v.setDescription(req.getParameter("description"));
                v.setActive(Integer.parseInt(req.getParameter("active")));

                int cateId = Integer.parseInt(req.getParameter("categoryid"));
                Category cate = cateService.findByID(cateId);
                v.setCategory(cate);

                String poster = UploadUtil.uploadFile(req.getPart("poster"), Constant.UPLOAD_DIRECTORY);
                if (poster != null) {
                    v.setPoster(poster);
                }

                videoService.update(v);
                resp.sendRedirect(req.getContextPath() + "/admin/videos");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
