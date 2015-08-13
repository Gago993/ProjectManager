package mk.finki.webprogramiranje.projectmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
	@Id
	private String id;
	
	private String author;	//members.id
	private String body;
	
	private Long timestamp;

	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getBody(){
		return body;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public Long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}
	
	@PersistenceConstructor
	public Comment(String id, String author, String body, Long timestamp){
		this.id = id;
		this.author = author;
		this.body = body;
		this.timestamp = timestamp;
	}
	
	public Comment(String author, String body){
		this.author = author;
		this.body = body;
		
		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}