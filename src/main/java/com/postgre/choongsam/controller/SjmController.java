package com.postgre.choongsam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Lecture;
import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.dto.Note;
import com.postgre.choongsam.dto.Notice;
import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.service.SjmService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SjmController {
	private final SjmService ss;

	// 공지사항 ------------------------------------------------------------------

	// NOTE - 공지사항 목록
	@GetMapping(value = "/api/notice")
	public String notice(Model model, @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
			@RequestParam(value = "keyword", required = false) String keyword) {

		log.info("noticeController start...");
		int total = ss.countNotice(keyword);
		Paging page = new Paging(total, currentPage);
		// Map 객체 생성하여 파라미터 설정
		Map<String, Object> params = new HashMap<>();
		params.put("start", page.getStart());
		params.put("rowPage", page.getRowPage());
		params.put("keyword", keyword);

		System.out.println("page---->" + page);

		List<Notice> noticeList = ss.selectNoticeList(params);

		model.addAttribute("page", page);
		model.addAttribute("total", total);
		model.addAttribute("noticeList", noticeList);

		return "view_Sjm/noticeList";

	}

	// NOTE - 공지사항 작성화면
	@GetMapping(value = "/api/notice/new")
	public String noticeCreateForm() {

		System.out.println("SjmController.noticeCreate start .....");

		return "view_Sjm/noticeCreate";
	}

	// NOTE - 공지사항 작성
	@PostMapping(value = "/api/notice/new")
	public ResponseEntity<Integer> noticeCreate(Notice notice, @RequestParam("files") MultipartFile[] files,
			HttpServletRequest request) {
		System.out.println("작성 시작");
		System.out.println("받은 파일 수: " + files.length); // 파일 수 출력

		// TODO : 1. 1102 공지사항 insert 2. 파일 넣기
		System.out.println("notice-->" + notice);
		int result = ss.noticeCreate(notice, files, request);

		return ResponseEntity.ok(result);
	}

	// NOTE - 공지사항 상세 화면
	@GetMapping(value = "/view_Sjm/noticeDetail")
	public String noticeDetailForm(@RequestParam("ntc_mttr_sn") int ntc_mttr_sn, Model model) {
		model.addAttribute("ntc_mttr_sn", ntc_mttr_sn);
		return "view_Sjm/noticeDetail"; // view_Sjm/noticeDetail.jsp로 이동
	}

	// NOTE - 공지사항 상세
	@GetMapping(value = "/api/notice/{ntc_mttr_sn}")
	@ResponseBody
	public Notice noticeDetail(@PathVariable("ntc_mttr_sn") int ntc_mttr_sn) {

		System.out.println("SjmController.noticeDetailForm() start...");

		Notice notice = ss.noticeDetail(ntc_mttr_sn);

		return notice;
	}

	// 쪽지 ------------------------------------------------------------------

	// 쪽지 목록 조회 화면
	@GetMapping(value = "/notes")
	public String noteBoxForm(HttpSession session) {
		System.out.println("쪽지함 화면");

		System.out.println("쪽지함 화면");

		// 세션에서 user_seq 가져오기 (null 여부 확인)
		Integer user_seq = (Integer) session.getAttribute("user_seq");

		if (user_seq == null || user_seq == 0) {
			System.out.println("로그인 하시오");
			return "redirect:/view_Ljm/loginForm";
		}

		System.out.println(user_seq);
		return "view_Sjm/noteList";
	}

	// NOTE - 안읽은 쪽지 목록
	@GetMapping(value = "/api/notes/unread")
	@ResponseBody
	public List<Note> rcvrnoteList(HttpSession session) {

		 System.out.println("text is of type: " + session.getAttribute("user_seq").getClass().getSimpleName());
		Map<String, Object> params = new HashMap<>();

		params.put("user_id", session.getAttribute("user"));
		params.put("user_status", session.getAttribute("usertype"));

		int user_seq = ss.getUserSeq(params);

		System.out.println("user.getUser_seq() " + user_seq);

		params.put("user_seq", user_seq);

		List<Note> noteList = ss.noteList(params);

		return noteList;
	}

	// NOTE - 보낸 쪽지 목록
	@GetMapping(value = "/api/notes/sent")
	@ResponseBody
	public List<Note> noteList(HttpSession session) {

		Map<String, Object> params = new HashMap<>();

		params.put("user_id", session.getAttribute("user"));
		params.put("user_status", session.getAttribute("usertype"));

		int user_seq = ss.getUserSeq(params);

		System.out.println("user.getUser_seq() " + user_seq);

		params.put("user_seq", user_seq);

		List<Note> noteList = ss.getSentNotes(params);

		return noteList;
	}

	// 쪽지 상세 화면
	@GetMapping(value = "/note/{note_sn}")
	public String noteDetailForm(@PathVariable("note_sn") int note_sn, Model model) {
		System.out.println("쪽지 디테일 화면");
		model.addAttribute("note_sn", note_sn);
		return "view_Sjm/noteDetail"; // view_Sjm/noticeDetail.jsp로 이동
	}

	// NOTE - 쪽지 상세정보 조회
	@GetMapping(value = "/api/notes/{note_sn}")
	@ResponseBody
	public Note getNote(@PathVariable("note_sn") int note_sn) {

		System.out.println("상세 쪽지 시작");

		Note note = ss.getNote(note_sn);
		return note;
	}

	//  쪽지 전송 화면
	@GetMapping(value = "/notes/new")
	public String CreateNoteForm() {
		System.out.println("쪽지전송화면");

		return "view_Sjm/noteCreate";
	}

	// NOTE - 쪽지 전송
	@PostMapping(value = "/api/notes")
	@ResponseBody
	public int createNote(Note note, HttpSession session) {

		System.out.println("Note" + note);

		// note.setRcvr_seq( (int) session.getAttribute("user_seq"));

		int result = ss.createNote(note);

		return result;

	}
	
	// NOTE - 내가 듣는 강의 목록
	@GetMapping(value="/api/lectures/my")
	@ResponseBody
	public ResponseEntity<List<Lecture>> getMyLectures(@RequestParam("user_seq") Integer user_seq) {
       System.out.println("내가 듣는 강의 목록 불러오기 ");
	
		List<Lecture> lectures = ss.getMyLectures(user_seq);
        return ResponseEntity.ok(lectures);
    }
	
	// NOTE - 같은 강의를 듣는 사람들 + 강사 목록
	@GetMapping(value="/api/lectures/{lectureId}/recipients") // 경로 변수로 lectureId를 받습니다.
	@ResponseBody
	public ResponseEntity<List<Lecture>> getMyLectures(@PathVariable("lectureId") int lectureId) {
	    System.out.println("내가 듣는 강의 목록 불러오기 for lectureId: " + lectureId);
	    
	    List<Lecture> lectures = ss.getSameLeceture(lectureId);
	    return ResponseEntity.ok(lectures);
	}
	
}
