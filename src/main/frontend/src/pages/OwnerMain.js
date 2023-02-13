import { useState } from "react";
import { AiOutlineMenu } from "react-icons/ai";
import { HiOutlineLink } from "react-icons/hi";
import Menu from "../components/Menu";
import ListItem from "../components/ListItem";

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

  return (
    <div className="OwnerMain">
      <div className="top">
        <p className="me">나는</p>
        <div className="mbtiresult">
          <p className="m">M</p>
          <p className="b">B</p>
          <p className="t">T</p>
          <p className="i">I</p>
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
