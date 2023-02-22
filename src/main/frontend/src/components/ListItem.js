const ListItem = ({ data }) => {
  console.log(data);
  return (
    <div className="ListItem">
      {data.map((it) => (
        <div key={it.id}>
          <div className="item-wrapper">
            <div className="info">
              <p className="nickname">{it.nickname}</p>
              <div className="mbti">
                <p className="m">{it.mbti[0]}</p>
                <p className="b">{it.mbti[1]}</p>
                <p className="t">{it.mbti[2]}</p>
                <p className="i">{it.mbti[3]}</p>
              </div>
              <div className="acctext">
                정확도 <p className="acc">{it.accuracy}</p>%
              </div>
            </div>
            <div className="text">
              <p className="content">{it.content}</p>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

ListItem.defaultProps = {
  data: [],
};
export default ListItem;
