const ListItem = ({ data }) => {
  return (
    <div className="ListItem">
      {data.map((it) => (
        <div key={it.id}>
          <div className="item-wrapper">
            <div className="info">
              <p className="nickname">{it.nickname}</p>
              <div className="mbti">
                <p className="m">{it.result[0]}</p>
                <p className="b">{it.result[1]}</p>
                <p className="t">{it.result[2]}</p>
                <p className="i">{it.result[3]}</p>
              </div>
              <div className="acctext">
                정확도 <p className="acc">{it.accuracy}</p>%
              </div>
            </div>
            <div className="text">
              <p className="content">{it.comment}</p>
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
