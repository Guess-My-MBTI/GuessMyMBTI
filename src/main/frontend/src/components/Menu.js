import { BsFillPersonFill } from "react-icons/bs";
import { HiOutlineLink } from "react-icons/hi";
import { AiOutlineClose } from "react-icons/ai";
import { MdOutlineReplay, MdOutlineKeyboardBackspace } from "react-icons/md";
import { useNavigate } from "react-router-dom";
import axios from "axios";
const Menu = ({ isOpen, setIsOpen }) => {
  const navigate = useNavigate();

  const baseUrl = "http://localhost:8080/";

  const accessToken = localStorage.getItem("access_token");
  const ownerId = localStorage.getItem("id");

  const menuToggle = () => {
    setIsOpen(!isOpen);
  };

  const shareLink = () => {
    axios({
      method: "GET",
      url: `${baseUrl}share`,
      headers: {
        Authorization: "Bearer " + accessToken,
        "Content-Type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin": "*",
      },
      params: {
        id: ownerId,
      },
    }).then((res) => {
      console.log(res.data);

      copyLink(res.data);
    });
  };

  const copyLink = async (link) => {
    try {
      await navigator.clipboard.writeText(link);
      alert("클립보드에 링크가 복사되었습니다.");
    } catch (e) {
      alert("실패 다시시도해주세요");
    }
  };

  return (
    <div className="Menu">
      <div className="menu-box">
        <div
          className="menuList m1"
          onClick={() => {
            navigate("/owner-result");
          }}
        >
          <BsFillPersonFill className="mymbti" />
          <p className="text">나의 MBTI</p>
        </div>
        <div className="menuList m2">
          <MdOutlineReplay className="return" />
          <p className="text">다시 검사하기</p>
        </div>
        <div className="menuList m3">
          <HiOutlineLink className="share" />
          <p className="text" onClick={shareLink}>
            링크 공유하기
          </p>
        </div>
        <div
          className="menuList m4"
          onClick={() => {
            navigate("/", { replace: true });
          }}
        >
          <MdOutlineKeyboardBackspace className="logout" />
          <p className="text">로그아웃</p>
        </div>
        <div className="m5">
          <AiOutlineClose onClick={menuToggle} />
        </div>
      </div>
    </div>
  );
};

export default Menu;
