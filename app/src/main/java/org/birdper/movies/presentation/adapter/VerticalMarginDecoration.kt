package org.birdper.movies.presentation.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalMarginDecoration(
    private val top: Int,
    private val inner: Int,
    private val bottom: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildLayoutPosition(view)) {
            0 -> {
                outRect.top = top.toPx(parent.context)
                outRect.bottom = inner.toPx(parent.context)
            }

            state.itemCount - 1 -> {
                outRect.bottom = bottom.toPx(parent.context)
            }

            else -> {
                outRect.bottom = inner.toPx(parent.context)
            }
        }
    }

    private fun Int.toPx(context: Context) =
        (this * context.resources.displayMetrics.density).toInt()
}

