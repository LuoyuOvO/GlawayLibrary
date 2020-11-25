package www.glaway.baselibrary.view.datepicker;

import android.content.Context;

/**
 * 时间选择控件
 *
 *                 //时间选择
 *                 PickerShow pickerShow = new PickerShow();
 *                 pickerShow.showPickerDate(this,date -> {
 *                     ToastUtil.showWarning(date+"");
 *                 });
 */
public class PickerShow {


    private CustomDatePicker mDatePicker, mTimerPicker;
    /**
     * 初始化日期选择
     * @param context
     */
    private void initDatePicker(String beginTime,String endTime,Context context, TimeCallback callback) {
        long beginTimestamp = DateFormatUtils.str2Long(beginTime, false);
        long endTimestamp =DateFormatUtils.str2Long(endTime, false);
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(context , new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                callback.Call(timestamp);
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }
    /**
     * 初始化日期选择
     * @param context
     */
    private void initDatePicker(Context context, TimeCallback callback) {
        initDatePicker("2010-01-01","2100-01-01",context,callback);
    }
    private void initTimerPicker(String beginTime,String endTime,Context context, TimeCallback callback) {
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(context, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                callback.Call(timestamp);
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
    }
    /**
     * 初始化时间选择
     * @param context
     */
    private void initTimerPicker(Context context, TimeCallback callback) {
        initTimerPicker("2010-01-01 00:00","2030-01-01 00:00",context,callback);
    }

    /**
     * 显示日期选择控件，
     * @param context
     * @param time 默认选择时间
     */
    public void showPickerDate(Context context, Long time, TimeCallback callback){
        initDatePicker(context,callback);
        mDatePicker.show(DateFormatUtils.long2Str(time, false));
    }
    public void showPickerDate(Context context, TimeCallback callback){
        initDatePicker(context,callback);
        mDatePicker.show(DateFormatUtils.long2Str(System.currentTimeMillis(), false));
    }

    /**
     * 显示时间选择控件
     * @param context
     * @param time
     * @param callback
     */
    public void showPickerTime(Context context, Long time, TimeCallback callback){
        initTimerPicker(context,callback);
        mTimerPicker.show(DateFormatUtils.long2Str(time, true));
    }

    /**
     * 回调接口
     */
    public interface TimeCallback{
        void Call(long date);
    }
}
