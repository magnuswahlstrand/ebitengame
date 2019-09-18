package cafe.gophers.ebitengame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cafe.gophers.ebitengame.splendidmobile.Counter
import cafe.gophers.ebitengame.splendidmobile.EbitenView
import go.Seq


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Seq.setContext(applicationContext)

        val c = Counter()
        c.inc()
        c.value
        Log.e("Magnus", c.value.toString())
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
}
