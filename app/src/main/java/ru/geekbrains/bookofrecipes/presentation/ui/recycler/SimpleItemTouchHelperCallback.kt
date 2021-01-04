package ru.geekbrains.bookofrecipes.presentation.ui.recycler

import android.graphics.Canvas
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors

class SimpleItemTouchHelperCallback(private val adapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
//        Log.e("isLongPressDragEnabled", "started ")
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
//        Log.e("isItemViewSwipeEnabled", "started ")
        return true
    }

    override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
    ): Int {
//        Log.e("getMovementFlags", "started ")
        // Set movement flags based on the layout manager
        return if (recyclerView.layoutManager is GridLayoutManager) {
            val dragFlags =
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            makeMovementFlags(
                    dragFlags,
                    swipeFlags
            )
        } else {
            val dragFlags =
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            makeMovementFlags(
                    dragFlags,
                    swipeFlags
            )
        }
    }

    override fun onMove(
            recyclerView: RecyclerView,
            source: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ): Boolean {
//        Log.e("onMove", "started")
        if (source.itemViewType != target.itemViewType) {
            return false
        }
        // Notify the adapter of the move
        adapter.onItemMove(source.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
//        Log.e("onSwiped", "started")
        // Notify the adapter of the dismissal
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
    ) {
//        Log.e("onChildDraw", "started ")
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Fade out the view as it is swiped out of the parent's bounds
            val alpha =
                    MaterialColors.ALPHA_FULL - Math.abs(dX) / viewHolder.itemView.width.toFloat()
            viewHolder.itemView.alpha = alpha
            viewHolder.itemView.translationX = dX
        } else {
            super.onChildDraw(
                    c,
                    recyclerView!!, viewHolder, dX, dY, actionState, isCurrentlyActive
            )
        }
    }


    override fun onSelectedChanged(
            viewHolder: RecyclerView.ViewHolder?,
            actionState: Int
    ) {
//        Log.e("onSelectedChanged", "started ")
        // We only want the active item to change
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is ItemTouchHelperViewHolder) {
                // Let the view holder know that this item is being moved or dragged
                val itemViewHolder =
                        viewHolder as ItemTouchHelperViewHolder
                itemViewHolder.onItemSelected()
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
    ) {
//        Log.e("clearView", "started ")
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.alpha = MaterialColors.ALPHA_FULL
        if (viewHolder is ItemTouchHelperViewHolder) {
            // Tell the view holder it's time to restore the idle state
            val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
            itemViewHolder.onItemClear()
        }
    }
}
