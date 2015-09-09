package mk.finki.webprogramiranje.projectmanager.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import mk.finki.webprogramiranje.projectmanager.model.Attachment;
import mk.finki.webprogramiranje.projectmanager.model.CodeSnippet;
import mk.finki.webprogramiranje.projectmanager.model.Project;
import mk.finki.webprogramiranje.projectmanager.repository.ProjectRepository;
import mk.finki.webprogramiranje.projectmanager.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository repository;
	
	@Autowired
	ServletContext servletContext;

	public Project save(Project project) {
		return repository.save(project);
	}
	
	public boolean removeLogo(Project project) {
		try {
			String oldLogo = project.getLogo();
			if(!oldLogo.isEmpty()){
				boolean deleted = new File(servletContext.getRealPath("/app/uploads/logos/" + oldLogo + ".png")).delete();
				if(!deleted){
					throw new IOException("Can not delete previous project logo.");
				}
				
				project.setLogo("");
				this.save(project);
				
				return true;
			}
			
			return true;
		}catch(IOException exception){
			return false;
		}
	}
	
	public boolean saveLogo(Project project, MultipartFile logo) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(logo.getBytes());
			BufferedImage originalImage = ImageIO.read(in);
			in.close();
			
			BufferedImage resizedImage = ServiceUtilities.resizeBufferedImage(originalImage);
			
			String oldLogo = project.getLogo();
			if(!oldLogo.isEmpty()){
				boolean deleted = new File(servletContext.getRealPath("/app/uploads/logos/" + oldLogo + ".png")).delete();
				if(!deleted){
					throw new IOException("Can not delete previous project logo.");
				}
			}
			
			String filename = ServiceUtilities.getRandomString();
			
			File file = new File(servletContext.getRealPath("/app/uploads/logos/" + filename + ".png"));
			ImageIO.write(resizedImage, "png", file);
			
			project.setLogo(filename);
			this.save(project);
			
			return true;
		}catch(IOException exception){
			//should be logged in a production environment
			return false;
		}
	}

	public Iterable<Project> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
	}
	
	public Iterable<Project> findAll(Iterable<String> ids) {
		return repository.findAll(ids);
	}

	public Project findById(String id) {
		return repository.findOne(id);
	}
	
	public List<Project> findProjectsForMember(String memberId) {
		return repository.findProjectsForMember(memberId);
	}

	public void delete(String id) {
		repository.delete(id);
	}

	public void delete(Project project) {
		repository.delete(project);
	}
	
	public boolean saveAttachment(Project project, MultipartFile attachment, String name, String description, String author) {
		try {
			String[] filenameParts = attachment.getOriginalFilename().split("\\.");
			String extension = "";
			if(filenameParts.length > 0){
				extension = filenameParts[filenameParts.length - 1];
			}
			
			String filename = ServiceUtilities.getRandomString() + (extension != "" ? "." + extension : "");
			
			File file = new File(servletContext.getRealPath("/app/uploads/attachments/" + filename));
			
			FileOutputStream out = new FileOutputStream(file);
			out.write(attachment.getBytes());
			out.close();
			
			Attachment newAttachment = new Attachment();

			newAttachment.setName(name);
			newAttachment.setDescription(description);
			newAttachment.setAuthor(author);
			newAttachment.setFileLocation(filename);
			
			project.getAttachments().add(newAttachment);
			this.save(project);
			
			return true;
		}catch(IOException exception){
			return false;
		}
	}

	public boolean removeAttachment(Project project, int index) {
		try {
			String filelocation = project.getAttachments().get(index).getFileLocation();
			boolean deleted = new File(servletContext.getRealPath("/app/uploads/attachments/" + filelocation)).delete();
			if(!deleted){
				throw new IOException("Can not delete attachment.");
			}
			
			project.getAttachments().remove(index);
			this.save(project);
			
			return true;
		}catch(IOException exception){
			return false;
		}
	}
}