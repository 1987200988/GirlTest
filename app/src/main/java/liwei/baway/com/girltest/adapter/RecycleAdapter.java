package liwei.baway.com.girltest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.recyclerview.BaseViewHolder;

import java.util.List;

import liwei.baway.com.girltest.R;
import liwei.baway.com.girltest.bean.GirlBean;

/**
 * 1.类的用途
 * 2.@author:liwei
 * 3.@2016/12/10
 */

public class RecycleAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    public Context mContext;
    public List<GirlBean.ResultsBean> mList;
    public int normalType = 1;
    public int loadType = 2;



    public RecycleAdapter(Context context, List<GirlBean.ResultsBean> list){
        mContext = context;
        mList = list;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==normalType) {
            LayoutInflater from = LayoutInflater.from(mContext);
            View inflate = from.inflate(R.layout.recycleitem, parent, false);
            return new ViewHolder1(inflate);
        }else{
            LayoutInflater from = LayoutInflater.from(mContext);
            View inflate = from.inflate(R.layout.loaditem, parent, false);
            return new ViewHolder2(inflate);

        }


    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
       if(position==mList.size()){
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           return;
       }
//        holder.setIsRecyclable(false);
        holder.setData(mContext,mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if(position<mList.size()){
            return normalType;
        }else{
            return loadType;
        }

    }
}
