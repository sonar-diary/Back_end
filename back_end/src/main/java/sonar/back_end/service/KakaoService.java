package sonar.back_end.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sonar.back_end.dto.KakaoDTO;
import sonar.back_end.entity.SocialType;
import sonar.back_end.entity.UserEntity;
import sonar.back_end.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.redirect.url}")
    private String redirectUrl;

    public String getAccessToken(String code) {
        // 카카오 OAuth2 토큰 엔드포인트
        String tokenUri = "https://kauth.kakao.com/oauth/token";

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUrl);
        body.add("code", code);
        body.add("client_secret", clientSecret);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<KakaoDTO.KakaoTokenResponse> response = restTemplate.exchange(
                tokenUri,
                HttpMethod.POST,
                request,
                KakaoDTO.KakaoTokenResponse.class
        );

        return response.getBody().getAccess_token();
    }

    public KakaoDTO.KakaoUserInfo getUserInfo(String accessToken) {
        // 카카오 사용자 정보 엔드포인트
        String userInfoUri = "https://kapi.kakao.com/v2/user/me";

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<KakaoDTO.KakaoUserInfo> response = restTemplate.exchange(
                userInfoUri,
                HttpMethod.GET,
                request,
                KakaoDTO.KakaoUserInfo.class
        );
        System.out.println(response.getBody());
        return response.getBody();
    }

    public UserEntity login(KakaoDTO.KakaoUserInfo userInfo) {
        // 이미 가입된 회원인지 확인
        Optional<UserEntity> existingUser = userRepository
                .findBySocialIdAndSocialType(
                        String.valueOf(userInfo.getId()),
                        SocialType.KAKAO
                );

        // 가입된 회원이면 그대로 반환, 아니면 회원가입 후 반환
        return existingUser.orElseGet(() ->
                userRepository.save(userInfo.toEntity())
        );
    }
}