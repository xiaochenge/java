package gx.common.utils;

import com.github.pagehelper.PageHelper;
import gx.common.entity.PageBean;

import java.util.List;
import java.util.function.Function;

/**
 * @Description:   操作数据库时,抽象分页方法
 * @Author: 陈悟
 * @CreateDate: 2018/12/22  10:09
 * @Version: 1.0
 */
public class PageHelperUtil {

    /**
     * 对分页方法进行抽象
     * @param currentPage 第几页
     * @param pageSize  页大小
     * @param param  请求参数
     * @param function
     * @param <T>
     * @param <M>
     * @return
     */
    public static<T,M> PageBean<T> getPages(Integer currentPage,Integer pageSize,M param, Function<M, List<T>> function){
        PageHelper.startPage(currentPage, pageSize);
        List<T> list = function.apply(param);
        PageBean pageBean=new PageBean(list);
        return pageBean;
    }
}
