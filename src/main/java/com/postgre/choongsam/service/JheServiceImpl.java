package com.postgre.choongsam.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dao.JheDao;
import com.postgre.choongsam.dto.Attendance_Check;
import com.postgre.choongsam.dto.File_Group;
import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Homework_Submission;
import com.postgre.choongsam.dto.Lecture;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JheServiceImpl implements JheService {

	@Autowired
	private final JheDao hed;

	@Override
	public List<Homework> getLectureHomeworkList() {
		System.out.println("강사별 강의 리스트 서비스");
		List<Homework> lectureHomeworkList = hed.getLectureHomeworkList();
		return lectureHomeworkList;
	}

	@Override
	public List<Homework> getProfHomeworkList(String lctr_id) {
		System.out.println("강사 강의 리스트 서비스");
		List<Homework> profHomeworkList = hed.getProfHomeworkList(lctr_id);
		upAsmtStatus(profHomeworkList);
		return profHomeworkList;
	}

	private void upAsmtStatus(List<Homework> homeworkList) {
		LocalDate currentDate = LocalDate.now();

		for (Homework homework : homeworkList) {
			 LocalDate startDate = LocalDate.parse(homework.getSbmsn_bgng_ymd(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			 LocalDate endDate = LocalDate.parse(homework.getSbmsn_end_ymd(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			if (currentDate.isBefore(startDate)) {
					homework.setAsmtStatus("미등록");
			} else if (currentDate.isAfter(endDate)) {
				homework.setAsmtStatus("종료");
			} else {
				homework.setAsmtStatus("진행중");
			}

			int submissionRate = getSubmissionRate(homework.getLctr_id(), homework.getAsmt_no());
			homework.setSubmissionRate(submissionRate);
			System.out.println("submissionRate: " + submissionRate);
		}
	}

	private int getSubmissionRate(String lctr_id, int asmt_no) {
		int totalStudents = hed.getTotStudInCourse(lctr_id);
		System.out.println("getSubmissionRate lctr_id: " + lctr_id);
		System.out.println("totalStudents: " + totalStudents);
		System.out.println("getSubmissionRate asmt_no: " + asmt_no);
		int submittedStudents = hed.getSubmittedStuds(asmt_no);
		return (totalStudents == 0) ? 0 : (submittedStudents * 100) / totalStudents;
	}

	@Override
	public List<Homework> getStudSubmitList(String lctr_id) {
		List<Homework> studSubmitList = hed.getStudSubmitList(lctr_id);
		return studSubmitList;
	}

	@Override
	public Lecture findByLCTR(String lctr_id) {
		System.out.println("강의 아이디 찾기 서비스");
		Lecture findByLCTR = hed.findByLCTR(lctr_id);
		return findByLCTR;
	}

	@Override
	@Transactional
	public int insertHomework(Homework homework, MultipartFile file) {
		System.out.println("과제 등록 서비스");

		// 파일이 있다면 파일을 먼저 저장하고, 그 정보를 과제에 매핑
		if (file != null && !file.isEmpty()) {
			// 파일 저장 및 FILE_GROUP 얻기
			int fileGroup = saveFile(file);
			homework.setFile_group(fileGroup); // 과제에 파일 그룹 설정
		}

		// 과제 정보 저장
		int insHWList = hed.insertHomework(homework);
		return insHWList;
	}

	// 파일 저장 메서드
	private int saveFile(MultipartFile file) {
		System.out.println("파일 저장 서비스");

		// 파일 경로와 이름을 설정하여 파일을 저장
		String uploadDir = "C:\\spring\\springSrc17\\choongsam_repo\\src\\main\\webapp\\WEB-INF\\chFile\\homework"; // 파일 업로드 디렉토리
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		File dest = new File(uploadDir + File.separator + fileName);

		try {
			// 파일을 실제 경로에 저장
			file.transferTo(dest);

			// 파일 정보를 DB에 저장
			File_Group file_Group = new File_Group();
			file_Group.setFile_nm(fileName);
			file_Group.setFile_extn_nm(getFileExtension(fileName)); // 확장자 추출
			file_Group.setFile_sz((int) file.getSize());
			file_Group.setFile_path_nm(uploadDir + File.separator + fileName);

			// 파일 저장 후 FILE_GROUP 반환
			hed.insertFile(file_Group); // DAO에서 파일을 DB에 저장하는 SQL 문 실행
			return file_Group.getFile_group();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("파일 업로드 실패 ", e);
		}
	}

	// 파일 확장자 추출
	private String getFileExtension(String fileName) {
		int idx = fileName.lastIndexOf('.');
		if (idx > 0) {
			return fileName.substring(idx + 1);
		}
		return "";
	}

	@Override
	public Homework findById(int asmt_no) {
		System.out.println("수정할 과제 불러오는 서비스");
		Homework findByASMT = hed.findById(asmt_no);
		return findByASMT;
	}

	@Override
	@Transactional
	public int updateHomework(Homework homework , MultipartFile file) {
		System.out.println("과제 수정 서비스");

		// 파일이 있다면 새로운 파일을 저장하고, 그 정보를 과제에 매핑
		if (file != null && !file.isEmpty()) {
			// 파일 저장 및 FILE_GROUP 얻기
			int fileGroup = saveFile(file);  // 서비스 계층에서 파일 저장
			homework.setFile_group(fileGroup); // 과제에 파일 그룹 설정
		}

		int upHomeworkList = hed.updateHomework(homework);
		return upHomeworkList;
	}

	private void deleteHomework(int asmt_no) {
		System.out.println("과제 삭제 서비스");
		hed.deleteHomeworkSubmission(asmt_no);
		hed.deleteHomework(asmt_no);
	}

	@Override
	public void deleteHomeworkList(List<Integer> delCheck) {
		System.out.println("과제 삭제리스트 서비스");
		for (Integer asmt_no : delCheck) {
			hed.deleteHomeworkSubmission(asmt_no);
			deleteHomework(asmt_no);
		}
	}

	@Override
	public List<Homework> getStudHomeworkList(int user_seq) {
		System.out.println("학생 강의 리스트 서비스");
		List<Homework> studHomeworkList = hed.getStudHomeworkList(user_seq);
		upAsmtStatus(studHomeworkList);
		return studHomeworkList;
	}

	@Override
	public void notifyStudents(String lctr_id) {
		System.out.println("강사가 등록한 과제 학생에게 등록 서비스");
		List<Homework> studHomeworkList = hed.notifyStudents(lctr_id);
		for (Homework homework : studHomeworkList) {
			int userSeq = homework.getUser_seq();
			int latestAsmtNo = homework.getAsmt_no();
			System.out.println("userSeq: " + userSeq);
			System.out.println("latestAsmtNo: " + latestAsmtNo);
			Homework_Submission submission = new Homework_Submission();
			submission.setUser_seq(userSeq);
			submission.setAsmt_no(latestAsmtNo);
			hed.insSubmission(submission);
		}
	}

	@Override
	public int updatesubmitHomework(Homework homework, int user_seq) {
		System.out.println("학생 과제 제출 수정 서비스");
		Homework_Submission homework_Submission = new Homework_Submission();
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		homework_Submission.setUser_seq(user_seq);
		homework_Submission.setAsmt_no(homework.getAsmt_no());
		homework_Submission.setSbmsn_yn("Y");
		homework_Submission.setSbmsn_ymd(today);
		homework_Submission.setFile_group(1);
		int upSubmitHomework = hed.updatesubmitHomework(homework_Submission);
		return upSubmitHomework;
	}

	@Override
	public List<Lecture> getProfLectureInfo(String lctr_id) {
		System.out.println("강의 메인보드 서비스");
		List<Lecture> profLectureInfo = hed.getProfLectureInfo(lctr_id);
		return profLectureInfo;
	}

	@Override
	public List<Attendance_Check> profAttMain(String lctr_id) {
		System.out.println("출석 메인보드 서비스");
		List<Attendance_Check> profAttMainList = hed.profAttMain(lctr_id);
		return profAttMainList;
	}

	@Override
	public List<Attendance_Check> getStudAtt(String lctr_id, int lctr_no) {
		System.out.println("차시별 수강생 출석 호출 서비스");
		List<Attendance_Check> getStudAttList = hed.getStudAtt(lctr_id, lctr_no);
		return getStudAttList;
	}

	@Override
	public List<Attendance_Check> getOnlineStudAtt(String lctr_id) {
		System.out.println("차시별 온라인 수강생 출석 호출 서비스");
		List<Attendance_Check> onlineStudAttList = hed.getOnlineStudAtt(lctr_id);

		for (Attendance_Check attendance : onlineStudAttList) {
			updateStudAtt(attendance.getLctr_id(), attendance.getLctr_no(),
						  Arrays.asList(attendance.getUser_seq()),
						  Map.of("att_status_" + attendance.getUser_seq(), String.valueOf(attendance.getAtt_status())),
						  7002);
		}
		return onlineStudAttList;
	}

	@Override
	public void updateStudAtt(String lctr_id, int lctr_no,
							  List<Integer> user_seq, Map<String, String> att_status, int onoff) {
		System.out.println("출석 insert 서비스");

		for (int i = 0; i < user_seq.size(); i++) {
			Integer userSeq = user_seq.get(i);
			String  attStatus = att_status.get("att_status_" + userSeq);

			if (attStatus != null) {
				int attStatusValue = Integer.parseInt(attStatus);
				Attendance_Check attendance_Check = new Attendance_Check();
				attendance_Check.setLctr_id(lctr_id);
				attendance_Check.setLctr_no(lctr_no);
				attendance_Check.setUser_seq(userSeq);
				attendance_Check.setAtt_status(attStatusValue);

				if (onoff == 7001) {
					hed.updateStudAtt(attendance_Check);
				} else if (onoff == 7002) {
					hed.upStudOnlineAtt(attendance_Check);
				}
			}
		}
	}
}
