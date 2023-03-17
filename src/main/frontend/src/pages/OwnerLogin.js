import { BsQuestion } from "react-icons/bs";
import { useNavigate } from "react-router-dom";
import { KAKAO_AUTH_URL } from "../components/OAuth";

const OwnerLogin = () => {
  const navigate = useNavigate();

  return (
    <div className="OwnerLogin">
      <div className="banner">
        <p className="me">
          <img
            src={process.env.PUBLIC_URL + `assets/pencil1.png`}
            className="pencil1"
          />
          나의
        </p>

        <div className="mbti">
          <img
            src={process.env.PUBLIC_URL + `assets/pencil2.png`}
            className="pencil2"
          />
          <p className="m">M</p>
          <p className="b">B</p>
          <p className="t">T</p>
          <p className="i">I</p>
          <p className="r">를</p>
        </div>

        <p className="guess">맞춰봐</p>
        <div>
          <BsQuestion
            className="info"
            onClick={() => {
              navigate("/notice");
            }}
          />
        </div>
      </div>

      <div className="login_wrapper">
        <a href={KAKAO_AUTH_URL} className="kakaobtn">
          <img src={process.env.PUBLIC_URL + `assets/Kakao.png`} />
        </a>
        <img
          className="googlebtn"
          src={process.env.PUBLIC_URL + `assets/Google.png`}
        />
      </div>
    </div>
  );
};
export default OwnerLogin;
