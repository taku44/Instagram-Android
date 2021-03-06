/**
 * Created by taku on 16/08/20.
 */

        package com.example.mvince.instagramviewer;
        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.app.FragmentTabHost;
        import android.support.v4.app.ListFragment;
        import android.support.v4.view.ViewPager;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.View;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TabHost;
        import android.widget.TabHost.TabSpec;
        import android.widget.TextView;

public class MainActivity2 extends FragmentActivity{

    IntentFilter intentFilter;
    MyService4Receiver receiver;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        // レシーバーの登録
        receiver = new MyService4Receiver();
        receiver.activity = this;
        intentFilter = new IntentFilter();
        intentFilter.addAction(MyService4Service.actionName);
        registerReceiver(receiver, intentFilter);


        //FragmentTabHostを使うパターン
        /*FragmentTabHost host = (FragmentTabHost) findViewById(android.R.id.tabhost);
        host.setup(this, getSupportFragmentManager(), R.id.content);

        TabSpec tabSpec1 = host.newTabSpec("tab1");      //TabSpecのタグを設定
//        Button button1 = new Button(this);
//        button1.setBackgroundResource(R.drawable.tab_left);
        tabSpec1.setIndicator("Profile");     //(button1) などのViewを設定できる  Stringを渡すとデフォルトのタブボタンの View が使われます。
        Bundle bundle1 = new Bundle();
        bundle1.putString("name", "Tab1");     //fragmentに値を渡す
        host.addTab(tabSpec1, ProfileFragment.class, bundle1);

        TabSpec tabSpec2 = host.newTabSpec("tab2");
//        Button button2 = new Button(this);
//        button2.setBackgroundResource(R.drawable.tab_center);
        tabSpec2.setIndicator("Search");
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", "Tab2");
        host.addTab(tabSpec2, SearchFragment.class, bundle2);

        TabSpec tabSpec3 = host.newTabSpec("tab3");
//        Button button3 = new Button(this);
//        button3.setBackgroundResource(R.drawable.tab_right);
        tabSpec3.setIndicator("Notification");
        Bundle bundle3 = new Bundle();
        bundle3.putString("name", "Tab3");
        host.addTab(tabSpec3, NotificationFragment.class, bundle3);*/

        //viewPagerを使うパターン
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);     //pagerTitleStripを定義済み
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));
    }

    public void onResume() {
        super.onResume();
        Log.i("タグ", "onResume()");

        intent = new Intent(getBaseContext(), MyService4Service.class);
        startService(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("タグ", "onDestroy()");

        unregisterReceiver(receiver);//レシーバー登録解除

        if (intent != null)
        {
            stopService(intent);//サービスの停止
        }
        intent = null;

    }

    /*public static class SampleFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setText(getArguments().getString("name"));    //Actiityクラスから渡された私を受け取る

            return textView;
        }
    }*/

    //以下、iewPagerの動作を作成するアダプター  このようにJavadoc フォーマットでコメントを記述しておくと、javadoc コマンドを使ってドキュメントファイルを生成できます（一般的に HTML 形式で出力します）
    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 3;

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                ProfileFragment fragment1 = new ProfileFragment();
                return fragment1;
            } else if (position == 1) {
                SearchFragment fragment2 = new SearchFragment();
                return fragment2;
            } else if (position == 2) {
                NotificationFragment fragment3 = new NotificationFragment();
                return fragment3;
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {    //pagerTitleStripにページの情報を渡すメソッド  これで各タブのタイトル名が決まる
            switch (position) {
                case 0:
                    return getString(R.string.profile).toUpperCase();
                case 1:
                    return getString(R.string.search).toUpperCase();
                case 2:
                    return getString(R.string.notification).toUpperCase();
            }
            return null;
        }
    }

    //このメソッドのデフォルトの実装では、EditText ウィジェット内のテキストまたは ListView のスクロール位置などのアクティビティのビュー階層の状態に関する情報が保存されます。
    @Override
    //アクティビティの追加の状態情報を保存するには、 onSaveInstanceState() を実装し、Bundle オブジェクトにキー値のペアを追加する必要があります。
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);  //必要
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {     //復元対象の保存済みの状態がある場合のみ、呼ばれる(画面を横にした時などで)
        super.onRestoreInstanceState(savedInstanceState);   //必要
    }
}




