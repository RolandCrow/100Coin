package com.bignerdranch.android.a100coin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.loader.app.LoaderManager
import com.bignerdranch.android.a100coin.fragments.CurrenciesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CurrenciesListFragment(), null) // добавляем фрагмент
                .commit()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu) // присоединяем меню
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // кнопка для эбаут активити
        when(item!!.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java) // запускаем эбаут
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
