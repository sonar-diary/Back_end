package sonar.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sonar.back_end.dto.KakaoDTO;
import sonar.back_end.entity.UserEntity;
import sonar.back_end.service.KakaoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")  // 리액트 앱 주소
public class LoginController {
    private final KakaoService kakaoService; // 카카오 로그인 처리를 위한 서비스

    @GetMapping("/login/oauth2/callback/kakao")
    public ResponseEntity<UserEntity> kakaoCallback(@RequestParam String code) {
        String accessToken = kakaoService.getAccessToken(code);
        KakaoDTO.KakaoUserInfo userInfo = kakaoService.getUserInfo(accessToken);
        UserEntity user = kakaoService.login(userInfo);

        return ResponseEntity.ok(user);
    }
}