import { AiFillHome } from "react-icons/ai";
import { HiOutlineLink } from "react-icons/hi";
import { MdOutlineReplay } from "react-icons/md";
import { useNavigate } from "react-router-dom";
const OwnerResult = () => {
  const navigate = useNavigate();

  return (
    <div className="OwnerResult">
      <div className="result">
        <p className="r">RESULT</p>
        <div className="mbti-box">
          <div className="mbti">
            <p className="m">M</p>
            <p className="b">B</p>
            <p className="t">T</p>
            <p className="i">I</p>
          </div>
        </div>
      </div>
      <div className="mbti-description">
        <p className="mbti-sum">새학기 인싸</p>
        <div className="mbti-des-box">
          <p className="des">어쩌구 저쩌구 어쩔 뚱땡이 저쩔 냉장고</p>
        </div>
      </div>
      <div className="button-wrapper">
        <div className="text">
          <div>다시하기</div>
          <div>홈으로</div>
          <div>공유하기</div>
        </div>
        <div className="icons">
          <div>
            <MdOutlineReplay className="return" />
          </div>
          <div>
            <AiFillHome
              className="home"
              onClick={() => {
                navigate("/owner-main");
              }}
            />
          </div>
          <div>
            <HiOutlineLink className="share" />
          </div>
        </div>
      </div>
    </div>
  );
};
export default OwnerResult;
