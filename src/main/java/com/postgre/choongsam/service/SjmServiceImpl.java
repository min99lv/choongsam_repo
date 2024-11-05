package com.postgre.choongsam.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dao.SjmDao;
import com.postgre.choongsam.dto.Filegroup;
import com.postgre.choongsam.dto.Lecture;
import com.postgre.choongsam.dto.Note;
import com.postgre.choongsam.dto.Notice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SjmServiceImpl implements SjmService {
	private final SjmDao sd;

	// NOTE - 공지사항 목록
	@Override
	public List<Notice> selectNoticeList(Map<String, Object> params) {

		List<Notice> noticeList = sd.selectNoticeList(params);

		return noticeList;
	}

	// NOTE - 공지사항 갯수 (페이징)
	@Override
	public int countNotice(String keyword) {

		int total = sd.countNotice(keyword);

		return total;
	}

	@Override
	public Notice noticeDetail(int ntc_mttr_sn) {

		System.out.println("SjmServiceImpl.noticeDetail() start.....");

		Notice notice = sd.noticeDetail(ntc_mttr_sn);

		return notice;
	}





	@Override
	@Transactional
	public int noticeCreate(Notice notice,MultipartFile[] files, HttpServletRequest request) {
	   
		List<Filegroup> uploadFiles = new ArrayList<>();

	    // 파일 리스트를 순회하며 업로드
	    for (MultipartFile file : files) {
	        if (file != null && !file.isEmpty()) {
	            try {
	                Filegroup uploadFile = uploadFile(file, request);
	                if (uploadFile != null) {
	                    uploadFiles.add(uploadFile);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	                return -1;
	            }
	        }
	    }

	    // 파일 정보를 공지사항과 함께 저장
	    int result = sd.noticeCreate(notice, uploadFiles);
	    return result;
	}

	// 업로드된 파일을 처리하는 메서드
	public Filegroup uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
	    // 파일 정보 변수 선언
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String idntf_no = UUID.randomUUID().toString() + "_" + timestamp;
	    String originFile = file.getOriginalFilename();
	    String file_nm = ""; // 실제 파일명
	    String file_extn_nm = ""; // 확장자
	    int file_sz = 0; // 파일 크기

	    // 파일이 있는 경우 처리
	    if (originFile != null && !originFile.isEmpty()) {
	        int lastIndex = originFile.lastIndexOf(".");
	        if (lastIndex != -1) {
	            file_nm = originFile.substring(0, lastIndex);
	            file_extn_nm = originFile.substring(lastIndex + 1);
	            file_sz = (int) file.getSize();

	            System.out.println("originFile >> " + file_nm);
	            System.out.println("fileSuffix >> " + file_extn_nm);
	            System.out.println("fileSize >> " + file_sz + "바이트");

	            InputStream inputStream = file.getInputStream();
	            String uploadPath = request.getSession().getServletContext().getRealPath("/WEB-INF/chFile/notice");

	            // 파일 저장
	            File targetFile = new File(uploadPath, idntf_no + "." + file_extn_nm);
	            try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {
	                byte[] buffer = new byte[1024];
	                int bytesRead;
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);
	                }
	            }

	            // 파일 객체 생성 및 반환
	            Filegroup uploadFile = new Filegroup(); // Filegroup 클래스 생성
	            uploadFile.setIdntf_no(idntf_no);
	            uploadFile.setFile_nm(file_nm);
	            uploadFile.setFile_extn_nm(file_extn_nm);
	            uploadFile.setFile_sz(file_sz);
	            uploadFile.setFile_path_nm(targetFile.getPath());

	            return uploadFile; // 업로드된 파일 정보를 반환
	        } else {
	            System.out.println("파일에 확장자가 없습니다.");
	        }
	    } else {
	        System.out.println("업로드된 파일이 없습니다.");
	    }
	    return null; // 파일이 없는 경우 null 반환
	}

	@Override
	public int getUserSeq(Map<String, Object> params) {
		
		int user_seq = 0;
		
		return user_seq = sd.getUserSeq(params);
	}
	
	@Override
	public List<Note> noteList(Map<String, Object> params) {
		List<Note> noteList = sd.noteList(params);
		return noteList;
	}


	@Override
	public int countNote(Map<String, Object> params) {

		int total = sd.countNote(params);

		return total;
	}

	@Override
	public List<Note> getSentNotes(Map<String, Object> params) {
		List<Note> noteList = sd.getSentNotes(params);
		return noteList;
	}

	
	// NOTE - 쪽지 상세 
	@Override
	public Note getNote(int note_sn) {
		Note note = sd.getNote(note_sn);
		
		return note;
	}

	@Override
	public int createNote(Note note) {
		
		int result = sd.createNote(note);
		return result;
	}

	@Override
	public List<Lecture> getMyLectures(int user_seq) {
		List<Lecture> lectures = sd.getMyLectures(user_seq);
		return lectures;
	}

	@Override

	public List<Lecture> getSameLeceture(int lectureId) {
		List<Lecture> lectures = sd.getSameLeceture(lectureId);
		return lectures;
	}



}
