package com.android.my.paintapp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlin.random.Random

class DrawingFragment : Fragment() {

    private lateinit var drawingView: DrawingView
    private lateinit var clearButton: Button
    private lateinit var colorButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_drawing, container, false)
        drawingView = view.findViewById(R.id.drawingView)
        clearButton = view.findViewById(R.id.clearButton)
        colorButton = view.findViewById(R.id.colorButton)

        clearButton.setOnClickListener {
            drawingView.clearCanvas()
        }

        colorButton.setOnClickListener {
            val randomColor = Color.rgb(
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256)
            )
            drawingView.changeColor(randomColor)
        }

        return view
    }
}
