import { useState } from "react";
import { AiOutlineMenu } from "react-icons/ai";
import Menu from "../components/Menu";
const OwnerMain = () => {
  const [isOpen, setIsOpen] = useState(false);

  const menuToggle = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="OwnerMain">
      <div className="top">
        <p className="me">나는</p>
        <div className="mbtiresult">
          <p className="m">M</p>
          <p className="b">B</p>
          <p className="t">T</p>
          <p className="i">I</p>
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
    </div>
  );
};

export default OwnerMain;
