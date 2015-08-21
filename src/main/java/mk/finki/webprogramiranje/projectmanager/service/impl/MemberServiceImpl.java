package mk.finki.webprogramiranje.projectmanager.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import mk.finki.webprogramiranje.projectmanager.model.Member;
import mk.finki.webprogramiranje.projectmanager.repository.MemberRepository;
import mk.finki.webprogramiranje.projectmanager.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private ServletContext servletContext;

	public Member save(Member member) {
		return repository.save(member);
	}
	
	public boolean savePicture(Member member, MultipartFile picture) {
		try {
			BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(picture.getBytes()));
			BufferedImage resizedImage = ServiceUtilities.resizeBufferedImage(originalImage);
			
			String oldPicture = member.getPicture();
			if(!oldPicture.isEmpty()){
				boolean deleted = new File(servletContext.getRealPath("/app/uploads/pictures/" + oldPicture + ".png")).delete();
				if(!deleted){
					throw new IOException("Can not delete previous member picture.");
				}
			}
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(resizedImage, "png", outputStream);
			outputStream.flush();
			
			String filename = ServiceUtilities.getMD5(outputStream.toByteArray());
			outputStream.close();
			
			File file = new File(servletContext.getRealPath("/app/uploads/pictures/" + filename + ".png"));
			ImageIO.write(resizedImage, "png", file);
			
			member.setPicture(filename);
			this.save(member);
			
			return true;
		}catch(IOException exception){
			//should be logged in a production environment
			return false;
		}
	}

	public Iterable<Member> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
	}
	
	public Iterable<Member> findAll(Iterable<String> ids) {
		return repository.findAll(ids);
	}

	public Member findById(String id) {
		return repository.findOne(id);
	}
	
	public Member findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public void delete(String id) {
		repository.delete(id);
	}

	public void delete(Member member) {
		repository.delete(member);
	}

	public List<Member> searchByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.searchByEmail(email);
	}
}