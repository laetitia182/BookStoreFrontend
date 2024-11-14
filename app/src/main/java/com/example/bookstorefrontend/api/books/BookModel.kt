package com.example.bookstorefrontend.api.books

/**
 * Data model representing a book in the BookStore frontend application.
 *
 * @property id Unique identifier for the book.
 * @property author The name of the author who wrote the book.
 * @property title The title of the book.
 */
data class BookModel(
    var id: Int,
    var author: String,
    var title: String
)
