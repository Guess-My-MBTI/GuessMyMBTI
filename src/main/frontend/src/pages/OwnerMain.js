import { useEffect, useState } from "react";
import { AiOutlineMenu } from "react-icons/ai";
import { HiOutlineLink } from "react-icons/hi";
import Menu from "../components/Menu";
import ListItem from "../components/ListItem";
import axios from "axios";

const OwnerMain = () => {
  const [isOpen, setIsOpen] = useState(false);

  const [createData, setCreateData] = useState([
    {
      id: 0,
      nickname: "",
      result: "",
      accuracy: 0,
      comment: "",
    },
  ]);

  const menuToggle = () => {
    setIsOpen(!isOpen);
  };
  const baseUrl = "http://localhost:8080/";

  const accessToken = localStorage.getItem("access_token");
  const mbti = localStorage.getItem("mbti");
  const ownerId = localStorage.getItem("id");

  useEffect(() => {
    axios({
      method: "GET",
      url: `${baseUrl}main-page`,
      headers: {
        Authorization: "Bearer " + accessToken,
        "Content-Type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin": "*",
      },
      params: {
        id: ownerId,
      },
    }).then((res) => {
      const _createData = res.data.guests.map((it) => ({
        id: it.id,
        nickname: it.nickname,
        result: it.result,
        accuracy: it.accuracy,
        comment: it.comment,
      }));

      setCreateData(createData.concat(_createData));
    });
  }, []);

  const shareLink = () => {
    axios({
      method: "GET",
      url: `${baseUrl}share`,
      headers: {
        Authorization: "Bearer " + accessToken,
        "Content-Type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin": "*",
      },
      params: {
        id: ownerId,
      },
    }).then((res) => {
      console.log(res.data);

      copyLink(res.data);
    });
  };

  const copyLink = async (link) => {
    try {
      await navigator.clipboard.writeText(link);
      alert("클립보드에 링크가 복사되었습니다.");
    } catch (e) {
      alert("실패 다시시도해주세요");
    }
  };

  return (
    <div className="OwnerMain">
      <div className="top">
        <p className="me">나는</p>
        <div className="mbtiresult">
          <p className="m">{mbti[0]}</p>
          <p className="b">{mbti[1]}</p>
          <p className="t">{mbti[2]}</p>
          <p className="i">{mbti[3]}</p>
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
        {createData.slice(1).length > 1 ? (
          <ListItem data={createData.slice(1)} />
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
