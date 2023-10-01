package com.mcmouse88.mvvm_news_app.ui.models

import com.mcmouse88.mvvm_news_app.data.remote.dto.ArticleDto
import com.mcmouse88.mvvm_news_app.data.remote.dto.SourceDto

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String
)

fun ArticleDto.toArticle(): Article {
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = this.source,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}

fun List<ArticleDto>.toArticleList(): List<Article> {
    return this.map(ArticleDto::toArticle)
}
