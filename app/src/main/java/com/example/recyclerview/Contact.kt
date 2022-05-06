package com.example.recyclerview

data class Contact (val name : String, val age : Int) {
    val imageUrl = "https://picsum.photos/200/300?random=$age"
}
