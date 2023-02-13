const ListName = ({ data }) => {
  return (
    <div className="ListName">
      {data.map((it) => (
        <div key={it.id}>
          <div className="show-name">
            <p className="ownerName">{it.ownerName}의</p>
          </div>
        </div>
      ))}
    </div>
  );
};

ListName.defaltProps = {
  data: [],
};

export default ListName;
