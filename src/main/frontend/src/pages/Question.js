// import ListName from "../components/ListName";

// 'OO이 라면?'에 들어감
// const dummyData = [{ ownerName: "아름" }];

const Question = () => {
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
        <p className="ques-num">Q1.</p>
        <p className="ques-text">여기엔 질문이 들어갑니다람쥐.</p>
      </div>

      <div className="progress">
        <div className="progress-graph">
          <span></span>
        </div>
        <div className="progress-btn">
          <button className="prev"> {"<"}PREV </button>
          <button className="next"> NEXT{">"}</button>
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

          {/* <p className="owner-name">
            <ListName data={dummyData} />
          </p>
          <p className="e">이</p>
          <p className="r">라</p>
          <p className="m">면</p>
          <p className="ques-mark">?</p> */}
        </div>

        <div className="answer-card-1">
          <p className="ans-text-1">
            여기엔 질문에 대한 답변이 들어갑니다람쥐.
          </p>
        </div>

        <div className="answer-card-2">
          <p className="ans-text-2">
            여기엔 질문에 대한 답변이 들어갑니다람쥐.
          </p>
        </div>
      </div>
    </div>
  );
};

export default Question;
