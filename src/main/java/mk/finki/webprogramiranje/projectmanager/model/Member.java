package mk.finki.webprogramiranje.projectmanager.model;
 
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
public class Member {
	@Id
	@NotNull
	private String id;	//avoid org.bson.types.ObjectId
	
	@Indexed(unique = true)
	private String email;
	
	private String password;	//hash
	
	@NotNull
	private String firstname;
	
	@NotNull
	private String lastname;
	
	@NotNull
	private String biography;
	
	@NotNull
	private String picture;
	
	@NotNull
	private List<Experience> experience;
	
	@NotNull
	private List<String> skills;	//tag names
	
	@NotNull
	private Long timestamp;

	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	
	public String getLastname(){
		return lastname;
	}
	
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	
	public String getBiography(){
		return biography;
	}
	
	public void setBiography(String biography){
		this.biography = biography;
	}
	
	public String getPicture(){
		return picture;
	}
	
	public void setPicture(String picture){
		this.picture = picture;
	}
	
	public List<Experience> getExperience(){
		return experience;
	}
	
	public void setExperience(List<Experience> experience){
		this.experience = experience;
	}
	
	public List<String> getSkills(){
		return skills;
	}
	
	public void setSkills(List<String> skills){
		this.skills = skills;
	}
	
	public Long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}
	
	public Member(){
		this.email = "";
		this.password = "";
		this.firstname = "";
		this.lastname = "";
		this.biography = "";
		this.picture = "";
		
		this.experience = new ArrayList<Experience>();
		this.skills = new ArrayList<String>();
		
		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}

class Experience {
	private String title;
	private String type;
	private Long dateFrom;
	private Long dateTo;
	private String description;
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public Long getDateFrom(){
		return dateFrom;
	}
	
	public void setDateFrom(Long dateFrom){
		this.dateFrom = dateFrom;
	}
	
	public Long getDateTo(){
		return dateTo;
	}
	
	public void setDateTo(Long dateTo){
		this.dateTo = dateTo;
	}
	
	public String getDescription(){
		return description;
	} 
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public Experience(){
		this.title = "";
		this.type = "";
		this.dateFrom = null;
		this.dateTo = null;
		this.description = "";
	}
}