import axios from "axios";
import { useEffect, useState } from "react";
import { AiFillHome } from "react-icons/ai";
import { HiOutlineLink } from "react-icons/hi";
import { MdOutlineReplay } from "react-icons/md";
import { Link, useNavigate } from "react-router-dom";
import UrlAPI from "../utils/UrlAPI";

const OwnerResult = () => {
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const baseUrl = UrlAPI;

  const accessToken = localStorage.getItem("access_token");
  const mbti = localStorage.getItem("mbti");
  const ownerId = localStorage.getItem("id");
  console.log(accessToken);

  useEffect(() => {
    axios({
      method: "GET",
      url: `${baseUrl}result/all`,
      headers: {
        Authorization: "Bearer " + accessToken,
      },
    }).then((res) => {
      console.log(res.data.data);
      setData(res.data.data);
    });
  }, []);

  const list = data.filter((it) => it.mbti == mbti);

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
    <div className="OwnerResult">
      <div className="result">
        <p className="r">RESULT</p>
        <div className="mbti-box">
          <div className="mbti">
            <p className="m">{mbti[0]}</p>
            <p className="b">{mbti[1]}</p>
            <p className="t">{mbti[2]}</p>
            <p className="i">{mbti[3]}</p>
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
            <HiOutlineLink className="share" onClick={shareLink} />
          </div>
        </div>
      </div>
    </div>
  );
};
export default OwnerResult;
