package gx.web.mvc.http;

/**
 * @Description:
 * @Author: 陈悟
 * @CreateDate: 2018/12/22  21:42
 * @Version: 1.0
 */

import com.alibaba.fastjson.JSONObject;
import gx.common.entity.ResponseBody;
import gx.common.entity.user.User;
import gx.web.mvc.QiNiuYunUtil;
import gx.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/firmware")
public class FileController {
    @Autowired
    private QiNiuYunUtil qiNiuYunUtil;
    @Autowired
    private UserService  userService;

    /**
     * 用户上传头
     * 只接受jpg格式和小于50kb的文件
     * @param
     * @return
     */
    @RequestMapping("/savePhoto")
    public ResponseBody addUserPhoto(HttpServletRequest request){
        HashMap map = new HashMap();
        map.get("");
        String fileName = "photo";
        ResponseBody responseBody = new ResponseBody();
        User user = (User) QiNiuYunUtil.getParames(request, User.class);
        if(user.getId() == null){
            responseBody.setStatus(500);
            responseBody.setMsg("非法请求");
            return responseBody;
        }

        User user1 = userService.selectByPrimaryKey(user.getId());
        if(user1 == null){
            responseBody.setStatus(500);
            responseBody.setMsg("非法请求");
            return responseBody;
        }
        ResponseBody requestFile = QiNiuYunUtil.getRequestFile(request, fileName);
        if(requestFile.getStatus() == 200){
            log.info("开始上传用户头像user->{}", JSONObject.toJSONString(user));
            responseBody = qiNiuYunUtil.upload(requestFile.getBinary());
            log.info("上传用户头像结束user->{},upload->{}", JSONObject.toJSONString(user),JSONObject.toJSONString(responseBody));
            if(responseBody.getStatus() == 200){
                log.info("上传用户头像成功user->{}",JSONObject.toJSONString(user));
                user.setPortrait(responseBody.getObj().toString());
                userService.updateByPrimaryKeySelective(user);
            }
        }
        return responseBody;
    }
}
