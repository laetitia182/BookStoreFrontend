package com.example.bookstorefrontend.api.reviews

/**
 * Data model representing a review for a book.
 * This model contains information about the reviewer, the review text, and the rating (in stars).
 *
 * @property reviewer The name of the person who wrote the review.
 * @property text The content of the review written by the reviewer.
 * @property stars The rating given by the reviewer, typically out of 5 stars.
 */
class ReviewModel(
    var reviewer: String,
    var text: String,
    var stars: Int
)
