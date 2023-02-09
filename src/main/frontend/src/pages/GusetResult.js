import { useNavigate } from "react-router-dom";

const GuestResult = () => {
  const navigate = useNavigate();
  const goHome = () => navigate("/guest-login");
  const copiedLink = () => alert("링크가 복사되었습니다!");

  return (
    <div className="GuestResult">
      <div className="goHome">
        <p className="home">홈으로</p>
        <div className="home_img_wrapper" onClick={goHome}>
          <img
            className="home_img"
            src={process.env.PUBLIC_URL + `assets/Home.png`}
          />
        </div>
      </div>

      <div className="goLink">
        <p className="link">공유하기</p>
        <div className="link_img_wrapper" onClick={copiedLink}>
          <img
            className="link_img"
            src={process.env.PUBLIC_URL + `assets/Link.png`}
          />
        </div>
      </div>
    </div>
  );
};

export default GuestResult;
