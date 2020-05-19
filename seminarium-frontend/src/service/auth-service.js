import axios from 'axios'
import {API_URL} from "../config";

const AXIOS = axios.create({
    baseURL: API_URL + '/api/users',
    timeout: 1000
});

class AuthService {
    login(username, password) {
        return AXIOS.post("/signIn", {
            'username': username,
            'password': password
        });
    }
}

export default new AuthService();
