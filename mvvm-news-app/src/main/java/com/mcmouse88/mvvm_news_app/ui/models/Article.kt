package com.mcmouse88.mvvm_news_app.ui.models

import com.mcmouse88.mvvm_news_app.data.local.entities.ArticleEntity
import com.mcmouse88.mvvm_news_app.data.local.entities.SourceType
import com.mcmouse88.mvvm_news_app.data.remote.dto.ArticleDto
import java.io.Serializable

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable

data class Source(
    val id: String,
    val name: String
)

fun ArticleDto.toArticle(): Article {
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = Source(this.source.id, this.source.name),
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = Source(this.source.id, this.source.name),
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}

fun List<ArticleEntity>.toArticleList(): List<Article> {
    return this.map(ArticleEntity::toArticle)
}

fun List<ArticleDto>.toArticleList(): List<Article> {
    return this.map(ArticleDto::toArticle)
}

fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = SourceType(this.source.id, this.source.name),
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}
