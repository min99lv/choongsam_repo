package com.postgre.choongsam.controller;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.dto.User_Info;
import com.postgre.choongsam.service.HjhService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "view_Hjh")
@RequiredArgsConstructor
public class HjhController {

	private final HjhService hjh;

	@RequestMapping(value = "/adminPage")
	public String adminPage(Model model) {
		System.out.println("관리자마이페이지");
		return "view_Hjh/adminPage";

	}
	
	@RequestMapping(value = "/myPageTeacher")
	public String myPageTeacher(HttpSession session) {

		System.out.println("강사마이페이지");
		System.out.println(session.getAttribute("user"));

		return "view_Hjh/myPageTeacher";
	}

	@RequestMapping(value = "/myPageStd")
	public String myPageStd(HttpSession session) {
		System.out.println("학생마이페이지");
		System.out.println(session.getAttribute("user"));
		return "view_Hjh/myPageStd";
	}

	// 학생 개인정보수정
	@GetMapping("updateProfile")
	public String updateProfile(HttpSession session, Model model, @RequestParam String user) {
		// 세션에서 user_id 가져오기
		String userId = (String) session.getAttribute("user");

		// userId를 사용하여 필요한 로직 추가
		System.out.println("user: " + user);
		System.out.println("user_id from session: " + userId);

		// 사용자 정보 가져오기
		User_Info userInfo = hjh.detailProfileuser(userId);

		// 사용자 정보 확인
		if (userInfo != null) {
			System.out.println("User Info retrieved in controller: " + userInfo);

			// 모델에 사용자 정보 추가
			model.addAttribute("user_seq", userInfo.getUser_seq());
			model.addAttribute("user_id", userInfo.getUser_id());
			model.addAttribute("user_name", userInfo.getUser_name());
			model.addAttribute("birth", userInfo.getBirth());
			model.addAttribute("profile_name", userInfo.getProfile_name());
			model.addAttribute("profile_addr", userInfo.getProfile_addr());
			model.addAttribute("address", userInfo.getAddress());
			model.addAttribute("email", userInfo.getEmail());
			model.addAttribute("phone_num", userInfo.getPhone_num());
		} else {
			System.out.println("No user info found for userId: " + userId);
		}

		model.addAttribute("userId", userId);

		return "view_Hjh/updateProfile"; // 뷰 이름 반환
	}
	
	//강사 프로필수정
	@GetMapping("updateProfileteacher")
	public String updateProfileteacher(HttpSession session, Model model, @RequestParam String user) {
		// 세션에서 user_id 가져오기
		String userId = (String) session.getAttribute("user");

		// userId를 사용하여 필요한 로직 추가
		System.out.println("user: " + user);
		System.out.println("user_id from session: " + userId);

		// 사용자 정보 가져오기
		User_Info userInfo = hjh.detailProfileuser(userId);

		// 사용자 정보 확인
		if (userInfo != null) {
			System.out.println("User Info retrieved in controller: " + userInfo);

			// 모델에 사용자 정보 추가
			model.addAttribute("user_seq", userInfo.getUser_seq());
			model.addAttribute("user_id", userInfo.getUser_id());
			model.addAttribute("user_name", userInfo.getUser_name());
			model.addAttribute("birth", userInfo.getBirth());
			model.addAttribute("profile_name", userInfo.getProfile_name());
			model.addAttribute("profile_addr", userInfo.getProfile_addr());
			model.addAttribute("address", userInfo.getAddress());
			model.addAttribute("email", userInfo.getEmail());
			model.addAttribute("phone_num", userInfo.getPhone_num());
		} else {
			System.out.println("No user info found for userId: " + userId);
		}

		model.addAttribute("userId", userId);

		return "view_Hjh/updateProfileteacher"; // 뷰 이름 반환
	}
	



