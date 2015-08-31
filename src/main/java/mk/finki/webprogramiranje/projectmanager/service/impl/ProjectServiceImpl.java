package mk.finki.webprogramiranje.projectmanager.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

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
				
				return deleted;
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
}