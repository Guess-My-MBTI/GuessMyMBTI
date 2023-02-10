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
                <p className="m">{it.mbti.slice(0, 1)}</p>
                <p className="b">{it.mbti.slice(1, 2)}</p>
                <p className="t">{it.mbti.slice(2, 3)}</p>
                <p className="i">{it.mbti.slice(3, 4)}</p>
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
