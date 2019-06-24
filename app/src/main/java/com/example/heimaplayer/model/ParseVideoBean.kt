package com.example.heimaplayer.model

data class ParseVideoBean(
    val duration: Int,
    val error: Boolean,
    val hcVideoUrl: String,
    val hdVideoUrl: String,
    val logined: Boolean,
    val message: String
)