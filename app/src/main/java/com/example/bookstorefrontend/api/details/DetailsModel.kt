package com.example.bookstorefrontend.api.details

/**
 * Data model representing detailed information about a book.
 * This model stores additional information about the book, such as the author, year of publication, type, and other relevant details.
 *
 * @property id Unique identifier for the book.
 * @property author The name of the author of the book.
 * @property year The year the book was published.
 * @property type The genre or type/category of the book.
 * @property publisher The publisher of the book.
 * @property language The language in which the book is written.
 * @property isbn The ISBN (International Standard Book Number) of the book.
 */
class DetailsModel(
    var id: Int,
    var author: String,
    var year: Int,
    var type: String,
    var publisher: String,
    var language: String,
    var isbn: String
)
