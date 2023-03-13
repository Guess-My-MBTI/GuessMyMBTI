import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import ListName from "../components/ListName";
import API from "../utils/API";

//localStorage에서 user name 불러오기
const name = localStorage.getItem("name");
// 이름이 3글자 이상이면 뒤에 두 글자만 가져옴
const nameData = [
  { ownerName: name.length >= 3 ? name.slice(-2) : name, id: 1 },
];

// const baseUrl = "http://localhost:8080/";

const GuestLogin = () => {
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

  const handleSubmit = (e) => {
    if (state.nickName.length < 1) {
      nickNameInput.current.focus();
      return;
    } else if (state.nickName.length > 10) {
      alert("닉네임을 10자 이내로 설정해주세요!");
      setState({ nickName: "" });
      return;
    } else {
      API.post("/guest-login", { nickname: state.nickName })
        .then((res) => {
          if (res.status === 200) {
            console.log(state);
            localStorage.setItem("nickname", state.nickName);
            navigate("/question");
          }
        })
        .catch((error) => console.log(error.res));
    }
  };

  return (
    <div className="GuestLogin">
      <div className="banner">
        <div className="banner-top">
          <img
            src={process.env.PUBLIC_URL + `assets/pencil1.png`}
            className="pencil1"
          />
          <ListName key={nameData.id} data={nameData} />
          <p>의</p>
        </div>

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