	@RequestMapping(value = "/updateProfileAdmin")
	public String updateProfileAdmin(Model model,
			@RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
			@RequestParam(value = "keyword", required = false) String keyword) {

		int totaluser = hjh.totaluser(keyword);
		System.out.println("리스트내놔");
		Paging page = new Paging(totaluser, currentPage);
		Map<String, Object> params = new HashMap<>();
		params.put("start", page.getStart());
		params.put("rowPage", page.getRowPage());
		params.put("keyword", keyword);
		List<User_Info> userList = hjh.userList(params);
		System.out.println("userList"+userList);

		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		model.addAttribute("totaluser", totaluser);
		model.addAttribute("userList", userList);
		return "view_Hjh/updateProfileAdmin";
	}
	// 리스트에서 수정창나와서 수정

	@RequestMapping("detailProfile/{user_id}") public String
	 detailProfile(@PathVariable("user_id") String user_id, Model model) {
	 User_Info user_Info = hjh.detailProfile(user_id);
	 System.out.println("dd");
	 System.out.println("detailProfile");
	 
	 if (user_Info != null) { model.addAttribute("user_seq",
	 user_Info.getUser_seq()); model.addAttribute("user_id",
	 user_Info.getUser_id()); model.addAttribute("user_name",
	 user_Info.getUser_name()); model.addAttribute("email", user_Info.getEmail());
	 model.addAttribute("address", user_Info.getAddress());
	 model.addAttribute("phone_num", user_Info.getPhone_num()); 
	 model.addAttribute("birth", user_Info.getBirth());
	 model.addAttribute("user_id", user_Info.getUser_id());
		model.addAttribute("profile_name", user_Info.getProfile_name());
		model.addAttribute("profile_addr", user_Info.getProfile_addr());
	 }
	 else { // 사용자
	
	 model.addAttribute("error", "사용자 정보를 찾을 수 없습니다."); }
	 
	 return "view_Hjh/detailProfile"; 
	 
	 }
	
	//관리자 회원정보수정
	@RequestMapping("/detailProfileEdit")
	public String userProfile1(@ModelAttribute User_Info info) {
		System.out.println("detailProfileEdit");
		int updateCountAdmin = hjh.updateCountAdmin(info);
		// 수정 작업 수행 System.out.println("updateCount"+updateCount); // 수정 결과에 따라 다르게처리할 수
		// 있습니다.
		System.out.println("zz");
		return "redirect:/view_Hjh/updateProfileAdmin";
	}
  
	//강사 개인정보수정
	@RequestMapping("updateUserInfo1")
	public String updateUserInfo1(@ModelAttribute User_Info info, 
	                               @RequestParam(value = "profileImage", required = false) MultipartFile profileImage, 
	                               HttpServletRequest request, 
	                               Model model) {
	    System.out.println("updateUserInfo1 Start...");

	    // 프로필 사진 파일을 업로드할 경로 지정 (서버의 실제 경로 사용)
	    String uploadDir = request.getServletContext().getRealPath("/chFile/user/");

	    // 파일이 선택되지 않은 경우 처리
	    if (profileImage != null && !profileImage.isEmpty()) {
	        // 파일 이름이 비어 있거나 확장자가 없는 경우 처리
	        String originalFileName = profileImage.getOriginalFilename();
	        if (originalFileName == null || originalFileName.isEmpty()) {
	            model.addAttribute("msg", "파일을 선택하지 않았습니다.");
	            return "main";  // 파일이 없으면 오류 메시지를 반환
	        }

	        // 확장자 추출 시, 확장자가 없는 경우 처리
	        int extIndex = originalFileName.lastIndexOf(".");
	        String fileExtension = "";
	        if (extIndex > 0) {
	            fileExtension = originalFileName.substring(extIndex);  // 확장자 추출
	        } else {
	            model.addAttribute("msg", "지원되지 않는 파일 형식입니다.");
	            return "main";  // 확장자가 없으면 오류 메시지를 반환
	        }

	        // UUID로 고유한 파일 이름 생성 (확장자 포함)
	        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;  // UUID + 확장자

	        // 파일 경로 설정
	        String profileAddr = "/chFile/user/" + uniqueFileName;  // 파일 경로

	        // 파일을 저장할 디렉토리 생성
	        File dir = new File(uploadDir);
	        if (!dir.exists()) {
	            dir.mkdirs();  // 폴더가 없다면 생성
	        }

	        // 파일 저장
	        try {
	            File file = new File(uploadDir + uniqueFileName);
	            profileImage.transferTo(file);  // 파일 저장
	            System.out.println("파일 업로드 성공: " + file.getAbsolutePath());
	        } catch (IOException e) {
	            e.printStackTrace();
	            model.addAttribute("msg", "파일 업로드에 실패했습니다.");
	            return "main";  // 업로드 실패시 반환할 페이지
	        }

	        // 프로필 이미지 경로와 이름을 사용자 정보에 세팅
	        info.setProfile_addr(profileAddr);  // DB에 저장할 프로필 주소
	        info.setProfile_name(uniqueFileName);  // DB에 저장할 프로필 파일명 (UUID)
	    } else {
	        // 파일을 선택하지 않으면 프로필 정보를 수정하지 않음 (혹은 기존 값 유지)
	        System.out.println("파일이 선택되지 않았습니다. 기존 프로필을 유지합니다.");
	    }

	    // 수정 작업 수행
	    int updateCount = hjh.userProfile1(info);  // 사용자 정보 수정
	    System.out.println("updateCount -> " + updateCount);

	    if (updateCount > 0) {
	        return "redirect:/view_Hjh/myPageTeacher";  // 수정 성공 후 이동할 페이지
	    } else {
	        model.addAttribute("msg", "사용자 정보 수정에 실패하였습니다.");
	        return "main";  // 수정 실패 시 이동할 페이지
	    }
	}

