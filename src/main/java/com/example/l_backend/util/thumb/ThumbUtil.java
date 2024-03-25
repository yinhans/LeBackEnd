package com.example.l_backend.util.thumb;

import com.example.l_backend.entity.Thumbnail;
import com.example.l_backend.util.H_Time;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Data
public class ThumbUtil {
    @Autowired
    private H_Time hTime;
    @Autowired
    private Thumbnail thumbnail;
    @Autowired
    Environment environment;
    private long size;
    private String directory;
    private String url;
    private String name;

    public String save(MultipartHttpServletRequest request) {
        String fileName = hTime.getTime() + ".png";
        thumbnail.setFileName(fileName);
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "thumb", fileName);
        //写入前端传来的文件
        try {
            MultipartFile file = request.getFile("file");
            name = request.getParameter("filename");
            InputStream inputStream = file.getInputStream();
            OutputStream outputStream = new FileOutputStream(path.toFile());
            int len;
            byte[] bytes = new byte[5];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes);
            }
            size = file.getSize();
            directory = path.toAbsolutePath().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    //获取url

    public String getUrl() {
        return "http://localhost:8885/thumb/";
    }
}
