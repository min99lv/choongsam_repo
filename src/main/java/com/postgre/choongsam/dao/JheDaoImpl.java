package com.postgre.choongsam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Homework_Submission;
import com.postgre.choongsam.dto.Lecture;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JheDaoImpl implements JheDao {

	@Autowired
	private final SqlSession session;

	@Override
	public List<Homework> getLectureHomeworkList() {
		System.out.println("강사별 강의 과제 리스트 와따오");
		List<Homework> lectureHomeworkList = null;
		try {
			lectureHomeworkList = session.selectList("getLectureHomeworkList");
		} catch (Exception e) {
			System.out.println("getLectureHomeworkList error: " + e.getMessage());
		}
		return lectureHomeworkList;
	}

	@Override
	public List<Homework> getProfHomeworkList(String LCTR_ID) {
		System.out.println("강사 강의별 과제 리스트 와따오");
		List<Homework> profHomeworkList = null;
		try {
			profHomeworkList = session.selectList("getProfHomeworkList", LCTR_ID);
			System.out.println("다오 LCTR_ID: " + LCTR_ID);
		} catch (Exception e) {
			System.out.println("getProfHomeworkList error: " + e.getMessage());
		}
		return profHomeworkList;
	}

	@Override
	public Lecture findByLCTR(String LCTR_ID) {
		System.out.println("강사 강의 아이디 가지러 와따오");
		Lecture findByLCTR = null;
		try {
			findByLCTR = session.selectOne("findByLCTR", LCTR_ID);
			System.out.println("findByLCTR: " + findByLCTR);
		} catch (Exception e) {
			System.out.println("findByLCTR error: " + e.getMessage());
		}
		return findByLCTR;
	}

	@Override
	@Transactional
	public int insertHomework(Homework homework) {
		System.out.println("과제등록 와따오");
		int insHWList = 0;
		try {
			insHWList = session.insert("insertHomework", homework);
			System.out.println("insHWList: " + insHWList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertHomework error: " + e.getMessage());
			System.out.println("insHWList: " + insHWList);
			
		}
		return insHWList;
	}

	@Override
	public Homework findById(int ASMT_NO) {
		System.out.println("과제 수정 이따오");
		Homework findByASMT = null;
		try {
			findByASMT = session.selectOne("findById", ASMT_NO);
		} catch (Exception e) {
			System.out.println("findById error: " + e.getMessage());
		}
		return findByASMT;
	}

	@Override
	public int updateHomework(Homework homework) {
		System.out.println("과제 수정 해따오");
		int upHomeworkList = 0;
		try {
			upHomeworkList = session.update("updateHomework", homework);
		} catch (Exception e) {
			System.out.println("updateHomework error: " + e.getMessage());
		}
		return upHomeworkList;
	}

	@Override
	public void deleteHomeworkSubmission(int asmtNo) {
		System.out.println("학생에게 준 과제 삭제하러 와따오");
		session.delete("delHomeworkSubmission", asmtNo);
	}

	@Override
	public void deleteHomework(int asmtNo) {
		System.out.println("과제삭제 와따오");
		session.delete("delHomework", asmtNo);
	}

	public void deleteHomeworkList(List<Integer> asmtNoList) {
		System.out.println("과제삭제 리스트여따오");
		for (Integer asmtNo : asmtNoList) {
			deleteHomeworkSubmission(asmtNo);
			deleteHomework(asmtNo);
		}
	}

	@Override
	public List<Homework> getStudHomeworkList(int USER_SEQ) {
		System.out.println("학생 강의별 과제 리스트 와따오");
		List<Homework> studHomeworkList = null;
		try {
			studHomeworkList = session.selectList("getStudHomeworkList", USER_SEQ);
		} catch (Exception e) {
			System.out.println("getStudHomeworkList error: " + e.getMessage());
		}
		return studHomeworkList;
	}

	@Override
	public List<Homework> notifyStudents(String lctr_ID) {
		System.out.println("과제 줄 학생 찾으러 와따오");
		System.out.println("notifyStudents lctr_ID: " + lctr_ID);
		return session.selectList("notifyStudents", lctr_ID);
	}

	@Override
	public int insSubmission(Homework_Submission homework_Submission) {
		System.out.println("등록된 과제 학생에게 주러 와따오");
		System.out.println("insSubmission homework_Submission : " + homework_Submission);
		return session.insert("insSubmission", homework_Submission);
	}

	@Override
	public int updatesubmitHomework(Homework_Submission homework_Submission) {
		System.out.println("과제 제출 수정하러 와따오");
		System.out.println("updatesubmitHomework submission : " + homework_Submission);
		int upsubmitHomework = 0;
		try {
			upsubmitHomework = session.update("updatesubmitHomework", homework_Submission);
			System.out.println("updatesubmitHomework success : " + homework_Submission);
		} catch (Exception e) {
			System.out.println("updatesubmitHomework error: " + e.getMessage());
			System.out.println("updatesubmitHomework error : " + homework_Submission);
			e.printStackTrace();
			if (e.getCause() != null) {
	            System.out.println("Cause: " + e.getCause());
	        }
		}
		return upsubmitHomework;
	}

	@Override
	public int getTotStudInCourse(String lctr_id) {
		System.out.println("강의에 등록된 학생 수 구하러 와따오");
		System.out.println("등록된 학생 lctr_id: " + lctr_id);
		int totalStudents = 0;
		try {
			totalStudents = session.selectOne("getTotStudInCourse",lctr_id);
			System.out.println("getTotStudInCourse lctr_id: " + lctr_id);
		} catch (Exception e) {
			System.out.println("getTotStudInCourse error: " + e.getMessage());
		}
		return totalStudents;
	}

	@Override
	public int getSubmittedStuds(int ASMT_NO) {
		System.out.println("과제 제출한 학생 수 구하러 와따오");
		System.out.println("등록된 학생 ASMT_NO: " + ASMT_NO);
		int submittedStudents = 0;
		try {
			submittedStudents = session.selectOne("getSubmittedStuds",ASMT_NO);
			System.out.println("getSubmittedStuds ASMT_NO: " + ASMT_NO);
		} catch (Exception e) {
			System.out.println("getSubmittedStuds error: " + e.getMessage());
		}
		return submittedStudents;
	}
}
