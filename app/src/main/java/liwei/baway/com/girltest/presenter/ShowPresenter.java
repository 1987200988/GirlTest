package liwei.baway.com.girltest.presenter;

import java.util.ArrayList;
import java.util.List;

import liwei.baway.com.girltest.bean.GirlBean;
import liwei.baway.com.girltest.contract.Mycontract;
import liwei.baway.com.girltest.model.ICallBack;
import liwei.baway.com.girltest.model.IShowModel;
import liwei.baway.com.girltest.model.ShowModel;
import liwei.baway.com.girltest.view.MainActivity;

/**
 * 1.类的用途
 * 2.@author:liwei
 * 3.@2016/12/10
 */

public class ShowPresenter implements Mycontract.IShowPresenter {
    public Mycontract.IShowView mShowView;
    public IShowModel mShowModel;
    private List<GirlBean.ResultsBean> list;


    public ShowPresenter(Mycontract.IShowView iShowView) {
        mShowView = iShowView;
        mShowModel = new ShowModel();
    }


    @Override
    public void addPresenterData() {
        list = new ArrayList<>();
        mShowModel.addData(list, new ICallBack() {
            @Override
            public void success() {
                MainActivity mShowView = (MainActivity) ShowPresenter.this.mShowView;
                mShowView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShowPresenter.this.mShowView.addSuccess(list);
                    }
                });


            }

            @Override
            public void failure() {

                MainActivity mShowView = (MainActivity) ShowPresenter.this.mShowView;
                mShowView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShowPresenter.this.mShowView.addFailure();
                    }
                });

            }
        });


    }

    @Override
    public void addPresenterMoreData() {
        mShowModel.addMoreData(list, new ICallBack() {
            @Override
            public void success() {
                MainActivity mShowView = (MainActivity) ShowPresenter.this.mShowView;
                mShowView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShowPresenter.this.mShowView.addMoreSuccess(list);
                    }
                });
            }

            @Override
            public void failure() {
                MainActivity mShowView = (MainActivity) ShowPresenter.this.mShowView;
                mShowView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShowPresenter.this.mShowView.addFailure();
                    }
                });
            }
        });


    }

    @Override
    public void detachPresenter() {
        mShowView = null;
        mShowModel = null;
    }

}
