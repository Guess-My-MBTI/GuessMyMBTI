const ListName = ({ data }) => {
  return (
    <div className="ListName">
      {data.map((it) => (
        <div key={it.id}>
          <div className="show-name">
            <p className="ownerName">{it.ownerName}</p>
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
