package gx.common.entity;

import com.github.pagehelper.PageSerializable;
import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> extends PageSerializable<T> {

    public PageBean(List<T> list) {
        super(list);
    }
}