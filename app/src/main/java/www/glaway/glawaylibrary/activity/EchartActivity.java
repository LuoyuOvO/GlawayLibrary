package www.glaway.glawaylibrary.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.glaway.baselibrary.activity.BaseActivity;
import www.glaway.baselibrary.view.EchartView;
import www.glaway.glawaylibrary.R;

/**
 * 图表事例
 */
public class EchartActivity extends BaseActivity {

    @BindView(R.id.chart1)
    EchartView chart1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echart);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    public void initView() {

        chart1.setPage("echarts.html");

        String optionString = "{\n" +
                "    \"tooltip\": {\n" +
                "        \"trigger\": \"item\",\n" +
                "        \"formatter\": \"{a} <br/>{b}: {c} ({d}%)\"\n" +
                "    },\n" +
                "    \"legend\": {\n" +
                "        \"orient\": \"vertical\",\n" +
                "        \"left\": 10,\n" +
                "        \"data\": [\"直接访问\", \"邮件营销\", \"联盟广告\", \"视频广告\", \"搜索引擎\"]\n" +
                "    },\n" +
                "    \"series\": [\n" +
                "        {\n" +
                "            \"name\": \"访问来源\",\n" +
                "            \"type\": \"pie\",\n" +
                "            \"radius\": [\"50%\", \"70%\"],\n" +
                "            \"avoidLabelOverlap\": false,\n" +
                "            \"label\": {\n" +
                "                \"show\": false,\n" +
                "                \"position\": \"center\"\n" +
                "            },\n" +
                "            \"emphasis\": {\n" +
                "                \"label\": {\n" +
                "                    \"show\": true,\n" +
                "                    \"fontSize\": \"30\",\n" +
                "                    \"fontWeight\": \"bold\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"labelLine\": {\n" +
                "                \"show\": false\n" +
                "            },\n" +
                "            \"data\": [\n" +
                "                {\"value\": 335, \"name\": \"直接访问\"},\n" +
                "                {\"value\": 310, \"name\": \"邮件营销\"},\n" +
                "                {\"value\": 234, \"name\": \"联盟广告\"},\n" +
                "                {\"value\": 135, \"name\": \"视频广告\"},\n" +
                "                {\"value\": 1548, \"name\": \"搜索引擎\"}\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        chart1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                chart1.refreshEchartsWithOptionByString(optionString);
            }
        });
    }

    @Override
    public void initData() {

    }
}
