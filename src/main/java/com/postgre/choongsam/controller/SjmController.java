package com.postgre.choongsam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Lecture;
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

	// ##################
	// ##################
	// ##################
	// ##################
	// 쪽지 ------------------------------------------------------------------
	// ##################
	// ##################
	// ##################
	// ##################

	// 받은 쪽지 화면
	@GetMapping(value = "/notes/received")
	public String ShowNoteReceived(HttpSession session) {
		System.out.println("SjmController.ShowNoteSend() start.....");
		System.out.println("SjmController.ShowNoteSend() 받은 쪽지 화면 ---> " + session.getAttribute("user_seq"));

		return "view_Sjm/noteReceived";
	}

	// 보낸 쪽지 화면
	@GetMapping(value = "/notes/sent")
	public String ShowNoteSend(HttpSession session) {
		System.out.println("SjmController.ShowNoteSend() start.....");
		System.out.println("SjmController.ShowNoteSend() 보낸 쪽지 화면 ---> " + session.getAttribute("user_seq"));

		return "view_Sjm/noteSent";
	}

	// NOTE - 받은 쪽지 목록
	@GetMapping(value = "/api/notes/received")
	@ResponseBody
	public List<Note> getNotesReceived(HttpSession session) {

		System.out.println("SjmController.rcvrnoteList() ");
		// System.out.println("text is of type: " +
		// session.getAttribute("user_seq").getClass().getSimpleName());

		Map<String, Object> params = new HashMap<>();
		params.put("user_seq", 10001);

		List<Note> noteList = ss.getNotesReceived(params);

		return noteList;
	}

	// NOTE - 보낸 쪽지 목록
	@GetMapping(value = "/api/notes/sent")
	@ResponseBody
	public List<Note> getNotesSend(HttpSession session) {

		Map<String, Object> params = new HashMap<>();

		params.put("user_seq", 10001);

		List<Note> noteList = ss.getNotesSend(params);

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

	// 쪽지 전송 화면
	@GetMapping(value = "/notes/new")
	public String CreateNoteForm() {
		System.out.println("쪽지전송화면");

		return "view_Sjm/noteCreate";
	}

	// NOTE - 쪽지 전송
	@PostMapping(value = "/api/notes")
	@ResponseBody
	public int createNote(Note note, HttpSession session) {
		System.out.println(note);
		
		
	    // rcvr_seq가 int 타입이지만, 이를 String으로 변환 후 ','로 나누어 배열로 처리
	    String[] receiverSeqs = Integer.toString(note.getRcvr_seq()).split(",");

	    // 각 수신자에 대해 쪽지를 삽입
	    for (String receiverSeq : receiverSeqs) {
	        int receiverSeqInt = Integer.parseInt(receiverSeq);  // String을 int로 변환
	        note.setRcvr_seq(receiverSeqInt);  // 수신자 설정
	        int result = ss.createNote(note);  // 쪽지 생성
	        if (result == 0) {
	            return 0; // 실패 시 종료
	        }
	    }

	    return 1; // 성공 시
	}

	// NOTE - 내가 듣는 강의 목록
	@GetMapping(value = "/api/lectures/my")
	@ResponseBody
	public ResponseEntity<?> getMyLectures(@RequestParam(value = "user_seq", required = false) Integer userSeq) {
		if (userSeq == null) {
			return ResponseEntity.badRequest().body("user_seq is required");
		}
		System.out.println("내가 듣는 강의 목록 불러오기 ");
		System.out.println("SjmController.getMyLectures() user_seq");
		List<Lecture> lectures = ss.getMyLectures(userSeq);
		return ResponseEntity.ok(lectures);
	}


	@GetMapping(value = "/api/lectures/recipients")
	@ResponseBody
	public ResponseEntity<?> getMyLectures(@RequestParam("lctr_id") String lctr_id) {
		System.out.println("내가 듣는 강의 목록 불러오기 같은 강의를 듣는 사람들->" + lctr_id);

		List<Note> note = ss.getSameLeceture(lctr_id);
		return ResponseEntity.ok(note);
	}

}
