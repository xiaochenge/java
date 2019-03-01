package gx.web.mvc;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import gx.common.Constant;
import gx.common.entity.ResponseBody;
import gx.common.utils.EmptyCheck;
import gx.common.utils.GxUUid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Enumeration;

/**
 * @Description:  上传上下附件公共类
 * @Author: 陈悟
 * @CreateDate: 2018/12/22  22:24
 * @Version: 1.0
 */
@Slf4j
@Component
public class QiNiuYunUtil {

    @Value("${gx.access_key}")
    private String ACCESS_KEY;
    @Value("${gx.secret_key}")
    private String SECRET_KEY;
    @Value("${gx.bucketname}")
    private String bucketname;
    @Value("${gx.qiNiuYunUrl}")
    private String qiNiuYunUrl;
    private Auth auth ;
    private static UploadManager uploadManager;
    private static BucketManager bucketManager;
    @PostConstruct
    private void qiYunNiuInitMethad(){
         //密钥配置
         auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //创建上传对象
         uploadManager =new UploadManager(new Configuration());
         bucketManager = new BucketManager(auth,new Configuration());
    }

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    public ResponseBody upload(byte[] data){
        ResponseBody responseBody = new ResponseBody();
        try {
            String filePath = GxUUid.generateShortUuid();
            //调用put方法上传
            Response res = uploadManager.put(data,filePath,getUpToken());
            //打印返回的信息
            if(res == null || res.statusCode != 200){
                log.error("上传图片失败->{}", JSONObject.toJSONString(res));
                responseBody.setStatus(500);
                responseBody.setMsg("上传图片失败");
            }else{
                responseBody.setStatus(res.statusCode);
                responseBody.setObj(qiNiuYunUrl+filePath);
            }
        } catch (Exception e) {
            log.error("上传文件到七-> response{}",JSONObject.toJSONString(e));
            responseBody.setStatus(500);
            responseBody.setMsg("上传图片失败");
        }
        return responseBody;
    }

    public ResponseBody deleteFile(String fileName){
        ResponseBody responseBody = new ResponseBody();
        try {
            String replace = fileName.replace(qiNiuYunUrl, Constant.BLANK);
            Response delete = bucketManager.delete(bucketname, replace);
            if(delete == null || delete.statusCode != 200){
                log.error("删除图片失败->{}", JSONObject.toJSONString(delete));
                responseBody.setStatus(500);
                responseBody.setMsg("上传图片失败");
            }else{
                responseBody.setStatus(delete.statusCode);
                responseBody.setMsg("删除图片成功");
            }
        } catch (QiniuException e) {
            Response r = e.response;
            log.error("删除七牛云图片发生异常-> response{}",JSONObject.toJSONString(r));
        }
        return responseBody;
    }



    public static ResponseBody getRequestFile(HttpServletRequest request, String fileNmae){
        ResponseBody responseBody = new ResponseBody();
        InputStream is;
        try {
            request.setCharacterEncoding(Constant.ENCODING);
            Part part = request.getPart(fileNmae);
                if (part != null && "image/jpeg".equals(part.getContentType()) && part.getSize() > 6000) {
                    responseBody.setStatus(200);
                    responseBody.setBinary(input2byte(part.getInputStream()));
                } else {
                    responseBody.setStatus(500);
                    responseBody.setMsg("非法文件");
                }
        } catch (Exception e) {
            log.error("上传头像发生异常 excetion->{}",e);
            responseBody.setStatus(500);
            responseBody.setMsg("非法文件");
        }
        return responseBody;
    }


    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[2048];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }


    public static Object getParames(HttpServletRequest request,Class clazz) {
        try {
            request.setCharacterEncoding(Constant.ENCODING);
        } catch (UnsupportedEncodingException e) {
            log.error("设置字符集编码格式失败{}",e);
        }
        JSONObject json = new JSONObject();
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            if(EmptyCheck.isNotEmptyString(request.getParameter(name)) && !"null".equals(request.getParameter(name))){
            json.put(name,request.getParameter(name));
            }
        }
        return JSONObject.parseObject(json.toJSONString(json),clazz);
    }


}
