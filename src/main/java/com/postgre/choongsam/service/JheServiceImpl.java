package com.postgre.choongsam.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.JheDao;
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
		upAsmtStatus(lectureHomeworkList);
		return lectureHomeworkList;
	}

	@Override
	public List<Homework> getProfHomeworkList(String LCTR_ID) {
		System.out.println("강사 강의 리스트 서비스");
		List<Homework> profHomeworkList = hed.getProfHomeworkList(LCTR_ID);
		upAsmtStatus(profHomeworkList);
		return profHomeworkList;
	}

	private void upAsmtStatus(List<Homework> homeworkList) {
		LocalDate currentDate = LocalDate.now();

		for (Homework homework : homeworkList) {
			 LocalDate startDate = LocalDate.parse(homework.getSBMSN_BGNG_YMD(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			 LocalDate endDate = LocalDate.parse(homework.getSBMSN_END_YMD(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			if (currentDate.isBefore(startDate)) {
					homework.setAsmtStatus("미등록");
			} else if (currentDate.isAfter(endDate)) {
				homework.setAsmtStatus("종료");
			} else {
				homework.setAsmtStatus("진행중");
			}

			int submissionRate = getSubmissionRate(homework.getLCTR_ID(), homework.getASMT_NO());
			homework.setSubmissionRate(submissionRate);
			System.out.println("submissionRate: " + submissionRate);
		}
	}

	private int getSubmissionRate(String lctr_id, int ASMT_NO) {
		int totalStudents = hed.getTotStudInCourse(lctr_id);
		System.out.println("getSubmissionRate lctr_id: " + lctr_id);
		System.out.println("totalStudents: " + totalStudents);
		System.out.println("getSubmissionRate ASMT_NO: " + ASMT_NO);
		int submittedStudents = hed.getSubmittedStuds(ASMT_NO);
		return (totalStudents == 0) ? 0 : (submittedStudents * 100) / totalStudents;
	}

	@Override
	public Lecture findByLCTR(String LCTR_ID) {
		System.out.println("강의 아이디 찾기 서비스");
		Lecture findByLCTR = hed.findByLCTR(LCTR_ID);
		return findByLCTR;
	}

	@Override
	public int insertHomework(Homework homework) {
		System.out.println("과제 등록 서비스");
		int insHWList = hed.insertHomework(homework);
		return insHWList;
	}

	@Override
	public Homework findById(int ASMT_NO) {
		System.out.println("수정할 과제 불러오는 서비스");
		Homework findByASMT = hed.findById(ASMT_NO);
		return findByASMT;
	}

	@Override
	public int updateHomework(Homework homework) {
		System.out.println("과제 수정 서비스");
		int upHomeworkList = hed.updateHomework(homework);
		return upHomeworkList;
	}

	private void deleteHomework(int asmtNo) {
		System.out.println("과제 삭제 서비스");
		hed.deleteHomeworkSubmission(asmtNo);
		hed.deleteHomework(asmtNo);
	}

	@Override
	public void deleteHomeworkList(List<Integer> delCheck) {
		System.out.println("과제 삭제리스트 서비스");
		for (Integer asmtNo : delCheck) {
			hed.deleteHomeworkSubmission(asmtNo);
			deleteHomework(asmtNo);
		}
	}

	@Override
	public List<Homework> getStudHomeworkList(int USER_SEQ) {
		System.out.println("학생 강의 리스트 서비스");
		List<Homework> studHomeworkList = hed.getStudHomeworkList(USER_SEQ);
		upAsmtStatus(studHomeworkList);
		return studHomeworkList;
	}

	@Override
	public void notifyStudents(String lctr_ID, int asmt_no) {
		System.out.println("강사가 등록한 과제 학생에게 등록 서비스");
		List<Homework> studHomeworkList = hed.notifyStudents(lctr_ID);
		for (Homework homework : studHomeworkList) {
			int userSeq = homework.getUser_seq();
			int latestAsmtNo = homework.getASMT_NO();
			System.out.println("userSeq: " + userSeq);
			System.out.println("latestAsmtNo: " + latestAsmtNo);
			Homework_Submission submission = new Homework_Submission();
			submission.setUser_seq(userSeq);
			submission.setAsmt_no(latestAsmtNo);
			hed.insSubmission(submission);
		}
	}

	@Override
	public int updatesubmitHomework(Homework homework) {
		System.out.println("학생 과제 제출 수정 서비스");
		Homework_Submission homework_Submission = new Homework_Submission();
		homework_Submission.setUser_seq(10051);
		homework_Submission.setAsmt_no(homework.getASMT_NO());
		homework_Submission.setSbmsn_yn("Y");
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		homework_Submission.setSbmsn_ymd(today);
		homework_Submission.setFile_group(1);
		int upSubmitHomework = hed.updatesubmitHomework(homework_Submission);
		return upSubmitHomework;
	}
}
