package com.hbs.worldcup.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.motion.widget.MotionLayout
import com.hbs.worldcup.R
import com.hbs.worldcup.databinding.ParallaxScrollingViewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ParallaxScrollingView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    MotionLayout(context, attributeSet, defStyleAttr) {

    var destinationListener : DestinationListener? = null
    var progressListener : ProgressListener? = null

    init {
        val binding = ParallaxScrollingViewBinding.inflate(LayoutInflater.from(context), this, true)
        binding.root.setTransitionListener(object : TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                progressListener?.progress(progress)
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if(currentId == R.id.end_to_a) {
                    progressListener?.progress(1.0f)
                    postDelayed(Destination.LEFT)
                } else if (currentId == R.id.end_to_b) {
                    progressListener?.progress(1.0f)
                    postDelayed(Destination.RIGHT)
                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }
        })
    }

    private fun postDelayed(destination: Destination){
        postDelayed({
            destinationListener?.complete(destination)
        }, 800L)
    }

    enum class Destination {
        LEFT, RIGHT;
    }

    fun interface DestinationListener {
        fun complete(destination: Destination)
    }

    fun interface ProgressListener {
        fun progress(progress:Float)
    }
}