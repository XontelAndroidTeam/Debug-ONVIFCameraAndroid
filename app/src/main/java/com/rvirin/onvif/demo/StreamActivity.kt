package com.xontel.onvif

import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pedro.vlc.VlcListener
import com.pedro.vlc.VlcVideoLibrary

/**
 * This activity helps us to show the live stream of an ONVIF camera thanks to VLC library.
 */
class StreamActivity : AppCompatActivity(), VlcListener, View.OnClickListener {


    private var vlcVideoLibrary: VlcVideoLibrary? = null
//    private var vlcVideoLibrary1: VlcVideoLibrary? = null
//    private var vlcVideoLibrary2: VlcVideoLibrary? = null
//    private var vlcVideoLibrary3: VlcVideoLibrary? = null
//    private var vlcVideoLibrary4: VlcVideoLibrary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stream)

        val surfaceView = findViewById<SurfaceView>(R.id.surfaceView)
//        val surfaceView1 = findViewById<SurfaceView>(R.id.surfaceView1)
//        val surfaceView2 = findViewById<SurfaceView>(R.id.surfaceView2)
//        val surfaceView3 = findViewById<SurfaceView>(R.id.surfaceView3)
//        val surfaceView4 = findViewById<SurfaceView>(R.id.surfaceView4)
        val bStartStop = findViewById<Button>(R.id.b_start_stop)
        bStartStop.setOnClickListener(this)
        vlcVideoLibrary = VlcVideoLibrary(this, this, surfaceView)
//        vlcVideoLibrary1 = VlcVideoLibrary(this, this, surfaceView1)
//        vlcVideoLibrary2 = VlcVideoLibrary(this, this, surfaceView2)
//        vlcVideoLibrary3 = VlcVideoLibrary(this, this, surfaceView3)
//        vlcVideoLibrary4 = VlcVideoLibrary(this, this, surfaceView4)
    }

    /**
     * Called by VLC library when the video is loading
     */
    override fun onComplete() {
        Toast.makeText(this, "Loading video...", Toast.LENGTH_LONG).show()
    }

    /**
     * Called by VLC library when an error occured (most of the time, a problem in the URI)
     */
    override fun onError() {
        Toast.makeText(this, "Error, make sure your endpoint is correct", Toast.LENGTH_SHORT).show()
        vlcVideoLibrary?.stop()
//        vlcVideoLibrary1?.stop()
//        vlcVideoLibrary2?.stop()
//        vlcVideoLibrary3?.stop()
//        vlcVideoLibrary4?.stop()
    }


    override fun onClick(v: View?) {
        playVideo(vlcVideoLibrary)
//        playVideo(vlcVideoLibrary1)
//        playVideo(vlcVideoLibrary2)
//        playVideo(vlcVideoLibrary3)
//        playVideo(vlcVideoLibrary4)
    }

    private fun playVideo(vlcVideoLibraryy: VlcVideoLibrary?) {
        vlcVideoLibraryy?.let { vlcVideoLibrary ->
            if (!vlcVideoLibrary.isPlaying) {
                val url = intent.getStringExtra(RTSP_URL)
                Log.e("@l0g", "url = $url")
                vlcVideoLibrary.play(url)
            } else {
                vlcVideoLibrary.stop()
            }
        }
    }
}