	//학생 개인정보수정
	@RequestMapping("updateUserInfo")
	public String updateUserInfo(@ModelAttribute User_Info info, 
	                              @RequestParam(value = "profileImage", required = false) MultipartFile profileImage, 
	                              HttpServletRequest request, 
	                              Model model) {
	    System.out.println("updateUserInfo Start...");

	    // 프로필 사진 파일을 업로드할 경로 지정 (서버의 실제 경로 사용)
	    String uploadDir = request.getServletContext().getRealPath("/chFile/user/");

	    // 파일이 선택되지 않은 경우 처리
	    if (profileImage != null && !profileImage.isEmpty()) {
	        // 파일 이름이 비어 있거나 확장자가 없는 경우 처리
	        String originalFileName = profileImage.getOriginalFilename();
	        if (originalFileName == null || originalFileName.isEmpty()) {
	            model.addAttribute("msg", "파일을 선택하지 않았습니다.");
	            return "main";  // 파일이 없으면 오류 메시지를 반환
	        }

	        // 확장자 추출 시, 확장자가 없는 경우 처리
	        int extIndex = originalFileName.lastIndexOf(".");
	        String fileExtension = "";
	        if (extIndex > 0) {
	            fileExtension = originalFileName.substring(extIndex);  // 확장자 추출
	        } else {
	            model.addAttribute("msg", "지원되지 않는 파일 형식입니다.");
	            return "main";  // 확장자가 없으면 오류 메시지를 반환
	        }

	        // UUID로 고유한 파일 이름 생성 (확장자 포함)
	        String uniqueFileName = UUID.randomUUID().toString() +   fileExtension;  // UUID + 확장자

	        // 파일 경로 설정
	        String profileAddr = "/chFile/user/" + uniqueFileName;  // 파일 경로

	        // 파일을 저장할 디렉토리 생성
	        File dir = new File(uploadDir);
	        if (!dir.exists()) {
	            dir.mkdirs();  // 폴더가 없다면 생성
	        }

	        // 파일 저장
	        try {
	            File file = new File(uploadDir + uniqueFileName);
	            profileImage.transferTo(file);  // 파일 저장
	            System.out.println("파일 업로드 성공: " + file.getAbsolutePath());
	        } catch (IOException e) {
	            e.printStackTrace();
	            model.addAttribute("msg", "파일 업로드에 실패했습니다.");
	            return "main";  // 업로드 실패시 반환할 페이지
	        }

	        // 프로필 이미지 경로와 이름을 사용자 정보에 세팅
	        info.setProfile_addr(profileAddr);  // DB에 저장할 프로필 주소
	        info.setProfile_name(uniqueFileName);  // DB에 저장할 프로필 파일명 (UUID)
	    } else {
	        // 파일을 선택하지 않으면 프로필 정보를 수정하지 않음 (혹은 기존 값 유지)
	        System.out.println("파일이 선택되지 않았습니다. 기존 프로필을 유지합니다.");
	    }

	    // 수정 작업 수행
	    int updateCount = hjh.userProfile1(info);  // 사용자 정보 수정
	    System.out.println("updateCount -> " + updateCount);

	    if (updateCount > 0) {
	        return "redirect:/view_Hjh/myPageStd";  // 수정 성공 후 이동할 페이지
	    } else {
	        model.addAttribute("msg", "사용자 정보 수정에 실패하였습니다.");
	        return "main";  // 수정 실패 시 이동할 페이지
	    }
	}
	@GetMapping("deleteStd")
	public String deleteStd(HttpSession session, Model model) {
	    // 세션에서 user 정보를 가져오기
	    String userId = (String) session.getAttribute("user");
	    
	    // 세션에서 userId가 존재하는지 확인
	    if (userId != null) {
	        // userId를 이용해 사용자 정보 가져오기
	        User_Info userInfo = hjh.detailProfileuser(userId);
	        
	        // 가져온 사용자 정보를 model에 추가하여 뷰에서 사용하도록 전달
	        model.addAttribute("user_id", userId);
	        model.addAttribute("userInfo", userInfo);  // 사용자 정보 추가
	        
	        // 로그로 세션에서 가져온 값 출력
	        System.out.println("user: " + userId);  // 세션에서 가져온 userId 출력
	        System.out.println("user_id from session: " + userId);  // 세션에서 가져온 userId 출력
	    } else {
	        // 세션에 userId가 없으면 로그인 페이지로 리다이렉트
	        return "redirect:/login";
	    }
	    
	    // 사용자 정보와 user_id를 포함한 페이지로 이동
	    return "view_Hjh/deleteStd";  // deleteStd 페이지로 이동
	}

	
	@GetMapping("deleteTeacher") 
	public String deleteTeacher(HttpSession session) {
		
		System.out.println(session.getAttribute("user"));
		return "view_Hjh/deleteTeacher";
	}
	
