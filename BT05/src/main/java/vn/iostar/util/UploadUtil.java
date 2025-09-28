package vn.iostar.util;

import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

public class UploadUtil {
    public static String uploadFile(Part part, String uploadPath) throws Exception {
        if (part == null || part.getSize() == 0) return null;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        int index = filename.lastIndexOf(".");
        String ext = (index > 0) ? filename.substring(index + 1) : "";
        String newName = System.currentTimeMillis() + "." + ext;
        part.write(uploadPath + "/" + newName);
        return newName;
    }
}
