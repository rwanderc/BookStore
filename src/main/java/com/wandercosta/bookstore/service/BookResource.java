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
package com.wandercosta.bookstore.service;

import com.wandercosta.bookstore.entity.Book;
import com.wandercosta.bookstore.database.BookDB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class to represent the service.
 *
 * @author Wander Costa (wwww.wandercosta.com)
 */
@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    /**
     * Returns all the books.
     *
     * @return a {@link Response} with all the books.
     */
    @GET
    public Response list() {

        return Response.ok(BookDB.findAll()).build();

    }

    /**
     * Returns a single book.
     *
     * @param isbn The book's ISBN.
     * @return a {@link Response} with a single book, or 404 if not found.
     */
    @GET
    @Path("{isbn}")
    public Response get(@PathParam(value = "isbn") String isbn) {

        Book book = BookDB.find(isbn);

        return book != null
                ? Response.ok(book).build()
                : Response.status(404).build();

    }

    /**
     * Saves a new book.
     *
     * @param book The new book.
     * @return a {@link Response} with 201 if created; 400, otherwise.
     */
    @POST
    public Response save(Book book) {

        return BookDB.save(book)
                ? Response.status(201).build()
                : Response.status(400).build();

    }

    /**
     * Updates a book.
     *
     * @param isbn The book's ISBN.
     * @param book The book.
     * @return a {@link Response} with 200 if updated; 404, otherwise.
     */
    @POST
    @Path("{isbn}")
    public Response update(
            @PathParam(value = "isbn") String isbn,
            Book book) {

        return BookDB.update(isbn, book)
                ? Response.status(200).build()
                : Response.status(404).build();

    }

    /**
     * Removes a book.
     *
     * @param isbn The book's ISBN.
     * @return a {@link Response} with 204.
     */
    @DELETE
    @Path("{isbn}")
    public Response remove(@PathParam(value = "isbn") String isbn) {

        BookDB.remove(isbn);

        return Response.noContent().build();

    }

}
