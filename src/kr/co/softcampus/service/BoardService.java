package kr.co.softcampus.service;

import javax.annotation.Resource;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softcampus.beans.ContentBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name="loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	private String saveUploadFile(MultipartFile upload_file) {
		String file_name=System.currentTimeMillis()+"_"+upload_file.getOriginalFilename();
		try {
			upload_file.transferTo(new File(path_upload+"/"+file_name));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	
	public void addContentInfo(ContentBean writeContentBean) {
		//System.out.println(writeContentBean.getContent_subject());
		//System.out.println(writeContentBean.getContent_text());
		
		//자바프로젝트와 똑같이 하면 오류가 발생한다
		//자바로 만들면 자바에서는 알아서 자동으로 처리해 주지면 xml은 servlet-context.xml, web.xml등에서 우리가 설정해줘야한다
		//요청 정보가 넘어오면 우리가  MultipartResolver Filter를 설정해줘야지 정상작동한다.
		//System.out.println(writeContentBean.getUpload_file().getSize());
		
	MultipartFile upload_file=writeContentBean.getUpload_file();
		
		if(upload_file.getSize()>0) {
			String file_name=saveUploadFile(upload_file);
			writeContentBean.setContent_file(file_name);
		}
		
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		boardDao.addContentInfo(writeContentBean);
	}
}