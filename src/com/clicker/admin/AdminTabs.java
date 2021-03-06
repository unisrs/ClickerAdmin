package com.clicker.admin;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminTabs.
 */
public class AdminTabs extends TabActivity {
    
    /* (non-Javadoc)
     * @see android.app.ActivityGroup#onCreate(android.os.Bundle)
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admintabs);
        
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        
        intent = new Intent().setClass(this, AdminInterface.class);
        
        spec = tabHost.newTabSpec("stock").setIndicator("Stock",
                res.getDrawable(R.drawable.stockquestions_tab)).setContent(intent);
        
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, SetSelectInterface.class);
        spec = tabHost.newTabSpec("lecture").setIndicator("Lecture",
                res.getDrawable(R.drawable.lectureset_tab)).setContent(intent);
        
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ClassManagement.class);
        spec = tabHost.newTabSpec("classmanage").setIndicator("Class",
                res.getDrawable(R.drawable.classmanage_tab)).setContent(intent);
        
        tabHost.addTab(spec);
        
        tabHost.setCurrentTab(0);
    }
    
    /* (non-Javadoc)
     * @see android.app.ActivityGroup#onResume()
     */
    protected void onResume() {
        super.onResume();
        AdminApplication myApp = ((AdminApplication)getApplication());
        if (!myApp.amConnected()) {
            finish();
        }
    }
    
}
