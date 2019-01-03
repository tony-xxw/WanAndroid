package com.study.kotlin.wan.model

data class Blog(
        var id: Int,
        var originId: Int,
        var title: String,
        var chapterId: Int,
        var chapterName: String?,
        var envelopePic: Any,
        var link: String,
        var author: String,
        var origin: Any,
        var publishTime: Long,
        var zan: Any,
        var desc: Any,
        var visible: Int,
        var niceDate: String,
        var courseId: Int,
        var collect: Boolean
)