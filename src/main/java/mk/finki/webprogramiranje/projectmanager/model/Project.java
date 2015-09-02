package mk.finki.webprogramiranje.projectmanager.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {
	@Id
	private String id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	private String logo;
	
	@NotNull
	private List<String> managers;
	
	@NotNull
	private List<String> employees;
	
	private Long dateDue;
	
	@NotNull
	private List<Task> tasks;

	@NotNull
	private List<CodeSnippet> codeSnippets;
	
	@NotNull
	private List<Attachment> attachments;

	@NotNull
	private List<Comment> comments;
	
	@NotNull
	private List<String> tags;
	
	@NotNull
	private Long timestamp;

	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
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
	
	public String getLogo(){
		return logo;
	}
	
	public void setLogo(String logo){
		this.logo = logo;
	}
	
	public List<String> getManagers(){
		return managers;
	}
	
	public void setManagers(List<String> managers){
		this.managers = managers;
	}
	
	public List<String> getEmployees(){
		return employees;
	}
	
	public void setEmployees(List<String> employees){
		this.employees = employees;
	}
	
	public Long getDateDue(){
		return dateDue;
	}
	
	public void setDateDue(Long dateDue){
		this.dateDue = dateDue;
	}
	
	public List<Task> getTasks(){
		return tasks;
	}
	
	public void setTasks(List<Task> tasks){
		this.tasks = tasks;
	}
	
	public List<CodeSnippet> getCodeSnippets(){
		return codeSnippets;
	}
	
	public void setCodeSnippets(List<CodeSnippet> codeSnippets){
		this.codeSnippets = codeSnippets;
	}
	
	public List<Attachment> getAttachments(){
		return attachments;
	}
	
	public void setAttachments(List<Attachment> attachments){
		this.attachments = attachments;
	}

	public List<Comment> getComments(){
		return comments;
	}
	
	public void setComments(List<Comment> comments){
		this.comments = comments;
	}
	
	public List<String> getTags(){
		return tags;
	}
	
	public void setTags(List<String> tags){
		this.tags = tags;
	}

	public Long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}
	
	public Project(){
		this.name = "";
		this.description = "";
		this.logo = "";
		this.managers = new ArrayList<String>();
		this.employees = new ArrayList<String>();
		this.dateDue = null;
		this.tasks = new ArrayList<Task>();
		this.codeSnippets = new ArrayList<CodeSnippet>();
		this.attachments = new ArrayList<Attachment>();
		this.comments = new ArrayList<Comment>();
		this.tags = new ArrayList<String>();
		
		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}

class Task {
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private List<String> assignedTo;
	@NotNull
	private Long dateDue;
	@NotNull
	private List<Subtask> subtasks;
	@NotNull
	private List<CodeSnippet> codeSnippets;
	@NotNull
	private List<Attachment> attachments;
	@NotNull
	private List<Comment> comments;
	@NotNull
	private Boolean finished;
	@NotNull
	private Long timestamp;
	
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
	
	public List<String> getAssignedTo(){
		return assignedTo;
	}
	
	public void setAssignedTo(List<String> assignedTo){
		this.assignedTo = assignedTo;
	}
	
	public Long getDateDue(){
		return dateDue;
	}
	
	public void setDateDue(Long dateDue){
		this.dateDue = dateDue;
	}
	
	public List<Subtask> getSubtasks(){
		return subtasks;
	}
	
	public void setSubtasks(List<Subtask> subtasks){
		this.subtasks = subtasks;
	}
	
	public List<CodeSnippet> getCodeSnippets(){
		return codeSnippets;
	}
	
	public void setCodeSnippets(List<CodeSnippet> codeSnippets){
		this.codeSnippets = codeSnippets;
	}
	
	public List<Attachment> getAttachments(){
		return attachments;
	}
	
	public void setAttachments(List<Attachment> attachments){
		this.attachments = attachments;
	}
	
	public List<Comment> getComments(){
		return comments;
	}
	
	public void setComments(List<Comment> comments){
		this.comments = comments;
	}
	
	public Boolean getFinished(){
		return finished;
	}
	
	public void setFinished(Boolean finished){
		this.finished = finished;
	}
	
	public Long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}
	
	public Task(){
		this.name = "";
		this.description = "";
		this.assignedTo = new ArrayList<String>();
		this.dateDue = null;
		this.subtasks = new ArrayList<Subtask>();
		this.codeSnippets = new ArrayList<CodeSnippet>();
		this.attachments = new ArrayList<Attachment>();
		this.comments = new ArrayList<Comment>();
		this.finished = false;
		
		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}

class Subtask {
	private String name;
	private String description;
	private Boolean finished;
	private Long timestamp;

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
	
	public Boolean getFinished(){
		return finished;
	}
	
	public void setFinished(Boolean finished){
		this.finished = finished;
	}
	
	public Long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}
	
	public Subtask(){
		this.name = "";
		this.description = "";
		this.finished = false;

		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}

abstract class ProjectResource {
	private String author;
	private String name;
	private String description;
	private String fileLocation;
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
	
	public String getFileLocation(){
		return fileLocation;
	}
	
	public void setFileLocation(String fileLocation){
		this.fileLocation = fileLocation;
	}
	
	public Long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}
	
	public ProjectResource(){
		this.author = "";
		this.name = "";
		this.description = "";
		this.fileLocation = "";

		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}

class Comment {
	private String author;	//members.id
	private String body;
	
	private Long timestamp;
	
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
	
	public Comment(){
		this.author = "";
		this.body = "";
		
		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}