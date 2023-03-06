const CLIENT_ID = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = process.env.REACT_APP_REDIRECT_URL;

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=a1698cd60aafa124b03e4117dd52549f&redirect_uri=http://localhost:3000/login/oauth2/callback/kakao&response_type=code`;
// .get(`https://kauth.kakao.com/oauth/authorize?client_id=a1698cd60aafa124b03e4117dd52549f&redirect_uri=http://localhost:8080/login/oauth2/callback/kakao&response_type=code`)
// export const KAKAO_AUTH_URL = `http://localhost:8080/oauth2/authorization/kakao`;
