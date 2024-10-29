package com.example.gmail.data

import com.example.gmail.R
import com.example.gmail.model.Content
import com.example.gmail.model.Image
import com.example.gmail.model.Sender
import com.example.gmail.model.Time
import com.example.gmail.model.Title

class Datasource {

    fun loadSenders() : List<Sender>{

        return listOf<Sender>(
            Sender(R.string.sender1),
            Sender(R.string.sender2),
            Sender(R.string.sender3),
            Sender(R.string.sender4),
            Sender(R.string.sender5),
            Sender(R.string.sender6),
            Sender(R.string.sender7),
            Sender(R.string.sender8),
            Sender(R.string.sender9),
            Sender(R.string.sender10),
            Sender(R.string.sender11),
            Sender(R.string.sender12),
            Sender(R.string.sender13),
            Sender(R.string.sender14),
            Sender(R.string.sender15)

            )

    }
    fun loadTitles(): List<Title>{
        return  listOf<Title>(
            Title(R.string.title1),
            Title(R.string.title2),
            Title(R.string.title3),
            Title(R.string.title4),
            Title(R.string.title5),
            Title(R.string.title6),
            Title(R.string.title7),
            Title(R.string.title8),
            Title(R.string.title9),
            Title(R.string.title10),
            Title(R.string.title11),
            Title(R.string.title12),
            Title(R.string.title13),
            Title(R.string.title14),
            Title(R.string.title15)

            )

    }
    fun loadContents(): List<Content>{
        return listOf<Content>(

            Content(R.string.content1),
            Content(R.string.content2),
            Content(R.string.content3),
            Content(R.string.content4),
            Content(R.string.content5),
            Content(R.string.content6),
            Content(R.string.content7),
            Content(R.string.content8),
            Content(R.string.content9),
            Content(R.string.content10),
            Content(R.string.content11),
            Content(R.string.content12),
            Content(R.string.content13),
            Content(R.string.content14),
            Content(R.string.content15)
            )

    }
    fun loadTime(): List<Time>{
        return listOf<Time>(
            Time(R.string.time1),
            Time(R.string.time2),
            Time(R.string.time3),
            Time(R.string.time4),
            Time(R.string.time5),
            Time(R.string.time6),
            Time(R.string.time7),
            Time(R.string.time8),
            Time(R.string.time9),
            Time(R.string.time10),
            Time(R.string.time11),
            Time(R.string.time12),
            Time(R.string.time13),
            Time(R.string.time14),
            Time(R.string.time15)


        )

    }
    fun loadImage(): List<Image>{
        return listOf<Image>(
            Image(R.drawable.img1),
            Image(R.drawable.img3),
            Image(R.drawable.img2),
            Image(R.drawable.img3),
            Image(R.drawable.img1),
            Image(R.drawable.img2),
            Image(R.drawable.img1),
            Image(R.drawable.img2),
            Image(R.drawable.img3),
            Image(R.drawable.img1),
            Image(R.drawable.img2),
            Image(R.drawable.img1),
            Image(R.drawable.img2),
            Image(R.drawable.img3),
            Image(R.drawable.img1)



            )
    }

}