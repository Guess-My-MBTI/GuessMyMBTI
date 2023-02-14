const CLIENT_ID = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = "http://localhost:3000/login/oauth2/callback/kakao";

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
