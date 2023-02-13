import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";

const GuestLogin = () => {
  // 'OO의 MBTI를 맞춰봐' 에서 링크 주인의 이름 대신 dummyData 만들어서 넣어둠
  // const dummyData = { owner: "서아름" };

  const navigate = useNavigate();

  const nickNameInput = useRef();
  const [state, setState] = useState({ nickName: "" });

  const handleChangeState = (e) => {
    // console.log(state);
    setState((state) => {
      return {
        ...state,
        [e.target.name]: e.target.value,
      };
    });
  };

  const handleSubmit = () => {
    if (state.nickName.length < 1) {
      nickNameInput.current.focus();
      return;
    } else if (state.nickName.length > 10) {
      alert("닉네임을 10자 이내로 설정해주세요!");
      setState({ nickName: "" });
      return;
    } else {
      console.log(state.nickName);
      navigate("/Question");
    }
  };

  return (
    <div className="GuestLogin">
      <div className="banner">
        <p className="me">
          <img
            src={process.env.PUBLIC_URL + `assets/pencil1.png`}
            className="pencil1"
          />
          00의
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
      </div>
      <div className="Nick">
        <div className="write_Nick">
          <p className="N">N</p>
          <p className="I">I</p>
          <p className="C">C</p>
          <p className="K">K</p>
          <p className="N2">N</p>
          <p className="A">A</p>
          <p className="M">M</p>
          <p className="E">E</p>

          <p className="write"> 을 입력해주세요</p>
        </div>

        <form className="nickInput">
          <input
            placeholder="NICKNAME"
            name="nickName"
            ref={nickNameInput}
            value={state.nickName}
            onChange={handleChangeState}
          />
        </form>

        <div className="submit">
          <button
            className="submitBtn"
            onClick={handleSubmit}
            style={{ cursor: "pointer" }}
          >
            START
          </button>
        </div>
      </div>
    </div>
  );
};

export default GuestLogin;
