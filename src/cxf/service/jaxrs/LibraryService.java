package cxf.service.jaxrs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/library")
@Produces("text/xml") //默认json 此时xml
public class LibraryService {
	private Map<String, Book> books = new HashMap<String, Book>();

    public LibraryService(){
        init();  //初始化五本书
    }

    /**
     * 获取
     * @param id 索引
     * @return 书
     */
    @GET
    @Path("/books/{id}/")
    public Book getBook(@PathParam("id") String id){
        return books.get(id);
    }

    /**
     * 更新
     * @param book
     * @return
     */
    @PUT
    @Path("/books/")
    public Response updateBook(Book book){
        Response r;
        if(book == null){
            r = Response.noContent().build();
            return r;
        }

        String id = book.getId();
        Book b = books.get(id);
        if(b != null){
            books.put(id, book);
            r = Response.ok(true, MediaType.TEXT_PLAIN).build();
        }else{
            r = Response.notModified().build();
        }

        return r;
    }

    /**
     * 添加
     * @param book
     * @return
     */
    @POST
    @Path("/books/")
    public Response addBook(Book book){
        Response r;
        if(book == null){
            r = Response.notModified().build();
        }else{
            books.put(book.getId(), book);
            r = Response.ok(true, MediaType.TEXT_PLAIN).build();
        }

        return r;
    }

    /**
     * 删除
     * @param book
     * @return
     */
    @DELETE
    @Path("/books/{id}/")
    public Response deleteBook(@PathParam("id") String id){
        Response r;
        Book book = books.get(id);

        if(book == null){
            r = Response.notModified("id不存在").build();
        }else{
            books.remove(id);
            r = Response.ok(book, MediaType.APPLICATION_XML).build();
        }

        return r;
    }


    /**
     * 初始化，在图书馆里加几本书
     */
    private void init(){
        Book book = null;

        book = new Book();      
        book.setAuthor("CSDN");
        book.setId("blog");
        book.setName("如何使用博客");
        book.setPrice(3.0);     
        books.put(book.getId(), book);

        book = new Book();
        book.setAuthor("CSDN");
        book.setId("app");
        book.setName("如何下载CSDN移动客户端");
        book.setPrice(30.0);
        books.put(book.getId(), book);

        book = new Book();
        book.setAuthor("CSDN");
        book.setId("resource");
        book.setName("如何下载CSDN资源");
        book.setPrice(5.0);
        books.put(book.getId(), book);

        book = new Book();
        book.setAuthor("CSDN");
        book.setId("rs");
        book.setName("JAX-RS详解");
        book.setPrice(15.0);
        books.put(book.getId(), book);
    }
}
