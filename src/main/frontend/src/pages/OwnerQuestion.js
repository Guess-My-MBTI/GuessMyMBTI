import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const OwnerQuestion = () => {
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const [id, setId] = useState(1);

  const baseUrl = "http://localhost:8080/";

  const list = data.filter((it) => parseInt(it.id) == parseInt(id));
  const [answer, setAnswer] = useState([]);

  console.log(answer);

  const accessToken = localStorage.getItem("access_token");

  console.log(accessToken);

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
      setData(res.data.data);
    });
  }, []);

  const decreaseQuestion = () => {
    if (id <= 1) {
      navigate({ response: true });
    } else {
      setId(id - 1);

      // 뒤로 가면 선택됐던 값 삭제되도록 (다시 선택할거니까)
      answer.splice(answer.indexOf(answer.length - 1), 1);
      setAnswer([...answer]);
    }
  };

  const increaseQuestion = () => {
    if (id >= 20) {
      navigate("/owner-result");
    } else {
      setId(id + 1);
    }
  };

  const answerHandler = (e) => {
    // 첫 번째 보기 선택
    if (e.target.className == "answer-card-1") {
      setAnswer(answer.concat(list.map((it) => it.answer1).slice(0, 1)));
    }
    // 두 번째 보기 선택
    else {
      setAnswer(answer.concat(list.map((it) => it.answer2).slice(0, 1)));
    }

    // 정답 중복 선택을 방지하기 위해 답을 선택하면 바로 다음 문제로 넘어가도록 함
    {
      increaseQuestion();
    }
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
          <button className="prev" onClick={decreaseQuestion}>
            {" "}
            {"<"}PREV{" "}
          </button>
          {/* <button className="next" onClick={increaseQuestion}>
              {" "}
              NEXT{">"}
            </button> */}
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
          <div
            className="answer-card-1"
            value="answer1"
            onClick={answerHandler}
          >
            <p className="ans-text-1">{list.map((it) => it.answer1)}</p>
          </div>
        </div>

        <div className="answer-card-2">
          <div
            className="answer-card-2"
            value="answer2"
            onClick={answerHandler}
          >
            <p className="ans-text-2">{list.map((it) => it.answer2)}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OwnerQuestion;
