package com.postgre.choongsam.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postgre.choongsam.dto.Attendance_Check;
import com.postgre.choongsam.dto.File_Group;
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
	public List<Lecture> getProfLectureInfo(String lctr_id) {
		System.out.println("강의 메인보드 다오");
		List<Lecture> profLectureInfo = null;
		try {
			profLectureInfo = session.selectList("profLectureInfo", lctr_id);
			System.out.println("강의 메인보드 profLectureInfo: " + profLectureInfo);
		} catch (Exception e) {
			System.out.println("getProfLectureInfo error: " + e.getMessage());
		}
		return profLectureInfo;
	}

	@Override
	public List<Homework> getProfHomeworkList(String lctr_id) {
		System.out.println("강사 강의별 과제 리스트 와따오");
		List<Homework> profHomeworkList = null;
		try {
			profHomeworkList = session.selectList("getProfHomeworkList", lctr_id);
			System.out.println("다오 LCTR_ID: " + lctr_id);
		} catch (Exception e) {
			System.out.println("getProfHomeworkList error: " + e.getMessage());
		}
		return profHomeworkList;
	}

	@Override
	public Lecture findByLCTR(String lctr_id) {
		System.out.println("강사 강의 아이디 가지러 와따오");
		Lecture findByLCTR = null;
		try {
			findByLCTR = session.selectOne("findByLCTR", lctr_id);
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
	public Homework findById(int asmt_no) {
		System.out.println("과제 수정 이따오");
		Homework findByASMT = null;
		try {
			findByASMT = session.selectOne("findById", asmt_no);
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
	public void deleteHomeworkSubmission(int asmt_no) {
		System.out.println("학생에게 준 과제 삭제하러 와따오");
		session.delete("delHomeworkSubmission", asmt_no);
	}

	@Override
	public void deleteHomework(int asmt_no) {
		System.out.println("과제삭제 와따오");
		session.delete("delHomework", asmt_no);
	}

	public void deleteHomeworkList(List<Integer> asmtNoList) {
		System.out.println("과제삭제 리스트여따오");
		for (Integer asmt_no : asmtNoList) {
			deleteHomeworkSubmission(asmt_no);
			deleteHomework(asmt_no);
		}
	}

	@Override
	public List<Homework> getStudHomeworkList(int user_seq) {
		System.out.println("학생 강의별 과제 리스트 와따오");
		List<Homework> studHomeworkList = null;
		try {
			studHomeworkList = session.selectList("getStudHomeworkList", user_seq);
		} catch (Exception e) {
			System.out.println("getStudHomeworkList error: " + e.getMessage());
		}
		return studHomeworkList;
	}

	@Override
	public List<Homework> notifyStudents(String lctr_id) {
		System.out.println("과제 줄 학생 찾으러 와따오");
		System.out.println("notifyStudents lctr_ID: " + lctr_id);
		return session.selectList("notifyStudents", lctr_id);
	}

	@Override
	public int insSubmission(Homework_Submission homework_Submission) {
		System.out.println("등록된 과제 학생에게 주러 와따오");
		System.out.println("insSubmission homework_Submission : " + homework_Submission);
		return session.insert("insSubmission", homework_Submission);
	}

	@Override
	@Transactional
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
	public int getSubmittedStuds(int asmt_no) {
		System.out.println("과제 제출한 학생 수 구하러 와따오");
		System.out.println("등록된 학생 ASMT_NO: " + asmt_no);
		int submittedStudents = 0;
		try {
			submittedStudents = session.selectOne("getSubmittedStuds",asmt_no);
			System.out.println("getSubmittedStuds ASMT_NO: " + asmt_no);
		} catch (Exception e) {
			System.out.println("getSubmittedStuds error: " + e.getMessage());
		}
		return submittedStudents;
	}

	@Override
	public void insertFile(File_Group file_Group) {
		session.insert("insertFile", file_Group);  // 파일 저장 SQL 실행
	}

	@Override
	public List<Homework> getStudSubmitList(String lctr_id) {
		List<Homework> studSubmitList = null;
		try {
			studSubmitList = session.selectList("getStudSubmitList", lctr_id);
		} catch (Exception e) {
			System.out.println("getStudList error: " + e.getMessage());
		}
		return studSubmitList;
	}

	@Override
	public List<Attendance_Check> profAttMain(String lctr_id) {
		System.out.println("출석 메인보드 와따오");
		List<Attendance_Check> profAttMainList = null;
		try {
			profAttMainList = session.selectList("profAttMain", lctr_id);
		} catch (Exception e) {
			System.out.println("profAttMain error: " + e.getMessage());
		}
		return profAttMainList;
	}

	@Override
	public List<Attendance_Check> getStudAtt(String lctr_id, int lctr_no) {
		System.out.println("오프라인 수강생 출석 부르러 와따오");
		List<Attendance_Check> getStudAttList = null;
		try {
			getStudAttList = session.selectList("getStudAtt", Map.of("LCTR_ID", lctr_id, "LCTR_NO", lctr_no));
			System.out.println("getStudAttList: " + getStudAttList);
		} catch (Exception e) {
			System.out.println("getStudAtt error: " + e.getMessage());
		}
		return getStudAttList;
	}

	@Override
	public List<Attendance_Check> getOnlineStudAtt(String lctr_id) {
		System.out.println("온라인 수강생 출석 부르러 와따오");
		System.out.println("lctr_id: " + lctr_id);
		List<Attendance_Check> onlineStudAttList = null;
		try {
			onlineStudAttList = session.selectList("getOnlineStudAtt", lctr_id);
			System.out.println("onlineStudAttList: " + onlineStudAttList);
		} catch (Exception e) {
			System.out.println("getOnlineStudAtt error: " + e.getMessage());
			System.out.println("onlineStudAttList: " + onlineStudAttList);
		}
		return onlineStudAttList;
	}

	@Override
	public int updateStudAtt(Attendance_Check attendance_Check) {
		System.out.println("오프라인 출석 넣으러 와따오");
		System.out.println("attendance_Check: " + attendance_Check);
		int upStudAtt = 0;
		try {
			upStudAtt = session.insert("updateStudAtt", attendance_Check);
		} catch (Exception e) {
			System.out.println("updateStudAtt error: " + e.getMessage());
		}
		return upStudAtt;
	}

	@Override
	public int upStudOnlineAtt(Attendance_Check attendance_Check) {
		System.out.println("온라인 출석 넣으러 와따오");
		System.out.println("attendance_Check: " + attendance_Check);
		int upStudOnAtt = 0;
		try {
			upStudOnAtt = session.insert("upStudOnlineAtt", attendance_Check);
			System.out.println("upStudOnAtt: " + upStudOnAtt);
		} catch (Exception e) {
			System.out.println("upStudOnlineAtt error: " + e.getMessage());
		}
		return upStudOnAtt;
	}
}
