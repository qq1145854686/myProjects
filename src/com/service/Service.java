package com.service;

import java.util.List;

/**
 * Created by Jmlc on 2017/8/23.
 */
public interface Service {
    List<Object> gainList(String sql,Object[] params);//获取列表

}