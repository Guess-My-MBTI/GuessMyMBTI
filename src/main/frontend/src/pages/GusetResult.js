import { useNavigate } from "react-router-dom";
import React, { useRef, useState, useEffect } from "react";
import { AiFillHome } from "react-icons/ai";
import { HiOutlineLink } from "react-icons/hi";
import ListName from "../components/ListName";
import API from "../utils/API";
import axios from "axios";

//localStorage에서 user name 불러오기
const name = localStorage.getItem("name");
// 이름이 3글자 이상이면 뒤에 두 글자만 가져옴
const nameData = [
  { ownerName: name.length >= 3 ? name.slice(-2) : name, id: 1 },
];

const GuestResult = () => {
  const navigate = useNavigate();
  const mbti = localStorage.getItem("mbti");
  const result = localStorage.getItem("guest_mbti");
  const nickname = localStorage.getItem("nickname");
  const messageInput = useRef();

  const guestId = localStorage.getItem("id");
  const role = localStorage.getItem("role");

  // const [guestId, setGuestId] = useState("");
  // const [nickname, setNickname] = useState("");

  const owner_answer = JSON.parse(localStorage.getItem("owner_answer"));
  const guest_answer = JSON.parse(localStorage.getItem("guest_answer"));

  // const search = () => {
  //   return axios.create({
  //     baseURL: "http://localhost:8080/guest-info",
  //     headers: {
  //       "Content-Type": "application/json;charset=utf-8",
  //       "Access-Control-Allow-Origin": "*",
  //     },
  //   });
  // };

  // async function fetchData() {
  //   try {
  //     const res = await search().get();
  //     setGuestId(res.data);
  //     console.log(res.data);
  //   } catch (error) {
  //     console.log(error);
  //   }
  // }
  // fetchData();

  const calAcc = () => {
    let count = 0;
    for (let i = 0; i < owner_answer.length; i++) {
      if (owner_answer[i] == guest_answer[i]) {
        count += 5;
      } else {
        continue;
      }
    }
    return count;
  };

  const accuracy = calAcc();

  const [state, setState] = useState({
    result: result,
    accuracy: accuracy,
    comment: "",
  });

  const goHome = () => navigate("/owner-main");

  const share = () => alert("링크가 복사되었습니다!");

  // useEffect(() => {
  //   async function fetchData() {
  //     try {
  //       const response = await API.get("/guest-info");
  //       setGuestId(response.data);
  //       console.log(guestId);
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   }
  //   fetchData();
  // }, []);

  const handleChangeState = (e) => {
    // console.log(state);
    setState((state) => {
      return {
        ...state,
        [e.target.name]: e.target.value,
      };
    });
  };

  const handleSend = (e) => {
    if (state.comment.length < 1) {
      messageInput.current.focus();
      return;
    } else if (state.comment.length > 20) {
      alert("20자 이내로 설정해주세요!");
      setState({ comment: "" });
      return;
    } else {
      API.post("/guest-result", {
        nickname: nickname,
        result: state.result,
        accuracy: state.accuracy,
        comment: state.comment,
        guestId: guestId,
        role: state.role,
      })
        .then((res) => {
          if (res.status === 200) {
            console.log(res);
            alert("전달 완료!");
            navigate(`/`);
          }
        })
        .catch((error) => console.log(error.res));

      // console.log("nickname: " + state.nickname);
      // console.log("result: " + state.result);
      // console.log("accuracy: " + state.accuracy);
      // console.log("message: " + state.comment);
      // alert("전달 완료!");
      // navigate(`/`);
    }
  };

  return (
    <div className="GuestResult">
      <div className="answer">
        <div className="name">
          <ListName key={nameData.id} data={nameData} />의{" "}
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
          <p className="m">{mbti[0]}</p>
          <p className="b">{mbti[1]}</p>
          <p className="t">{mbti[2]}</p>
          <p className="i">{mbti[3]}</p>
        </div>
      </div>

      <div className="choose">
        <div className="c_name">
          당신이 생각한 <ListName key={nameData.id} data={nameData} />의{" "}
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
          <p className="m">{result[0]}</p>
          <p className="b">{result[1]}</p>
          <p className="t">{result[2]}</p>
          <p className="i">{result[3]}</p>
        </div>
      </div>

      <div className="accauracy">
        <div className="percentage">
          <p className="zero">0%</p>
          <p className="standard">정확도</p>
          <p className="perfect">100%</p>
        </div>
        <div className="graph">
          <span className={["graphAni", `graphAni_${accuracy}`].join(" ")}>
            {accuracy + "%"}
          </span>
        </div>
      </div>
      <br />
      <div className="sendMessage">
        <form className="message">
          <input
            placeholder="상대에게 남기고 싶은 한마디"
            name="comment"
            ref={messageInput}
            value={state.comment}
            onChange={handleChangeState}
          />
        </form>

        <div className="send">
          <button className="sendBtn" onClick={handleSend}>
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
