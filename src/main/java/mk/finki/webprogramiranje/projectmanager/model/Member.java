package mk.finki.webprogramiranje.projectmanager.model;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
public class Member {
	@Id
	private String id;	//avoid org.bson.types.ObjectId
	
	private String email;
	private String password;	//hash
	private String firstname;
	private String lastname;
	private String biography;
	private String avatar;
	
	private List<Experience> experience;
	private List<String> skills;	//tag names
	
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
	
	public String getFirstName(){
		return firstname;
	}
	
	public void setFirstName(String firstname){
		this.firstname = firstname;
	}
	
	public String getLastName(){
		return lastname;
	}
	
	public void setLastName(String lastname){
		this.lastname = lastname;
	}
	
	public String getBiography(){
		return biography;
	}
	
	public void setBiography(String biography){
		this.biography = biography;
	}
	
	public String getAvatar(){
		return avatar;
	}
	
	public void setAvatar(String avatar){
		this.avatar = avatar;
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
	
	@PersistenceConstructor
	public Member(String id, String email, String password, String firstname, String lastname, String biography, String avatar, List<Experience> experience, List<String> skills, Long timestamp){
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.biography = biography;
		this.avatar = avatar;
		this.experience = experience;
		this.skills = skills;
		this.timestamp = timestamp;
	}
	
	public Member(String email, String password, String firstname, String lastname){
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.biography = "";
		this.avatar = "";
		
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
	
	@PersistenceConstructor
	public Experience(String title, String type, Long dateFrom, Long dateTo, String description){
		this.title = title;
		this.type = type;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
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