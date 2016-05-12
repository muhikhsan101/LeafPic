package com.horaapps.leafpic.Views;

import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;

import com.horaapps.leafpic.R;
import com.horaapps.leafpic.utils.ColorPalette;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;

/**
 * Created by dnld on 23/02/16.
 */
public class ThemedActivity extends AppCompatActivity {

    SharedPreferences SP;

    private int primaryColor;
    private int accentColor;
    private int basicTheme;
    private boolean darkTheme;
    private boolean coloredNavBar;
    private boolean oscuredStatusBar;
    private boolean applyThemeImgAct; //TASPARENCY


    public boolean isNavigationBarColored() {
        return coloredNavBar;
    }

    public boolean isTraslucentStatusBar() {
        return oscuredStatusBar;
    }

    public boolean isApplyThemeOnImgAct() {
        return applyThemeImgAct;
    }

    public boolean isTransparencyZero() {
        return 255 - SP.getInt("set_alpha", 0) == 255;
    }

    public int getTransparency() {
        return 255 - SP.getInt("set_alpha", 0);
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public int getAccentColor() {
        return accentColor;
    }

    public int getBasicTheme(){ return  basicTheme; }

    //METHOD
    public int getBackgroundColor(){
        int color;
        switch (basicTheme){
            case 2:color = ContextCompat.getColor(getApplicationContext(), R.color.md_dark_background);break;
            case 3:color = ContextCompat.getColor(getApplicationContext(), R.color.md_black_1000);break;
            case 1:
            default:color = ContextCompat.getColor(getApplicationContext(), R.color.md_light_background);
        }
        return color;
    }

    public int getInvertedBackgroundColor(){
        int color;
        switch (basicTheme){
            case 2:color = ContextCompat.getColor(getApplicationContext(), R.color.md_light_background);break;
            case 3:color = ContextCompat.getColor(getApplicationContext(), R.color.md_light_background);break;
            case 1:
            default:color = ContextCompat.getColor(getApplicationContext(), R.color.md_black_1000);
        }
        return color;
    }

    public int getTextColor(){
        int color;
        switch (basicTheme){
            case 2:color = ContextCompat.getColor(getApplicationContext(), R.color.md_grey_200);break;
            case 3:color = ContextCompat.getColor(getApplicationContext(), R.color.md_grey_200);break;
            case 1:
            default:color = ContextCompat.getColor(getApplicationContext(), R.color.md_grey_800);
        }
        return color;
    }

    public int getSubTextColor(){
        int color;
        switch (basicTheme){
            case 2:color = ContextCompat.getColor(getApplicationContext(), R.color.md_grey_400);break;
            case 3:color = ContextCompat.getColor(getApplicationContext(), R.color.md_grey_400);break;
            case 1:
            default:color = ContextCompat.getColor(getApplicationContext(), R.color.md_grey_600);
        }
        return color;
    }

    public int getCardBackgroundColor(){
        int color;
        switch (basicTheme){
            case 2:color = ContextCompat.getColor(getApplicationContext(), R.color.md_dark_cards);break;
            case 3:color = ContextCompat.getColor(getApplicationContext(), R.color.md_black_1000);break;
            case 1:
            default:color = ContextCompat.getColor(getApplicationContext(), R.color.md_light_cards);
        }
        return color;
    }

    public int getIconColor(){
        int color;
        switch (basicTheme){
            case 2:
            case 3:color = ContextCompat.getColor(getApplicationContext(), R.color.md_white_1000);break;//md_dark_primary_icon
            case 1:
            default:color = ContextCompat.getColor(getApplicationContext(), R.color.md_light_primary_icon);
        }
        return color;
    }

    public int getDrawerBackground(){
        int color;
        switch (basicTheme){
            case 2:color = ContextCompat.getColor(getApplicationContext(), R.color.md_dark_cards);break;
            case 3:color = ContextCompat.getColor(getApplicationContext(), R.color.md_black_1000);break;
            case 1:
            default: color = ContextCompat.getColor(getApplicationContext(), R.color.md_light_cards);
        }
        return color;
    }

    public int getDialogStyle(){
        int style;
        switch (getBasicTheme()){
            case 2: style = R.style.AlertDialog_Dark;break;
            case 3: style = R.style.AlertDialog_Dark_Amoled;break;
            case 1:
            default: style = R.style.AlertDialog_Light;
        }
        return style;
    }

    public int getPopupToolbarStyle(){
        int style;
        switch (getBasicTheme()){
            case 2: style = R.style.DarkActionBarMenu;break;
            case 3: style = R.style.AmoledDarkActionBarMenu;break;
            case 1: default: style = R.style.LightActionBarMenu;
        }
        return style;
    }

    public int getDefaultThemeToolbarColor3th(){
        int color;
        switch (basicTheme){
            case 2:color = ContextCompat.getColor(getApplicationContext(), R.color.md_black_1000);break;
            case 3:color = ContextCompat.getColor(getApplicationContext(), R.color.md_blue_grey_800);break;
            case 1:
            default:
                //TODO cambia questo colore plis
                color = ContextCompat.getColor(getApplicationContext(), R.color.md_blue_grey_800);
        }
        return color;
    }

    public ColorStateList getRadioButtonColor(){
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled}, //disabled
                        new int[]{android.R.attr.state_enabled} //enabled
                }, new int[] {
                        getTextColor() //disabled
                        ,getAccentColor() //enabled
                });
        return colorStateList;
    }

    public void setNavBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isNavigationBarColored()) getWindow().setNavigationBarColor(getPrimaryColor());
            else
                getWindow().setNavigationBarColor(ColorPalette.getTransparentColor(ContextCompat.getColor(getApplicationContext(), R.color.md_black_1000), 255));
        }
    }

    public void updateSwitchColor(SwitchCompat sw, int color){
        if(sw.isChecked())
            sw.getThumbDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        else
            sw.getThumbDrawable().setColorFilter(getTextColor(), PorterDuff.Mode.MULTIPLY);
        if(getBasicTheme()!=3)sw.getTrackDrawable().setColorFilter(getBackgroundColor(), PorterDuff.Mode.MULTIPLY);
        else sw.getTrackDrawable().setColorFilter(getSubTextColor(), PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateTheme();
    }

    public IconicsDrawable getToolbarIcon(IIcon icon){
        return new IconicsDrawable(this).icon(icon).color(Color.WHITE).sizeDp(18);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        updateTheme();
    }

    protected void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isTraslucentStatusBar())
                getWindow().setStatusBarColor(ColorPalette.getOscuredColor(getPrimaryColor()));
            else
                getWindow().setStatusBarColor(getPrimaryColor());
        }
    }

    public void updateTheme(){
        this.primaryColor = SP.getInt("primary_color", ContextCompat.getColor(getApplicationContext(),R.color.md_indigo_500));//DEFAULT;
        this.accentColor = SP.getInt("accent_color", ContextCompat.getColor(getApplicationContext(), R.color.md_light_blue_500));//COLOR DEFAULT
        basicTheme = SP.getInt("basic_theme", 1);//WHITE DEFAULT
        coloredNavBar = SP. getBoolean("nav_bar", false);
        oscuredStatusBar = SP.getBoolean("set_traslucent_statusbar",true);
        applyThemeImgAct = SP.getBoolean("apply_theme_img_act", true);
    }


    public void setRecentApp(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BitmapDrawable drawable = ((BitmapDrawable) getDrawable(R.mipmap.ic_launcher));
            setTaskDescription(new ActivityManager.TaskDescription(text, drawable.getBitmap(), getPrimaryColor()));
        }
    }
}
