package com.example.rectrun

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var draggableText: ImageView
    lateinit var draggableTextRect: Rect

    lateinit var detectableImage: ImageView
    lateinit var detectableImageRect: Rect

    lateinit var detectableImageTwo: ImageView
    lateinit var detectableImageTwoRect: Rect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        draggableText = findViewById(R.id.TextView)
        detectableImage = findViewById(R.id.imageView)
        detectableImageTwo = findViewById(R.id.imageView2)

        draggableTextRect = Rect(
            draggableText.x.toInt(),
            draggableText.y.toInt(),
            draggableText.x.toInt() + draggableText.width,
            draggableText.y.toInt() + draggableText.height)

        detectableImageRect = Rect(
            detectableImage.x.toInt(),
            detectableImage.y.toInt(),
            detectableImage.x.toInt() + detectableImage.width,
            detectableImage.y.toInt() + detectableImage.height)

        detectableImageTwoRect = Rect(
            detectableImageTwo.x.toInt(),
            detectableImageTwo.y.toInt(),
            detectableImageTwo.x.toInt() + detectableImageTwo.width,
            detectableImageTwo.y.toInt() + detectableImageTwo.height)

        var listener = View.OnTouchListener(function = { view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                draggableTextRect = Rect(
                    draggableText.x.toInt(),
                    draggableText.y.toInt(),
                    draggableText.x.toInt() + draggableText.width,
                    draggableText.y.toInt() + draggableText.height)

                detectableImageRect = Rect(
                    detectableImage.x.toInt(),
                    detectableImage.y.toInt(),
                    detectableImage.x.toInt() + detectableImage.width,
                    detectableImage.y.toInt() + detectableImage.height)

                detectableImageTwoRect = Rect(
                    detectableImageTwo.x.toInt(),
                    detectableImageTwo.y.toInt(),
                    detectableImageTwo.x.toInt() + detectableImageTwo.width,
                    detectableImageTwo.y.toInt() + detectableImageTwo.height)

                view.y = motionEvent.rawY - view.height * 5
                view.x = motionEvent.rawX - view.width / 2

                if (draggableTextRect.intersect(detectableImageRect)) {
                   val intent = Intent(this, GameOverActivity::class.java)
                    startActivity(intent)
                }
                if (draggableTextRect.intersect(detectableImageTwoRect)) {
                    val intent = Intent(this, GameOverActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        })
        draggableText.setOnTouchListener(listener)
    }
}