package com.pvstechlabs.app.data.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "genre_id")
	private Genre genre;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "publish_date")
	@Temporal(TemporalType.DATE)
	private Date publishDate;

	@Column(name = "stock")
	private int stock;

	@Column(name = "price")
	private Double price;

	@Column(name = "active")
	private boolean active;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Genre getgenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date date) {
		this.publishDate = date;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", genre=" + genre + ", title=" + title + ", author=" + author + ", rating="
				+ rating + ", publishDate=" + publishDate + ", stock=" + stock + ", price=" + price + ", active="
				+ active + "]";
	}
}
