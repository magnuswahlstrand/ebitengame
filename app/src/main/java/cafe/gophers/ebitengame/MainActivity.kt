package cafe.gophers.ebitengame

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import cafe.gophers.ebitengame.splendidmobile.*
import cafe.gophers.ebitengame.splendidmobile.GameMobileInterface
import cafe.gophers.ebitengame.splendidmobile.EbitenView
import cafe.gophers.ebitengame.splendidmobile.Splendidmobile
import go.Seq
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity(), GameMobileInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Seq.setContext(applicationContext)

        Splendidmobile.setMobileConnector(this)
    }

    private fun getEbitenView(): EbitenView {
        return this.findViewById(R.id.ebitenview) as EbitenView
    }

    override fun onPause() {
        super.onPause()
        this.getEbitenView().suspendGame()

    }

    override fun getDeviceID(): String {
        return Settings.Secure.getString(contentResolver,
            Settings.Secure.ANDROID_ID)
    }

    override fun onResume() {
        super.onResume()
        this.getEbitenView().resumeGame()
    }

    override fun showEndDialog() {
        //Don't do anything yet
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
