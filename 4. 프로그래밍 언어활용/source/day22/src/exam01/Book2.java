package exam01;

import java.io.Serializable;

public class Book2 implements Serializable {

        private int isbn; // 도서 번호
        private String title; // 도서명
        private transient String author; // 저자   transient : 직렬화 배제

        public Book2 (int isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
        }

}
