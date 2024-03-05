import React, { useDebugValue, useState } from 'react';
import { connect, useDispatch } from 'react-redux';
import { authenticate, authFailure, authSuccess } from '../auth/AuthActions';
// import './login.css';
import { fetchUserData, userLogin } from '../auth/AuthenticationService';
import { Alert, Container, Spinner } from 'react-bootstrap';
import { Link } from "react-router-dom";
import NewUser from './NewUser';
import { useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';


const Hola = () => {


    const dispatch = useDispatch();
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState({});
    const navigate = useNavigate();

    React.useEffect(() => {
        fetchUserData().then((response) => {
            setData(response.data);
        }).catch((e) => {
            localStorage.clear();
            navigate("/");
        })
    }, [])
    const logOut = () => {
        localStorage.clear();
        navigate("/");
    }
    return (
        <Container>
            <div className='teste'>asdas
                <div className='tittle'>
                    <h3>Olá,{data && `${data.fistName} ${data.lastName}. Perfil:${data.roles && data.roles.find(role => role.roleCode === "ADMIN") ? 'ADMINISTRADOR' : 'Usuario'}`}</h3>
                </div>
                <div className='button-container'>
                    <button type="button" className='"btn btn-primary'>
                        <a className='nav-item nav-link' href='/home'>
                            Início.
                        </a>
                    </button>
                    <div className="t2">
                        <button type="submit" className="btn btn-primary">
                            <Link to="/list" className="animated-button9" style={{ color: 'white', textDecoration: 'none' }}>
                                Listar todos
                            </Link>
                        </button>
                        <br></br>
                        <Button style={{ marginTop: '5px' }} onClick={() => logOut()}>
                            Logout
                        </Button>
                    </div>
                </div>


                {/* <NavBar></NavBar> */}
                <br></br>
                {data && data.roles && data.roles.filter(value => value.roleCode === 'ADMIN').length > 0 && <Button type="variant">Add User</Button>}
                <br></br>
                <br></br>
            </div>






            /</Container>
    );
}
export default Hola;