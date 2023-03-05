import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import axios from "axios";
const LoginHandeler = (props) => {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get("code");

  useEffect(() => {
    // axios
    //   .post(`${process.env.REACT_APP_REDIRECT_URL}kakaoLogin${code}`)
    //   .then((res) => {
    //     console.log(res.data);
    //     // 토큰을 받아서 localStorage같은 곳에 저장하는 코드를 여기에 쓴다.
    //     localStorage.setItem("name", res.data.user_name); // 일단 이름만 저장했다.
    //     navigate("/question");
    //   });

    console.log("code : " + code);
      fetch(`http://localhost:8080/login/oauth2/callback/kakao/?code=${code}`, {
          method: "GET",
          headers: {
              'Content-Type' : 'application/json;charset=utf-8',
              'Access-Control-Allow-Origin': '*',
          }
      }).then(response => {
          console.log("fetch : " + response.headers.get('Authorization'));
          localStorage.setItem("access_token", response.headers.get('Authorization'));
          navigate("/question");
      })

  //   const kakaoLogin = async () => {
  //     await axios
  //       // .get(`${process.env.REACT_APP_REDIRECT_URL}?code=${code}`)
  //       .get(`http://localhost:8080/login/oauth2/callback/kakao/?code=${code}`)
  //       .then((res) => {
  //         console.log(res);
  //         localStorage.setItem("token", res.headers.authorization);
  //         navigate("/question");
  //       });
  //   };
  //   kakaoLogin();
  }, [props.history]);


  return <div></div>;
};

export default LoginHandeler;
