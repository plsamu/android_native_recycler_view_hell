package sp.recycler_view_hell.navigator1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import sp.recycler_view_hell.R
import sp.recycler_view_hell.fragments.RV000

class Navigator1: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_layout)
        changeFragment(RV000())
    }

    private fun changeFragment(f: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl1, f)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}