	@PostMapping("deleteStd1")
	public String deleteStd1(@RequestParam("password") String password,
	                         HttpSession session, Model model) {

	    // 세션에서 user_id를 가져옵니다.
	    String userId = (String) session.getAttribute("user");
	    
	    // 세션에 user_id가 없다면, 로그인 상태가 아닌 것이므로 처리할 수 없게 합니다.
	    if (userId == null) {
	        model.addAttribute("msg", "로그인 정보가 없습니다.");
	        return "view_Hjh/deleteStd";  // 로그인 페이지나 탈퇴 페이지로 이동
	    }

	    // 서비스에서 회원 탈퇴 처리
	    int result = hjh.deleteStd1(userId, password);

	    if (result == 1) {
	        // 회원 탈퇴가 성공했으면, 세션 해제 (로그아웃)
	        session.invalidate();  // 세션 무효화
	        model.addAttribute("msg", "회원 탈퇴가 완료되었습니다.");
	        return "main";  // 탈퇴 후 마이페이지로 리다이렉트
	    } else if (result == 0) {
	        model.addAttribute("msg", "비밀번호가 일치하지 않거나 회원 정보가 없습니다.");
	        return "view_Hjh/deleteStd";  // 실패 메시지와 함께 탈퇴 페이지로 이동
	    } else {
	        model.addAttribute("msg", "탈퇴 처리에 실패하였습니다.");
	        return "view_Hjh/deleteStd";  // 탈퇴 실패 시 다시 탈퇴 페이지로 이동
	    }
	}
	@GetMapping("changePW")
	public String changePW(HttpSession session, Model model) {
	    // 세션에서 사용자 아이디를 가져옵니다.
	    String userId = (String) session.getAttribute("user"); // 세션에 저장된 사용자 아이디

	    // 사용자 아이디를 모델에 추가
	    model.addAttribute("userId", userId);

	    // 비밀번호 변경 페이지를 반환
	    return "view_Hjh/changePW";
	}
	
}


