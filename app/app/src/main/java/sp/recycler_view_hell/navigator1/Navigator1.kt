package sp.recycler_view_hell.navigator1

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import sp.recycler_view_hell.R
import sp.recycler_view_hell.fragments.RV000
import sp.recycler_view_hell.fragments.RV001
import kotlin.math.abs


class Navigator1 : AppCompatActivity() {

    val TAG = Navigator1::class.simpleName
    var fragments = listOf(RV000(), RV001())
    var fragmentIndex = 0
    val SWIPE_MIN_DISTANCE = 120
    val SWIPE_MAX_OFF_PATH = 250
    val SWIPE_THRESHOLD_VELOCITY = 200

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_layout)

        val gesture = GestureDetector(this, object : SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent, e2: MotionEvent, velocityX: Float,
                velocityY: Float
            ): Boolean {
                Log.i(TAG, "onFling has been called!")
                try {
                    if (abs(e1.y - e2.y) > SWIPE_MAX_OFF_PATH) return false
                    if (e1.x - e2.x > SWIPE_MIN_DISTANCE
                        && abs(velocityX) > SWIPE_THRESHOLD_VELOCITY
                    ) {
                        Log.i(TAG, "Right to Left")
                        fragmentIndex -= 1
                        if (fragmentIndex <= -1)
                            fragmentIndex = fragments.size - 1
                    } else if (e2.x - e1.x > SWIPE_MIN_DISTANCE
                        && abs(velocityX) > SWIPE_THRESHOLD_VELOCITY
                    ) {
                        Log.i(TAG, "Left to Right")
                        fragmentIndex += 1
                        if (fragmentIndex >= fragments.size)
                            fragmentIndex = 0
                    }
                    changeFragment(fragments[fragmentIndex])
                } catch (e: Exception) {
                    // nothing
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })

        window.decorView.rootView.setOnTouchListener { v, event ->
            gesture.onTouchEvent(event)
        }
        changeFragment(fragments[fragmentIndex])
    }

    private fun changeFragment(f: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl1, f)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}