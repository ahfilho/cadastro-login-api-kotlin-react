import React, { useState } from 'react';
import { connect } from 'react-redux';
import { authenticate, authFailure, authSuccess } from '../auth/AuthActions';
// import './login.css';
import { userLogin } from '../auth/AuthenticationService';
import { Alert, Spinner } from 'react-bootstrap';
import { Link } from "react-router-dom";
import NewUser from './NewUser';
import { useNavigate } from 'react-router-dom';

const Login = ({ loading, error, ...props }) => {
    const [values, setValues] = useState({
        userName: '',
        password: ''
    });
    const navigate = useNavigate();

    const handleSubmit = (evt) => {
        evt.preventDefault();
        props.authenticate();

        userLogin(values).then((response) => {
            console.log("response", response);

            console.log("Token recebido:", response.data.token);


            if (response.status === 200) {
                localStorage.getItem('USER_KEY', response.data.token);
                console.log("TOKEN ARMAZENADO"+response.data.token);
    
                props.setUser(response.data);
                navigate('/hola');
            }
            if (response.status === 404) {
                props.setUser(response.data);
                props.authFailure('Usuário não cadastrado.');
            }
            else {
                props.loginFailure('Algo saiu errado. Tente novamente. 1');
            }
        }).catch((err) => {
            if (err && err.response) {
                switch (err.response.status) {
                    case 401:
                        console.log("401 status");
                        props.loginFailure("A autenticação falhou. Reveja seus dados.");
                        break;
                    default:
                        props.loginFailure('Algo saiu errado. Tente novamente 2.');
                }
            } else {
                props.loginFailure('Algo saiu errado. Tente novamente. 3');
            }
        });
    };
    const handleChange = (e) => {
        e.persist();
        setValues(values => ({
            ...values,
            [e.target.name]: e.target.value
        }));
    };


    return (
        <div className="login-page">
            <div className="login-container">
                <NewUser />
            </div>
            <section className="h-100">
                <div className="container h-100">
                    <div className="row justify-content-md-center h-100">
                        <div className="card-wrapper">
                            <div className="card fat">
                                <div className="card-body">
                                    <h4 className="card-title">Login</h4>
                                    <form className="my-login-validation" onSubmit={handleSubmit} noValidate={false}>
                                        <div className="form-group">
                                            <label htmlFor="email">Usuário</label>
                                            <input
                                                id="username"
                                                type="text"
                                                className="form-control"
                                                minLength={5}
                                                value={values.userName}
                                                onChange={handleChange}
                                                name="userName"
                                                required
                                            />
                                            <div className="invalid-feedback">
                                                UserId is invalid
                                            </div>
                                        </div>
                                        <br></br>
                                        <div className="form-group">
                                            <label>Senha
                                                {/* <Link to="**" className="animated-button9"></Link> */}

                                            </label>
                                            <input
                                                id="passwordInput"
                                                type="password"
                                                className="form-control"
                                                minLength={6}
                                                value={values.password}
                                                onChange={handleChange}
                                                name="password"
                                                required
                                            />
                                            <div className="invalid-feedback">
                                                Password is required
                                            </div>
                                        </div>
                                        <div className="form-group">
                                            <div className="custom-control custom-checkbox">
                                                <br></br>
                                            </div>
                                        </div>
                                        <div className="form-group m-0">
                                            <button type="submit" className="btn btn-primary">
                                                Login
                                                {loading && (
                                                    <Spinner
                                                        as="span"
                                                        animation="border"
                                                        size="sm"
                                                        role="status"
                                                        aria-hidden="true"
                                                    />
                                                )}
                                            </button>
                                        </div>
                                        <div className='senha'>
                                            <br></br>
                                            <br></br>
                                            <Link
                                                to="/forgot/password"
                                                style={{ color: 'red', textDecoration: 'underline', cursor: 'pointer' }}
                                            >
                                                <br></br><br></br>
                                                Esqueceu sua senha?
                                            </Link>
                                        </div>

                                    </form>
                                    {error && (
                                        <Alert style={{ marginTop: '20px' }} variant="danger">
                                            {error}
                                        </Alert>
                                    )}
                                </div>

                            </div>
                        </div>
                    </div>
                </div >
            </section >
        </div >
    );
};

const mapStateToProps = ({ auth }) => {
    console.log("state ", auth)
    return {
        loading: auth.loading,
        error: auth.error
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        authenticate: () => dispatch(authenticate()),
        setUser: (data) => dispatch(authSuccess(data)),
        loginFailure: (message) => dispatch(authFailure(message))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);
