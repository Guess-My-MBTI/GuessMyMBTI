import axios from "axios";
import React, { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import ListName from "../components/ListName";
import API from "../utils/API";
import UrlAPI from "../utils/UrlAPI";
import OwnerMain from "./OwnerMain";

const GuestLogin = () => {
  const navigate = useNavigate();
  const nickNameInput = useRef();

  // 중복 클릭 방지 (isLoding이 false면 disabled)
  const [isLoading, setIsLoading] = useState(false);
  const [state, setState] = useState({
    nickName: "",
    role: "ROLE_GUEST",
  });
  const [ownerName, setOwnerName] = useState("");

  const ownerId = new URL(window.location.href).searchParams.get("id");
  const baseUrl = UrlAPI;

  const handleChangeState = (e) => {
    setState((state) => {
      return {
        ...state,
        [e.target.name]: e.target.value,
      };
    });
  };

  //mount시 owner정보 가져오기 이제 localstorage에서 가져오면 안됨
  useEffect(() => {
    axios({
      method: "GET",
      url: `${baseUrl}guest-login`,
      params: {
        id: ownerId,
      },
    }).then((res) => {
      console.log(res.data);
      localStorage.setItem("name", res.data.kakaoName);
      localStorage.setItem("owner_answer", res.data.result);
      localStorage.setItem("mbti", res.data.mbti);
      setOwnerName(res.data.kakaoName);
    });
  }, []);

  const nameData = [
    {
      ownerName: ownerName.length >= 3 ? ownerName.slice(-2) : ownerName,
      id: 1,
    },
  ];

  // todo : url 파라미터에서 id 값을 빼와서 post 요청할 때 ownerId를 포함해서 수행하도록 했습니다.
  // todo : 추가적으로 로그인 할 때 조금 느린 경향이 있어서 수빈이처럼 handler를 만들어서 딜레이 주는 것도 좋아보입니당

  console.log(ownerId);
  const handleLogin = () => {
    setIsLoading(true);
    API.post("/guest-login/?id=" + ownerId, {
      nickname: state.nickName,
      role: state.role,
    })
      .then((res) => {
        if (res.status === 200) {
          localStorage.setItem("nickname", state.nickName);
          localStorage.setItem("role", state.role);
          console.log(state);
          console.log(res);
          navigate("/question");
        }
      })
      .catch((error) => console.log(error.res))
      .finally(() => setIsLoading(false));
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
      handleLogin();
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
            disabled={isLoading}
          >
            START
          </button>
        </div>
      </div>
    </div>
  );
};

export default GuestLogin;
