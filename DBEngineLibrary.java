package librarysystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBEngineLibrary {
    
    
    private Connection conn;

    public DBEngineLibrary() throws Exception {

        String host = "jdbc:mysql://localhost:3306/artistsmysql";
        String uName = "root";
        String uPass = "password";

        conn = DriverManager.getConnection( host, uName, uPass );
        
        

    }
    
    public void AddBook(Book book) throws Exception{
        String insertTableSQL = "INSERT INTO books"
		+ "(TITLE, ISBN, AUTHOR, PUBLISHER) VALUES"
		+ "(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, book.getTitle().toUpperCase());
            preparedStatement.setString(2, book.getISBN().toUpperCase());
            preparedStatement.setString(3, book.getAuthor().toUpperCase());
            preparedStatement.setString(4, book.getPublisher().toUpperCase());
            preparedStatement.executeUpdate();
    }


   public ArrayList<Book> searchBooks(String title) throws Exception{
        ArrayList<Book> list = new ArrayList<Book>();
        ResultSet result = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        
        if(title == null || title.equals("")){  
            
            statement = conn.createStatement();
            String sql = "select * from books";
            result = statement.executeQuery(sql);

            while(result.next()) {
                    
                   int id = result.getInt("id");
                   String Title = result.getString("Title");
                   String Author = result.getString("Author");
                   String isbn = result.getString("isbn");
                   String Publisher = result.getString("Publisher");
                   
                   Book newBook = new Book(Title, Author, isbn, Publisher,0);
                   list.add(newBook);

                   System.out.println(Title);
                   System.out.println(Author);
                   System.out.println(isbn);
                   System.out.println(Publisher);
                   System.out.println(0);
            }         
            statement.close();
            
        }
        else {
            String sql = "select * from books where upper(TITLE) like ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, '%' + title.toUpperCase() + '%');

            result = preparedStatement.executeQuery();

            while(result.next()) {

                int id = result.getInt("id");
                String Title = result.getString("Title");
                String Author = result.getString("Author");
                String isbn = result.getString("ISBN");
                String Publisher = result.getString("Publisher");
                

                Book newBook = new Book(Title, Author, isbn, Publisher, id);
                list.add(newBook);

                System.out.println(Title);
                System.out.println(Author);
                System.out.println(isbn);
                System.out.println(Publisher);
                System.out.println(0);
                
            }
            preparedStatement.close();
        }
        
        result.close();
        return list;

    }
    public void deleteBook(int Id) throws Exception{
        
        String sql = "delete from books where id = ?";
        System.out.println("Delete ID is" + Id );
        PreparedStatement deleteStatement = null;
        try{
            deleteStatement = conn.prepareStatement(sql);
            deleteStatement.setInt(0, Id);
            deleteStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
             deleteStatement.close();
        }  
    }
   
   
}