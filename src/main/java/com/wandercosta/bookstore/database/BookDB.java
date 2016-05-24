/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Wander Costa (www.wandercosta.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.wandercosta.bookstore.database;

import com.wandercosta.bookstore.entity.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to represent the in-memory database.
 *
 * @author Wander Costa (www.wandercosta.com)
 */
public abstract class BookDB {

    private static final Map<String, Book> BOOKS = new HashMap<>();

    /**
     * Returns all the books.
     *
     * @return all the books.
     */
    public static List<Book> findAll() {

        return new ArrayList(BOOKS.values());

    }

    /**
     * Returns a single book.
     *
     * @param isbn The book's ISBN.
     * @return a single book.
     */
    public static Book find(String isbn) {

        return BOOKS.get(isbn);

    }

    /**
     * Saves a new book.
     *
     * @param book The new book.
     * @return true if saved; false, otherwise.
     */
    public static boolean save(Book book) {

        return (BOOKS.putIfAbsent(book.getIsbn(), book) == null);

    }

    /**
     * Updates a book.
     *
     * @param isbn The book's ISBN.
     * @param book The book.
     * @return true if updated; false, otherwise.
     */
    public static boolean update(String isbn, Book book) {

        if (BOOKS.get(isbn) != null) {

            BOOKS.put(isbn, book);
            return true;

        }

        return false;

    }

    /**
     * Removes a book.
     *
     * @param isbn The book's ISBN.
     */
    public static void remove(String isbn) {

        BOOKS.remove(isbn);

    }

}
