import { BsFillPersonFill } from "react-icons/bs";
import { HiOutlineLink } from "react-icons/hi";
import { AiOutlineClose } from "react-icons/ai";
import { MdOutlineReplay, MdOutlineKeyboardBackspace } from "react-icons/md";
import { useNavigate } from "react-router-dom";
const Menu = ({ isOpen, setIsOpen }) => {
  const navigate = useNavigate();

  const menuToggle = () => {
    setIsOpen(!isOpen);
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
          <p className="text">링크 공유하기</p>
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
