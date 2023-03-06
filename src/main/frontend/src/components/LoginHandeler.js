import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import axios from "axios";

const LoginHandeler = (props) => {
    const navigate = useNavigate();
    const code = new URL(window.location.href).searchParams.get("code");

    useEffect(() => {
        const kakaoLogin = async () => {
            await axios({
                method: "GET",
                url: `${process.env.REACT_APP_REDIRECT_URL}/?code=${code}`,
                headers: {
                    "Content-Type": "application/json;charset=utf-8",
                    "Access-Control-Allow-Origin": "*",
                },
            })
                .then((res) => {
                    console.log(res); // todo : 나중에 삭제하기
                    localStorage.setItem("access_token", res.headers.authorization);
                    navigate("/question");
                });
        };
        kakaoLogin();
    }, [props.history]);

    return <div></div>;
};

export default LoginHandeler;
