package mk.finki.webprogramiranje.projectmanager.model;

public class CodeSnippet {
	
	private String author;
	private String name;
	private String description;
	private String code;
	private String extension;
	private Long timestamp;

	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getExtension(){
		return extension;
	}
	
	public void setExtension(String extension){
		this.extension = extension;
	}
	
	public Long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}
	
	public CodeSnippet(){
		this.author = "";
		this.name = "";
		this.description = "";
		this.code = "";
		this.extension = "";

		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}

