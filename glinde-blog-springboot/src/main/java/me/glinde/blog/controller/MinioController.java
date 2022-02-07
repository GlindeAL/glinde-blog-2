package me.glinde.blog.controller;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import me.glinde.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.path.image}")
    private String imagePath;

    @Value("${minio.bucketName}")
    private String bucketName;


    @PostMapping("/post/image")
    public Result postImage(@RequestParam("image") MultipartFile file){

        String name = imagePath + UUID.randomUUID() + file.getOriginalFilename();
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .object(name)
                    .bucket(bucketName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build();

            minioClient.putObject(objectArgs);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("上传失败");
        }

        String url = null;
        try {
            url = minioClient.getObjectUrl(bucketName,name);
        }catch (Exception e){
            return Result.fail().message("url获取失败");
        }

        return url != null ? Result.ok(url).message("图片上传成功") : Result.fail().message("url获取失败");
    }

    @DeleteMapping("/delete/image")
    public Result deleteImage(@RequestParam("url") String url){

        String path = url.substring(url.indexOf(bucketName));
        String[] strings = path.split("/");

        String name = null;
        if(strings.length > 2){
            StringBuilder sb = new StringBuilder();
            sb.append(strings[1]);
            for(int i=2;i<strings.length;i++){
                sb.append("/").append(strings[i]);
            }
            name = sb.toString();
        }else{
            name = strings[1];
        }

        RemoveObjectArgs objectArgs = RemoveObjectArgs.builder()
                .object(name)
                .bucket(bucketName)
                .build();
        try {
            minioClient.removeObject(objectArgs);
            return Result.ok().message("图片删除成功");
        }catch (Exception e){
            return Result.fail().message("图片删除失败");
        }
    }
}
