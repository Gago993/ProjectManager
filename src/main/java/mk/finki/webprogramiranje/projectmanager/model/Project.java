package mk.finki.webprogramiranje.projectmanager.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {
	@Id
	private String id;
	
	private String name;
	private String description;
	private String icon;
	
	private List<String> managers;
	private List<String> employees;
	
	private Long dateDue;
	
	private List<Task> tasks;
	private List<CodeSnippet> codeSnippets;
	private List<Attachment> attachments;

	private List<String> comments;
	private List<String> tags;
	
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
	
	public String getIcon(){
		return icon;
	}
	
	public void setIcon(String icon){
		this.icon = icon;
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

	public List<String> getComments(){
		return comments;
	}
	
	public void setComments(List<String> comments){
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
	
	@PersistenceConstructor
	public Project(String id, String name, String description, String icon, List<String> managers, List<String> employees, Long dateDue, List<Task> tasks, List<CodeSnippet> codeSnippets, List<Attachment> attachments, List<String> comments, List<String> tags, Long timestamp){
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.dateDue = dateDue;
		this.managers = managers;
		this.employees = employees;
		this.tasks = tasks;
		this.codeSnippets = codeSnippets;
		this.attachments = attachments;
		this.comments = comments;
		this.tags = tags;
		this.timestamp = timestamp;
	}
	
	public Project(){
		this.name = "";
		this.description = "";
		this.icon = "";
		this.managers = new ArrayList<String>();
		this.employees = new ArrayList<String>();
		this.dateDue = null;
		this.tasks = new ArrayList<Task>();
		this.codeSnippets = new ArrayList<CodeSnippet>();
		this.attachments = new ArrayList<Attachment>();
		this.comments = new ArrayList<String>();
		this.tags = new ArrayList<String>();
		
		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}

class Task {
	@Id
	private String id;
	
	private String name;
	private String description;
	
	private List<String> assignedTo;
	
	private Long dateDue;
	
	private List<Subtask> subtasks;
	private List<CodeSnippet> codeSnippets;
	private List<Attachment> attachments;
	
	private List<String> comments;
	
	private Boolean finished;
	
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
	
	public List<String> getComments(){
		return comments;
	}
	
	public void setComments(List<String> comments){
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
	
	@PersistenceConstructor
	public Task(String id, String name, String description, List<String> assignedTo, Long dateDue, List<Subtask> subtasks, List<CodeSnippet> codeSnippets, List<Attachment> attachments, List<String> comments, Boolean finished, Long timestamp){
		this.id = id;
		this.name = name;
		this.description = description;
		this.assignedTo = assignedTo;
		this.dateDue = dateDue;
		this.subtasks = subtasks;
		this.codeSnippets = codeSnippets;
		this.attachments = attachments;
		this.comments = comments;
		this.finished = finished;
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
		this.comments = new ArrayList<String>();
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
	
	@PersistenceConstructor
	public Subtask(String name, String description, Boolean finished, Long timestamp){
		this.name = name;
		this.description = description;
		this.finished = finished;
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
	
	public ProjectResource(String author, String name, String description, String fileLocation, Long timestamp){
		this.author = author;
		this.name = name;
		this.description = description;
		this.fileLocation = fileLocation;
		this.timestamp = timestamp;
	}
	
	public ProjectResource(String author, String name, String description, String fileLocation){
		this.author = author;
		this.name = name;
		this.description = description;
		this.fileLocation = fileLocation;

		this.timestamp = System.currentTimeMillis() / 1000L;
	}
}

class CodeSnippet extends ProjectResource {
	@PersistenceConstructor
	public CodeSnippet(String author, String name, String description, String fileLocation, Long timestamp){
		super(author, name, description, fileLocation, timestamp);
	}
	
	public CodeSnippet(String author, String name, String description, String fileLocation){
		super(author, name, description, fileLocation);
	}
}

class Attachment extends ProjectResource {
	@PersistenceConstructor
	public Attachment(String author, String name, String description, String fileLocation, Long timestamp){
		super(author, name, description, fileLocation, timestamp);
	}
	
	public Attachment(String author, String name, String description, String fileLocation){
		super(author, name, description, fileLocation);
	}
}