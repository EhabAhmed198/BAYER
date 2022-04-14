package com.ehabahmed.bayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserHomeActivity : AppCompatActivity() {
    lateinit var floatingActionButton:FloatingActionButton;
    lateinit var bottomAppBar: BottomAppBar;
    lateinit var manager:FragmentManager;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        bottomAppBar=findViewById(R.id.bottomAppBar);
        manager=supportFragmentManager;
        manager.beginTransaction().replace(R.id.contianer,addNewOrderFragment()).commit();
        floatingActionButton=findViewById(R.id.add);
        floatingActionButton.setOnClickListener {
            manager.beginTransaction().
                replace(R.id.contianer,addNewOrderFragment()).commit();
//            manager.beginTransaction().remove(ShowMyRequestOrdersFragment()).commit()
        }

bottomAppBar.setOnMenuItemClickListener(object :Toolbar.OnMenuItemClickListener{
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId==R.id.myRequest){
            getSupportFragmentManager().beginTransaction().replace(R.id.contianer,ShowMyRequestOrdersFragment()).commit();
//            manager.beginTransaction().remove(addNewOrderFragment()).commit()
        }
    return true;
    }
})

    }





}