import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";

const GuestLogin = () => {
  // 'OO의 MBTI를 맞춰봐' 에서 링크 주인의 이름 대신 dummyData 만들어서 넣어둠
  // const dummyData = { owner: "서아름" };

  const navigate = useNavigate();

  const nickNameInput = useRef();
  const [state, setState] = useState({ nickName: "" });

  const handleChangeState = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
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
      {/* 이상하게 나옴 */}
      {/* <h2>{`${dummyData}`}의 MBTI를 맞춰봐</h2> */}

      <h2>MBTI를 맞춰봐</h2>
      <div>NICKNAME을 입력해주세요</div>
      <div>
        <input
          name="nickName"
          ref={nickNameInput}
          value={state.nickName}
          onChange={handleChangeState}
        />
      </div>
      <div>
        <button onClick={handleSubmit}>START</button>
      </div>
    </div>
  );
};

export default GuestLogin;
