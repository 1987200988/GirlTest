package liwei.baway.com.girltest.model;

import java.util.List;

import liwei.baway.com.girltest.bean.GirlBean;

/**
 * 1.类的用途
 * 2.@author:liwei
 * 3.@2016/12/10
 */

public interface IShowModel {
    void addData(List<GirlBean.ResultsBean> list,ICallBack iCallBack);
    void addMoreData(List<GirlBean.ResultsBean> list,ICallBack iCallBack);



}
