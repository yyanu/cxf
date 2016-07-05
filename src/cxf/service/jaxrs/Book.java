package cxf.service.jaxrs;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author admin
 *
 */
/*<Book>
	<author>尼古拉·奥斯特洛夫斯基</author>
	<id>10</id>
	<name>钢铁是怎样炼成的？</name>
	<price>3.0</price>
</Book>*/
@XmlRootElement(name="Book")
public class Book {
	//id
    private String id;
    //书名
    private String name;
    //作者
    private String author;
    //价格
    private Double price;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
