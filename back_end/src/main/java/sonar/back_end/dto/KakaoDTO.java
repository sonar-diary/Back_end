package sonar.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sonar.back_end.entity.SocialType;
import sonar.back_end.entity.UserEntity;

public class KakaoDTO {
    @Getter
    @NoArgsConstructor
    public static class KakaoTokenResponse {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private String id_token;
        private int expires_in;
        private int refresh_token_expires_in;
        private String scope;
    }

    @Getter
    @NoArgsConstructor
    public static class KakaoUserInfo {
        private Long id;
        private KakaoAccount kakao_account;

        @Getter
        @NoArgsConstructor
        public static class KakaoAccount {
            private String email;
            private String name;

        }
        // UserEntity로 변환하는 메서드 추가
        public UserEntity toEntity() {
            return UserEntity.builder()
                    .socialId(String.valueOf(this.id))  // Long -> String 변환
                    .socialType(SocialType.KAKAO)       // KAKAO로 설정
                    .name(this.kakao_account.name)
                    .email(this.kakao_account.email)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
        private UserEntity user;
    }
}
