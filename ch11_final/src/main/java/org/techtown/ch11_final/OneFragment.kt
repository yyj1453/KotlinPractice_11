package com.example.ch11_jetpack

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.ch11_final.R
import org.techtown.ch11_final.databinding.FragmentOneBinding
import org.techtown.ch11_final.databinding.FragmentTwoBinding
import org.techtown.ch11_final.databinding.FragmentThreeBinding
import org.techtown.ch11_final.databinding.ItemRecyclerviewBinding
import java.util.NavigableMap

// 항목 뷰를 가지는 역할
class MyViewHolder(val binding: ItemRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root)

// 항목 구성자. 어뎁터
class MyAdapter(val datas: MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 항목 개수를 판단하기 위해 자동 호출
    override fun getItemCount(): Int {
        return datas.size
    }
    // 항목 뷰를 가지는 뷰 홀더를 준비하기 위해 자동 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder =
            MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(
            parent.context), parent, false))
    // 각 항목을 구성하기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        // 뷰에 데이터 출력
        binding.itemData.text = datas[position]
    }
}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val width = parent.width
        val height = parent.height

        val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(),
            R.drawable.kbo, null)
        var drWidth = dr?.intrinsicWidth
        var drHeight = dr?.intrinsicHeight

        var left = width / 2 - drWidth?.div(2) as Int
        var top = height / 2 - drHeight?.div(2) as Int

        c.drawBitmap(
            BitmapFactory.decodeResource(context.getResources(), R.drawable.kbo),
            left.toFloat(),
            top.toFloat(),
            null
        )
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1

        if (index % 3 == 0)
            outRect.set(10, 10, 10, 60)
        else
            outRect.set(10, 10, 10, 0)

        view.setBackgroundColor(Color.parseColor("#28A0FF"))
        ViewCompat.setElevation(view, 20.0f)
    }
}

class OnFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        val datas = mutableListOf<String>()
        for(i in 1..9) {
            datas.add("Item $i")
        }

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = MyAdapter(datas)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))
        return binding.root
    }
}


class OneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentOneBinding.inflate(inflater, container, false).root
    }

}