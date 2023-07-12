package com.nnc.myblog.controller;

import cn.hutool.json.JSONObject;
import com.nnc.myblog.common.lang.Result;
import com.nnc.myblog.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
public class ArticleController {
    //上传目录绝对路径
    @Value("${file.upload.abpath}")
    private String abpath;

    //上传目录url
    @Value("${file.upload.mdImageDir}")
    private String mdImageDir;

    //端口号
    @Value("${server.port}")
    private String port;


    //上传md文件
    @RequestMapping("/api/saveMd")
    //注意参数传递是以json格式，因此用@RequestBody定义接收参数
    public Result saveMd(@RequestBody JSONObject param){
        //取出java中对应参数的值
        String str = param.getStr("content");

        String name=param.getStr("name");
        String typeId=param.getStr("typeId");
        //文件保存路径  F:\note\555.md
        String filepath=abpath+name+".md";
        FileUtil.string2File(str,filepath);
        return Result.succ("yes");
    }

    //上传图片
    @PostMapping("/api/uploadFile")
    public Result uploadFile(@RequestParam("image") MultipartFile file, HttpServletRequest request){
        //上传的绝对路径  F:/note/assets/
        String imgAbPath=abpath+"assets/";
        //绝对路径对应urlpath   http://localhost:4000/note/assets/
        String imgUrlDir="http:"+request.getHeader("Origin").split(":")[1]+":"+port+mdImageDir;
        //返回对应的File类型f
        File f = FileUtil.upload(file, imgAbPath);

        //返回图片地址 http://localhost:4000/note/assets/image-20200924014614792.png  注意图片名子中间有加密
        return Result.succ(imgUrlDir+f.getName());

    }

}
