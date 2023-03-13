import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const OwnerQuestion = () => {
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const [id, setId] = useState(0);
  const [answer, setAnswer] = useState([]);

  const baseUrl = "http://localhost:8080/";

  const accessToken = localStorage.getItem("access_token");

  //question list 불러옴
  useEffect(() => {
    axios({
      method: "GET",
      url: `${baseUrl}question/all`,
      headers: {
        // 요청을 할 때
        Authorization: "Bearer " + accessToken,
      },
    }).then((res) => {
      console.log(res.data.data);
      setData(res.data.data);
    });
  }, []);

  //answer이 변화할때만 id에 변화를줘야함
  useEffect(() => {
    console.log(answer);
    if (id == 20) {
      calcMbti(answer);
    }
    if (answer.length < 20) {
      setId(id + 1);
    }
  }, [answer]);

  //번호대로 불러온 질문과 답
  const list = data.filter((it) => it.id == id);

  //버튼 누를시 작동
  const answerHandeler = (e) => {
    if (e.target.className === "answer-card-1") {
      setAnswer(answer.concat(list?.map((it) => it.answer1).slice(0, 1)));
    } else {
      setAnswer(answer.concat(list?.map((it) => it.answer2).slice(0, 1)));
    }
  };

  // 테스트 후 삭제 (아름)
  localStorage.setItem("owner_answer", answer);

  const calcMbti = (answer) => {
    const EI = answer.slice(0, 5);
    const NS = answer.slice(5, 10);
    const FT = answer.slice(10, 15);
    const PJ = answer.slice(15, 20);
    console.log(EI);
    console.log(NS);
    console.log(FT);
    console.log(PJ);

    const countE = EI.filter((e) => "E" === e).length;
    const countI = EI.filter((e) => "I" === e).length;
    const countN = NS.filter((e) => "N" === e).length;
    const countS = NS.filter((e) => "S" === e).length;
    const countF = FT.filter((e) => "F" === e).length;
    const countT = FT.filter((e) => "T" === e).length;
    const countP = PJ.filter((e) => "P" === e).length;
    const countJ = PJ.filter((e) => "J" === e).length;

    const mbti =
      (countE > countI ? "E" : "I") +
      (countN > countS ? "N" : "S") +
      (countF > countT ? "F" : "T") +
      (countP > countJ ? "P" : "J");

    console.log(mbti);
    localStorage.setItem("mbti", mbti);
    // storeDB(answer, mbti);
    navigate("/owner-result");
  };

  const storeDB = (answer, mbti) => {
    axios({
      method: "POST",
      url: `${baseUrl}owner-result`,
      headers: {
        "Content-Type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin": "*",
      },
      body: {
        mbti: mbti,
        result: answer,
      },
    }).then((res) => {
      navigate("/owner-result");
    });
  };

  return (
    <div className="Question">
      <div className="ques-banner">
        <p className="Q">Q</p>
        <p className="U">U</p>
        <p className="E">E</p>
        <p className="S">S</p>
        <p className="T">T</p>
        <p className="I">I</p>
        <p className="O">O</p>
        <p className="N">N</p>
      </div>

      <div className="ques-card">
        <p className="ques-num">Q{id}.</p>
        <p className="ques-text">{list.map((it) => it.content)}</p>
      </div>

      <div className="progress">
        <div className="progress-graph">
          <span className={["proGraph", `proGraph_${id}`].join(" ")}></span>
        </div>
        <div className="progress-btn">
          <button className="prev"> {"<"}PREV </button>
        </div>
      </div>

      <div className="answer">
        <div className="ans-banner">
          <p className="A">A</p>
          <p className="N">N</p>
          <p className="S">S</p>
          <p className="W">W</p>
          <p className="E">E</p>
          <p className="R">R</p>
        </div>

        <div className="answer-card-1">
          <div className="answer-card-1" onClick={answerHandeler}>
            <p className="ans-text-1">
              {/* 전체 데이터 들어오면 slice(1)로 바꿔야함 */}
              {list.map((it) => it.answer1.slice(0, 1))}
            </p>
          </div>
        </div>

        <div className="answer-card-2">
          <div className="answer-card-2" onClick={answerHandeler}>
            <p className="ans-text-2">
              {list.map((it) => it.answer2.slice(0, 1))}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OwnerQuestion;
