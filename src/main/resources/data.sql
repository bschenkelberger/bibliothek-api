INSERT INTO book_genre (name) VALUES ('Biography');
INSERT INTO book_genre (name) VALUES ('Classic');
INSERT INTO book_genre (name) VALUES ('Comic');
INSERT INTO book_genre (name) VALUES ('Crime');
INSERT INTO book_genre (name) VALUES ('Essay');
INSERT INTO book_genre (name) VALUES ('Fable');
INSERT INTO book_genre (name) VALUES ('Fairy tale');
INSERT INTO book_genre (name) VALUES ('Fan fiction');
INSERT INTO book_genre (name) VALUES ('Fantasy');
INSERT INTO book_genre (name) VALUES ('Folklore');
INSERT INTO book_genre (name) VALUES ('Historical fiction');
INSERT INTO book_genre (name) VALUES ('Horror');
INSERT INTO book_genre (name) VALUES ('Humor');
INSERT INTO book_genre (name) VALUES ('Lab Report');
INSERT INTO book_genre (name) VALUES ('Memoir');
INSERT INTO book_genre (name) VALUES ('Mystery');
INSERT INTO book_genre (name) VALUES ('Mythology');
INSERT INTO book_genre (name) VALUES ('Owners Manual');
INSERT INTO book_genre (name) VALUES ('Picture book');
INSERT INTO book_genre (name) VALUES ('Reference book');
INSERT INTO book_genre (name) VALUES ('Science fiction');
INSERT INTO book_genre (name) VALUES ('Speech');
INSERT INTO book_genre (name) VALUES ('Textbook');
INSERT INTO book_genre (name) VALUES ('Thriller');
INSERT INTO book_genre (name) VALUES ('Western');

INSERT INTO book_source (name) VALUES ('Amazon Kindle');
INSERT INTO book_source (name) VALUES ('EPUB');
INSERT INTO book_source (name) VALUES ('Hardover');
INSERT INTO book_source (name) VALUES ('Mobipocket');
INSERT INTO book_source (name) VALUES ('PDF');
INSERT INTO book_source (name) VALUES ('Softcover');

INSERT INTO book (book_name, book_lend, book_deleted, book_genre_id, book_source_id)
VALUES ('Harry Potter und der Stein der Weisen', 0, 0, 9, 3);

INSERT INTO book (book_name, book_lend, book_deleted, book_genre_id, book_source_id)
VALUES ('Harry Potter und die Kammer des Schreckens', 0, 0, 9, 1);

INSERT INTO book (book_name, book_lend, book_deleted, book_genre_id, book_source_id)
VALUES ('Leichenblaesse', 0, 0, 24, 1);

INSERT INTO customer (customer_name, customer_first_name) VALUES ('Mustermann', 'Max');

INSERT INTO customer_lend_book (customer_lend_book_lend_on_date, customer_lend_book_returned_on_date, book_id, customer_id)
VALUES ('2017-11-11', '2018-01-07', 3, 1);