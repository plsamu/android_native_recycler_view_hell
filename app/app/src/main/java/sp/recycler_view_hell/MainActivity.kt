package sp.recycler_view_hell

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import sp.recycler_view_hell.navigator1.Navigator1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt1).setOnClickListener {
            val intent = Intent(this, Navigator1::class.java)
            changeActivity(intent)
        }
    }

    private fun changeActivity(intent: Intent) {
        startActivity(intent)
    }
}