package controller;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/books")
public class Servlet1 extends HttpServlet {
   ArrayList<Book> booksList = new ArrayList<Book>();

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
   {
      // booksList.clear();
      // booksList.add(new Book(1, "Book1", "Author1", 100));
      // booksList.add(new Book(2, "Book2", "Author2", 200));
      // booksList.add(new Book(3, "Book3", "Author3", 300));
      // booksList.add(new Book(4, "Book4", "Author4", 400));
      
      booksList.clear();

      try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "")) {

         Statement stmt = con.createStatement();

         String strSelect = "select * from book_table";

         ResultSet rslt = stmt.executeQuery(strSelect);

         int rowCount = 0;

         while (rslt.next()) {
            int id = rslt.getInt("Id");
            String title = rslt.getString("Title");
            String author = rslt.getString("Author");
            int price = rslt.getInt("Price");
            rowCount = rowCount + 1;
            System.out.println(id + "," + title + ", " + price + "," + author);
            booksList.add(new Book(id, title, author, price));
         }

         Gson g = new Gson();
         String books = g.toJson(booksList);
         resp.getWriter().print(books);

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
   {

      System.out.println("INSERT");

      String requestData = req.getReader().lines().collect(Collectors.joining());
      Gson g = new Gson();
      Book newBook = g.fromJson(requestData, Book.class);
      System.out.println(newBook.getId() + " " + newBook.getTitle() + " " + newBook.getAuthor() + " " + newBook.getPrice());
      
      try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root","")) 
      {
         String strInsert = "Insert into book_table (title,author,price) values (?,?,?)";
         PreparedStatement prepstmt = con.prepareStatement(strInsert);
         prepstmt.setString(1, newBook.getTitle());
         prepstmt.setString(2, newBook.getAuthor());
         prepstmt.setFloat(3, newBook.getPrice());           
         int rslt = prepstmt.executeUpdate();
         System.out.println(rslt + " Rows has been Inserted");
      }

      catch (SQLException e) 
      {
            e.printStackTrace();
      }

   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("Books doPUT");
      String requestData = req.getReader().lines().collect(Collectors.joining());
      System.out.println(requestData);
      Gson g = new Gson();
      Book newBook = g.fromJson(requestData, Book.class);
      System.out.println(newBook.getId() + " " + newBook.getTitle() + " " + newBook.getAuthor() + " " + newBook.getPrice());
      try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root","")) {
         String strDelete = "select * from book_table where id = ?";

            PreparedStatement prepStmt = con.prepareStatement(strDelete);

            int id = Integer.parseInt(req.getParameter("id"));

            prepStmt.setInt(1, id);

            ResultSet rslt = prepStmt.executeQuery();

            System.out.println(rslt + " rows updated");

            int rowCount = 0;

            while(rslt.next())
            {
               int idd = rslt.getInt("id"); 
               String title = rslt.getString("title"); 
               String author = rslt.getString("author"); 
               int price = rslt.getInt("price"); 
               rowCount = rowCount + 1;
               System.out.println(idd+","+title + ", " + price + ","+ author);
               booksList.add(new Book (idd, title, author, price));

                req.setAttribute("id", idd);
            }
      } catch (Exception e) {
  
      }
   
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("Books doDELETE");
      String requestData = req.getReader().lines().collect(Collectors.joining());
      System.out.println(requestData);
      Gson g = new Gson();
      // Student newStudent = new Gson().fromJson(requestData, Student.class);
      Book newBook = g.fromJson(requestData, Book.class);
      System.out.println(newBook.getId() + " " + newBook.getTitle() + " " + newBook.getAuthor() + " " + newBook.getPrice());
      try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root","")) {
         
         String strDelete = "delete from book_Table where id = ?";
         PreparedStatement prepStmt = con.prepareStatement(strDelete);
         int id = Integer.parseInt(req.getParameter("id"));
         prepStmt.setInt(1, id);
         int rslt = prepStmt.executeUpdate();
         System.out.println(rslt + " rows has been deleted");

      } 
      catch (Exception e) 
      {

      }
   }
}
