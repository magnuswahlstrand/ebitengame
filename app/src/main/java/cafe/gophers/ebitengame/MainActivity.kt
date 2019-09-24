package cafe.gophers.ebitengame

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import cafe.gophers.ebitengame.splendidmobile.Counter
import cafe.gophers.ebitengame.splendidmobile.EbitenView
import cafe.gophers.ebitengame.splendidmobile.VibrateDevice
import cafe.gophers.ebitengame.splendidmobile.Splendidmobile
import go.Seq
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity(), VibrateDevice {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Seq.setContext(applicationContext)

        val c = Counter()
        c.inc()
        Log.e("Magnus", c.value.toString())
        Splendidmobile.updateVibrateDevice(this)


    }

    private fun getEbitenView(): EbitenView {
        return this.findViewById(R.id.ebitenview) as EbitenView
    }

    override fun onPause() {
        super.onPause()
        this.getEbitenView().onPause()

    }

    override fun onResume() {
        super.onResume()
        this.getEbitenView().onResume()
    }


    override fun vibrate() {
        Log.e("Magnus", "vibrate!")
        val vibrator =  applicationContext?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }
}
