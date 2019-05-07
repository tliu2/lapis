package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class UploadFileBean {

	private UploadedFile file;

	private Part uploadedFile;
	
	private String folder = ".\\";

	
	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public void saveFile() {

		try {
			InputStream input = uploadedFile.getInputStream();
			String fileName = uploadedFile.getSubmittedFileName();
			System.out.println(fileName);
			File uploadedFiles = new File(folder, "filedessu");
			Files.copy(input, uploadedFiles.toPath());
			
			
			System.out.println(uploadedFiles.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//----------------------
	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
