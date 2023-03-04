import { useNavigate } from "react-router-dom";
import React, { useRef, useState } from "react";
import { AiFillHome } from "react-icons/ai";
import { HiOutlineLink } from "react-icons/hi";
import ListName from "../components/ListName";

// '당신이 생각한 OO의 MBTI'에 들어감
const dummyData = [{ ownerName: "아름", id: 1 }];

const GuestResult = () => {
  const navigate = useNavigate();
  const oResult = "INFJ";
  const gResult = "INFP";
  const messageInput = useRef();
  const [state, setState] = useState({ message: "" });

  const goHome = () => navigate("/guest-login");
  const share = () => alert("링크가 복사되었습니다!");

  const handleChangeState = (e) => {
    // console.log(state);
    setState((state) => {
      return {
        ...state,
        [e.target.name]: e.target.value,
      };
    });
  };

  const handleSend = () => {
    if (state.message.length < 1) {
      messageInput.current.focus();
      return;
    } else if (state.message.length > 20) {
      alert("20자 이내로 설정해주세요!");
      setState({ message: "" });
      return;
    } else {
      console.log(state.message);
      alert("전달 완료!");
      navigate(`/`);
    }
  };

  return (
    <div className="GuestResult">
      <div className="answer">
        <div className="name">
          <ListName key={dummyData.id} data={dummyData} />의{" "}
        </div>

        <div className="mbti">
          <p className="m">M</p>
          <p className="b">B</p>
          <p className="t">T</p>
          <p className="i">I</p>
        </div>
      </div>

      <div className="answerCard">
        <div className="mbti">
          <p className="m">{oResult[0]}</p>
          <p className="b">{oResult[1]}</p>
          <p className="t">{oResult[2]}</p>
          <p className="i">{oResult[3]}</p>
        </div>
      </div>

      <div className="choose">
        <div className="c_name">
          당신이 생각한 <ListName key={dummyData.id} data={dummyData} />의{" "}
        </div>
        <div className="c_mbti">
          <p className="m">M</p>
          <p className="b">B</p>
          <p className="t">T</p>
          <p className="i">I</p>
        </div>
      </div>

      <div className="chooseCard">
        <div className="mbti">
          <p className="m">{gResult[0]}</p>
          <p className="b">{gResult[1]}</p>
          <p className="t">{gResult[2]}</p>
          <p className="i">{gResult[3]}</p>
        </div>
      </div>

      <div className="accauracy">
        <div className="percentage">
          <p className="zero">0%</p>
          <p className="standard">정확도</p>
          <p className="perfect">100%</p>
        </div>
        <div className="graph">
          <span>80%</span>
        </div>
      </div>
      <br />
      <div className="sendMessage">
        <form className="message">
          <input
            placeholder="상대에게 남기고 싶은 한마디"
            name="message"
            ref={messageInput}
            value={state.message}
            onChange={handleChangeState}
          />
        </form>

        <div className="send">
          <button
            className="sendBtn"
            onClick={handleSend}
            style={{ cursor: "pointer" }}
          >
            send
          </button>
        </div>
      </div>

      <div className="button-wrapper">
        <div className="text">
          <div>홈으로</div>
          <div>공유하기</div>
        </div>

        <div className="icons">
          <div onClick={goHome}>
            <AiFillHome className="Home" />
          </div>
          <div onClick={share}>
            <HiOutlineLink className="Link" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default GuestResult;
