import axios from "axios";
import { useEffect, useState } from "react";
import { AiFillHome } from "react-icons/ai";
import { HiOutlineLink } from "react-icons/hi";
import { MdOutlineReplay } from "react-icons/md";
import { useNavigate } from "react-router-dom";
const OwnerResult = () => {
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const baseUrl = "http://localhost:8080/";
  const result = "INFJ";

  useEffect(() => {
    axios({
      method: "GET",
      url: `${baseUrl}result/all`,
    }).then((res) => {
      console.log(res.data.data);
      setData(res.data.data);
    });
  }, []);
  const list = data.filter((it) => it.mbti == result);
  return (
    <div className="OwnerResult">
      <div className="result">
        <p className="r">RESULT</p>
        <div className="mbti-box">
          <div className="mbti">
            <p className="m">{result[0]}</p>
            <p className="b">{result[1]}</p>
            <p className="t">{result[2]}</p>
            <p className="i">{result[3]}</p>
          </div>
        </div>
      </div>
      <div className="mbti-description">
        <p className="mbti-sum">{list.map((it) => it.name)}</p>
        <div className="mbti-des-box">
          <p className="des">{list.map((it) => it.charType)}</p>
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
