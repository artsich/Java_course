package com.bookshop;

import java.util.Date;
import java.util.List;

import com.bookshop.core.comparator.BookComparators;
import com.bookshop.core.comparator.OrderComparators;
import com.bookshop.core.comparator.RequestBookComparator;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.StorageException;
import com.bookshop.service.ServiceBook;
import com.bookshop.service.ServiceOrder;
import com.bookshop.service.ServiceRequestBook;

public class BookShop {
	private ServiceOrder serviceOrder;
	private ServiceBook serviceBook;
	private ServiceRequestBook serviceRequests;

	public BookShop() { 
		init();
	}
	
	private void init() { 
		serviceOrder = new ServiceOrder();
		serviceBook = new ServiceBook();
		serviceRequests = new ServiceRequestBook();
	}
	
	public void addBook(Book book, int quantity) throws StorageException {	
		serviceBook.add(book, quantity);
	}
	
	public void addOrder(Order order) throws StorageException {
		serviceOrder.add(order);
	}
	
	public void derigisterBook(Book book, int quantity) throws StorageException { 
		serviceRequests.deregisterBook(book, quantity);
	}

	public void equipOrder(Order order) throws StorageException { 
		serviceOrder.equip(order);
	}
	
	public void closeOrder(Order order) throws StorageException  { 
		serviceOrder.add(order);
	}
	
	public List<Book> getBooks() { 
		return serviceBook.sortBy(BookComparators.getComparator(BookComparators.Type.NAME));
	}
	
	public List<Book> getBooks(BookComparators.Type type) { 
		return serviceBook.sortBy(BookComparators.getComparator(type));
	}	
	
	public List<Order> getOrders() { 
		return serviceOrder.sortBy(OrderComparators.getComparator(OrderComparators.Type.RELEASE));
	}

	public List<Order> getOrders(OrderComparators.Type type) { 
		return serviceOrder.sortBy(OrderComparators.getComparator(type));
	}
	
	public List<RequestsBook> getRequest(RequestBookComparator.Type type) { 
		return serviceRequests.sortBy(RequestBookComparator.getComparator(type));
	}
	
	public List<RequestsBook> getRequest() {
		return serviceRequests.sortBy(RequestBookComparator.getComparator(RequestBookComparator.Type.REQUESTS));		
	}
	
	public List<Order> getCompletedOrder(Date min, Date max, OrderComparators.Type type) { 
		return serviceOrder.getCompleateForPeriod(min, max, type);
	}
	
	public int getEarnedMoney(Date min, Date max) { 
		return serviceOrder.getEarnedMoney(min, max);
	}

	public int getCompletedOrderCount(Date min, Date max) { 
		return serviceOrder.getCountCompleateForPeriod(min, max);
	}
		
	public List<Book> getAncientBook(BookComparators.Type type) { 
		return serviceBook.ancientBooks(BookComparators.getComparator(type));
	}		
	
}