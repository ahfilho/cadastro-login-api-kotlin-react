import { AUTH_REQ, AUTH_SUCESS, AUTH_FAILURE } from '../redux/types';

export const authenticate = () => {
    return {
        type: AUTH_REQ
    }
}

export const authenticateSucess = (content) => {
    localStorage.setItem('USER_KEY', content.token);
    return {
        type: AUTH_SUCESS,
        payload: content
    }
}
export const authFailure = (error) => {
    return {
        type: AUTH_FAILURE,
        payload: error
    }
}