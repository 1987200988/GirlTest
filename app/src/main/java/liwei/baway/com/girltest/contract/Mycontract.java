package liwei.baway.com.girltest.contract;

import java.util.List;

import liwei.baway.com.girltest.bean.GirlBean;

/**
 * 1.类的用途
 * 2.@author:liwei
 * 3.@2016/12/10
 */

public class Mycontract {
    public interface IShowView{
        void addViewData(List<GirlBean.ResultsBean> list);

        void addViewMoreData();
        void addSuccess(List<GirlBean.ResultsBean> list);
        void addMoreSuccess(List<GirlBean.ResultsBean> list);
        void addFailure();
        void addMoreFailure();


    }


    public interface IShowPresenter{
        void addPresenterData();
        void detachPresenter();
        void addPresenterMoreData();




    }







}
