package liwei.baway.com.girltest.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.library.utils.T;
import com.nobrain.android.permissions.AndroidPermissions;
import com.nobrain.android.permissions.Checker;
import com.nobrain.android.permissions.Result;

import java.util.List;

import liwei.baway.com.girltest.R;
import liwei.baway.com.girltest.adapter.RecycleAdapter;
import liwei.baway.com.girltest.bean.GirlBean;
import liwei.baway.com.girltest.contract.Mycontract;
import liwei.baway.com.girltest.presenter.ShowPresenter;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity implements Mycontract.IShowView {

    private ShowPresenter showPresenter;
    private RecyclerView recycleview;
    private RecycleAdapter recycleAdapter;
    private int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        showPresenter = new ShowPresenter(this);
        showPresenter.addPresenterData();
        initEvents();
//        AndroidPermissions.check(this)
//                .permissions(Manifest.permission.CALL_PHONE)
//                .hasPermissions(new Checker.Action0() {
//                    @Override
//                    public void call(String[] permissions) {
//                        // do something..
//                        T.showShort(MainActivity.this, "有权限");
//                        Intent intent = new Intent(Intent.ACTION_CALL);
//                        Uri data = Uri.parse("tel:" + "10086");
//                        intent.setData(data);
//                        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            // TODO: Consider calling
//                            //    ActivityCompat#requestPermissions
//                            // here to request the missing permissions, and then overriding
//                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                            //                                          int[] grantResults)
//                            // to handle the case where the user grants the permission. See the documentation
//                            // for ActivityCompat#requestPermissions for more details.
//                            return;
//                        }
//                        MainActivity.this.startActivity(intent);
//                    }
//                })
//                .noPermissions(new Checker.Action1() {
//                    @Override
//                    public void call(String[] permissions) {
//                        // do something..
//                        T.showShort(MainActivity.this, "没权限");
//                        ActivityCompat.requestPermissions(MainActivity.this
//                                , new String[]{Manifest.permission.CALL_PHONE}
//                                , REQUEST_CODE);
//                    }
//                })
//                .check();


    }

    public void onRequestPermissionsResult(int requestCode, final String[] permissions, int[] grantResults) {
        AndroidPermissions.result(MainActivity.this)
                .addPermissions(REQUEST_CODE, Manifest.permission.CALL_PHONE)
                .putActions(REQUEST_CODE, new Result.Action0() {
                    @Override
                    public void call() {
                        // do something..
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + "10086");
                        intent.setData(data);

                        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        MainActivity.this.startActivity(intent);
                    }
                }, new Result.Action1() {
                    @Override
                    public void call(String[] hasPermissions, String[] noPermissions) {
                        // do something..
                        T.showShort(MainActivity.this,"我也没权限");
                    }
                })
                .result(requestCode, permissions, grantResults);
    }

    private void initEvents() {



        recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int itemCount = layoutManager.getItemCount();
                if (lastVisibleItemPosition > itemCount - 2  && dy > 0) {

                    showPresenter.addPresenterMoreData();

                }
            }
        });



    }

    private void initViews() {
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
    }


    @Override
    public void addViewData(List<GirlBean.ResultsBean> list) {




    }



    @Override
    public void addViewMoreData() {

    }

    @Override
    public void addSuccess(final List<GirlBean.ResultsBean> list) {
        Log.e("mainactivity", "addSuccess: "+list.size() );
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
           public int getSpanSize(int position) {
               if (position == list.size()) {
                 return 2;
               } else {
                  return 1;
          }
            }
        });
        recycleview.setLayoutManager(gridLayoutManager);
        recycleAdapter = new RecycleAdapter(this, list);

        recycleview.setAdapter(recycleAdapter);


    }

    @Override
    public void addMoreSuccess(List<GirlBean.ResultsBean> list) {
        recycleAdapter.notifyDataSetChanged();

    }

    @Override
    public void addFailure() {

    }

    @Override
    public void addMoreFailure() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.detachPresenter();

    }
}
