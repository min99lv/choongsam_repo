package com.postgre.choongsam.controller;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Ask;
import com.postgre.choongsam.dto.File_Group;
import com.postgre.choongsam.dto.Lecture;
import com.postgre.choongsam.dto.Note;
import com.postgre.choongsam.dto.Notice;
import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.service.SjmService;

import org.springframework.core.io.Resource;

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

		model.addAttribute("keyword", keyword);
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

	// NOTE - 파일 리스트 가져오기
	@GetMapping(value = "/api/files/{file_group}")
	@ResponseBody
	public List<File_Group> getFilesByGroup(@PathVariable int file_group) {
		// 파일 그룹 ID에 해당하는 파일 리스트를 가져옴
		System.out.println("파일 리스트 가져오기");
		List<File_Group> files = ss.getFilesByGroup(file_group);
		System.out.println("file--->" + files);
		return files;
	}

	@GetMapping(value = "/api/files/{fileGroup}/{fileSeq}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileGroup") int fileGroup,
			@PathVariable("fileSeq") int fileSeq, HttpServletRequest request) { // HttpServletRequest 추가
		System.out.println("다운로드 시작");

		try {
			// 파일 정보를 조회
			File_Group file = ss.getFile(fileGroup, fileSeq);

			// 디버깅: file.getFile_path_nm()과 file.getFile_nm() 출력
			System.out.println("File path: " + file.getFile_path_nm());
			System.out.println("File name: " + file.getFile_nm());

			// 파일 경로 디코딩 및 설정
			String filePathStr = URLDecoder.decode(file.getFile_path_nm(), "UTF-8");

			// 파일명과 확장자 결합
			String fullFileName = file.getFile_nm() + "." + file.getFile_extn_nm(); // 파일명과 확장자를 결합

			// 경로를 Path 객체로 변환 (경로 구분자 처리)
			Path filePath = Paths.get(request.getSession().getServletContext().getRealPath(filePathStr)).normalize();

			// 디버깅: 최종 경로 출력
			System.out.println("File path (final): " + filePath);

			// 파일이 존재하는지 확인
			if (!Files.exists(filePath)) {
				System.out.println("파일을 찾을 수 없습니다: " + filePath); // 디버깅 메시지 추가
				return ResponseEntity.notFound().build();
			}

			// 파일 리소스 생성
			Resource resource = new UrlResource(filePath.toUri());

			// 파일명을 UTF-8로 인코딩하여 Content-Disposition 헤더에 사용
			String encodedFileName = URLEncoder.encode(fullFileName, "UTF-8").replaceAll("\\+", "%20") // + 기호를 공백으로 변경
					.replaceAll("%28", "(").replaceAll("%29", ")");

			// 디버깅: 리소스 생성 확인
			System.out.println("Resource created: " + resource.getFilename());

			// 파일 다운로드를 위한 HTTP 응답 생성
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
					.body(resource);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
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
	public List<Note> getNotesReceived(Model model,
			@RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
			@RequestParam(value = "keyword", required = false) String keyword, HttpSession session) {

		System.out.println("SjmController.rcvrnoteList() ");
		// System.out.println("text is of type: " +
		// session.getAttribute("user_seq").getClass().getSimpleName());

		Map<String, Object> params = new HashMap<>();
		params.put("user_seq", session.getAttribute("user_seq"));
		
		
		int total = ss.getNoteRcvrTotal(params);

		Paging page = new Paging(total, currentPage);
		// Map 객체 생성하여 파라미터 설정

		params.put("start", page.getStart());
		params.put("rowPage", page.getRowPage());
		params.put("keyword", keyword);
		List<Note> noteList = ss.getNotesReceived(params);

		return noteList;
	}

	// NOTE - 보낸 쪽지 목록
	@GetMapping(value = "/api/notes/sent")
	@ResponseBody
	public Map<String, Object> getNotesSend(Model model,
			@RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
			@RequestParam(value = "keyword", required = false) String keyword, HttpSession session) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("user_seq", session.getAttribute("user_seq"));
		
		int total = ss.getNoteSendTotal(params);


		Paging page = new Paging(total, currentPage);
		// Map 객체 생성하여 파라미터 설정

		params.put("start", page.getStart());
		params.put("rowPage", page.getRowPage());
		params.put("keyword", keyword);
		List<Note> noteList = ss.getNotesSend(params);

		Map<String, Object> response = new HashMap<>();
		response.put("total", total); // 전체 데이터의 수
		response.put("notes", noteList); // 쪽지 목록
		response.put("paging", page); // 페이징 정보 (현재 페이지, 전체 페이지 등)

		return response;
	}

	// 쪽지 상세 화면
	@GetMapping(value = "/note/{note_sn}")
	public String noteDetailForm(@PathVariable("note_sn") int note_sn, Model model, HttpSession session) {
		System.out.println("쪽지 디테일 화면");
		
		Note note = ss.getNote(note_sn);
		
		int userSeq = (int) session.getAttribute("user_seq");
		
		// 수신자일 경우 수신 일시 업데이트
	    if (note.getRcvr_seq() == userSeq && note.getRcptn_dt() == null) {
	        int result = ss.updateReceiveDate(note_sn);  // 쪽지 수신 일시 업데이트
	    }
		
		
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
	public String CreateNoteForm(@RequestParam(value = "note_sn", required = false) Integer note_sn,
			@RequestParam(value = "sndpty_seq", required = false) Integer sndpty_seq,
			@RequestParam(value = "sender_name", required = false) String sender_name, Model model) {

		System.out.println("쪽지전송화면");

		// note_sn이 존재하면 모델에 추가
		if (note_sn != null && sndpty_seq != null && sender_name != null) {
			model.addAttribute("bfr_note_sn", note_sn);
			model.addAttribute("rcvr_seq", sndpty_seq);
			model.addAttribute("receiver_name", sender_name);
		}

		return "view_Sjm/noteCreate";
	}

	@RequestMapping(value = "/api/notes", method = RequestMethod.POST)
	@ResponseBody
	public int createNote(@RequestBody Map<String, Object> noteData, HttpSession session) {
		System.out.println(noteData);

		// 수신자 배열 받아오기
		Object rcvrSeqObj = noteData.get("rcvr_seq");

		List<Integer> receiverSeqs = null;

		// rcvr_seq가 배열 형태로 넘어온다면 List<Integer>로 변환
		if (rcvrSeqObj instanceof List) {
			receiverSeqs = (List<Integer>) rcvrSeqObj; // rcvr_seq를 List<Integer>로 변환
		} else if (rcvrSeqObj instanceof String) {
			// 만약 String 형태로 넘어온다면, ','로 구분된 숫자들이 있을 수 있음
			String[] seqArray = ((String) rcvrSeqObj).split(",");
			receiverSeqs = new ArrayList<>();
			for (String seq : seqArray) {
				try {
					receiverSeqs.add(Integer.parseInt(seq.trim())); // 각 값을 Integer로 변환 후 추가
				} catch (NumberFormatException e) {
					// 만약 변환 실패하면 예외 처리
					continue;
				}
			}
		}

		// 수신자가 비어있다면 실패 처리
		if (receiverSeqs == null || receiverSeqs.isEmpty()) {
			return 0;
		}

		Note note = new Note();
		note.setNote_ttl((String) noteData.get("note_ttl"));
		note.setNote_cn((String) noteData.get("note_cn"));
		note.setSndpty_note_yn((String) noteData.get("sndpty_note_yn"));
		note.setRcvr_note_yn((String) noteData.get("rcvr_note_yn"));

		// bfr_note_sn이 null일 수 있으므로 기본값 0 처리
		Object bfrNoteSnObj = noteData.get("bfr_note_sn");
		int bfrNoteSn = (bfrNoteSnObj != null) ? Integer.parseInt(bfrNoteSnObj.toString()) : 0; // Integer로 안전하게 변환
		note.setBfr_note_sn(bfrNoteSn);

		// sndpty_seq는 String에서 Integer로 변환
		try {
			Object sndptySeqObj = noteData.get("sndpty_seq");
			if (sndptySeqObj != null) {
				note.setSndpty_seq(Integer.parseInt(sndptySeqObj.toString())); // String -> Integer 변환
			} else {
				note.setSndpty_seq(0); // 기본값 0 설정
			}
		} catch (NumberFormatException e) {
			// 변환 실패 시 기본값 0 설정
			note.setSndpty_seq(0);
		}

		// 각 수신자에 대해 쪽지 삽입
		for (int receiverSeq : receiverSeqs) {
			note.setRcvr_seq(receiverSeq);
			// 직접 쪽지 생성하는 로직
			int result = ss.createNote(note);
			if (result == 0) {
				return 0; // 실패 시 종료
			}
		}

		return 1; // 성공 시
	}

	// NOTE - 내가 듣는 강의 목록
	@GetMapping(value = "/api/lectures/my")
	@ResponseBody
	public ResponseEntity<?> getMyLectures(@RequestParam(value = "user_seq", required = false) Integer userSeq ) {
		if (userSeq == null) {
			return ResponseEntity.badRequest().body("user_seq is required");
		}
		System.out.println("내가 듣는 강의 목록 불러오기 ");
		
	
		
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

	// NOTE - 문의사항
	@GetMapping(value = "/asks/new")
	public String asksForm() {

		System.out.println("문의사항 페이지");

		return "view_Sjm/askCreate";
	}

	// NOTE - 문의사항 작성
	@PostMapping(value = "/api/asks/new")
	public ResponseEntity<Integer> postAsks(Ask ask, HttpSession session) {

		System.out.println("작성 시작");

		ask.setUser_seq((int) session.getAttribute("user_seq"));
		int result = ss.postAsks(ask);

		return ResponseEntity.ok(result);
	}

	// NOTE - 내 문의사항
	@GetMapping(value = "/asks/my")
	public String asksListForm() {

		System.out.println("문의사항 목록 페이지");

		return "view_Sjm/askMyList";
	}

	// 문의사항 리스트 
	@GetMapping(value = "/api/asks/my")
	@ResponseBody
	public List<Ask> getAsksMy(HttpSession session) {
		System.out.println("컨트롤러 문의사항 리스트 시작");

		Map<String, Object> params = new HashMap<>();
		params.put("user_seq", session.getAttribute("user_seq"));
		List<Ask> ask = null;
				

		
		int user_status = (int) session.getAttribute("usertype");
		
		if (user_status == 1003) {
			System.out.println("관리자 리스트");
			ask = ss.getAsks();
		}else {
			System.out.println("내리스트");
		ask = 	ss.getAsksMy(params);
			
		}
		
		return ask;
	}

	// 문의사항 상세 페이지
	@GetMapping(value = "/asks/{dscsn_sn}")
	public String askDetailForm(@PathVariable("dscsn_sn") int dscsn_sn, Model model) {
		System.out.println("쪽지 디테일 화면");
		model.addAttribute("note_sn", dscsn_sn);
		return "view_Sjm/askDetail"; // view_Sjm/noticeDetail.jsp로 이동
	}

	// NOTE - 문의사항 상세정보 조회
	@GetMapping(value = "/api/asks/{dscsn_sn}")
	@ResponseBody
	public Ask getAsk(@PathVariable("dscsn_sn") int dscsn_sn) {
		System.out.println("문의사항 상세 시작");

		Ask ask = ss.getAsk(dscsn_sn);
		System.out.println("ask-->" + ask);
		return ask;
	}
	
	// NOTE - 문의사항 답변 작성 
	@PostMapping(value = "/api/asks/reply")
	public ResponseEntity<Integer> replyUpdateAsks(Ask ask, HttpSession session) {
		System.out.println("답변작성 시작 -->" + ask);
		int user_seq =(int) session.getAttribute("user_seq");
		ask.setDscsn_ans_seq(user_seq);
		int result = ss.replyUpdateAsks(ask);
	
		return ResponseEntity.ok(result);
	}
}
