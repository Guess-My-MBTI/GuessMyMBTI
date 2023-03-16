import { useState } from "react";
import { AiOutlineMenu } from "react-icons/ai";
import { HiOutlineLink } from "react-icons/hi";
import Menu from "../components/Menu";
import ListItem from "../components/ListItem";
import axios from "axios";

const dummyData = [
  {
    id: 1,
    nickname: "다람쥐",
    mbti: "ENFP",
    accuracy: 75,
    content: "너가 I였다고?!",
  },
  {
    id: 2,
    nickname: "김엉덩",
    mbti: "INFP",
    accuracy: 100,
    content: "안뇽ㅋ",
  },
  {
    id: 3,
    nickname: "아자잣",
    mbti: "ESFP",
    accuracy: 50,
    content: "졸려요",
  },
  {
    id: 4,
    nickname: "아자잣",
    mbti: "ESFP",
    accuracy: 50,
    content: "졸려요",
  },
  {
    id: 5,
    nickname: "아자잣",
    mbti: "ESFP",
    accuracy: 50,
    content: "졸려요",
  },
];

const OwnerMain = () => {
  const [isOpen, setIsOpen] = useState(false);

  const menuToggle = () => {
    setIsOpen(!isOpen);
  };
  const baseUrl = "http://localhost:8080/";

  const accessToken = localStorage.getItem("access_token");
  const mbti = localStorage.getItem("mbti");
  const ownerId = localStorage.getItem("id");
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
    <div className="OwnerMain">
      <div className="top">
        <p className="me">나는</p>
        <div className="mbtiresult">
          <p className="m">{mbti[0]}</p>
          <p className="b">{mbti[1]}</p>
          <p className="t">{mbti[2]}</p>
          <p className="i">{mbti[3]}</p>
        </div>
        <div className="menu">
          <AiOutlineMenu onClick={menuToggle} />
          {isOpen ? (
            <>
              <Menu isOpen={isOpen} setIsOpen={setIsOpen} />
            </>
          ) : (
            <></>
          )}
        </div>
      </div>
      <hr />
      <div className="list">
        {dummyData.length > 1 ? (
          <ListItem data={dummyData} />
        ) : (
          <>
            <div className="none-list">
              <p className="sharetext">공유하기</p>
              <HiOutlineLink className="share" />
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default OwnerMain;
