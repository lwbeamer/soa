import axios from "axios";
import * as process from "process";

class AuthService{
    AUTH_API=  process.env.VUE_APP_AUTH_API
    signIn(signinForm){
        return axios.post(`${this.AUTH_API}/login`, signinForm)
            .then((resp) => {
                if (resp.data.token != null) localStorage.setItem('user', JSON.stringify(resp.data))
                return resp.data
            })
    }

    logout(){
        localStorage.removeItem('user')
    }

    signUp(signupForm){
        return axios.post(`${this.AUTH_API}/register`, signupForm)
    }

    loginOAuth(user){
        if (user != null) localStorage.setItem('user', JSON.stringify(user))
        return user
    }

    getProfileInfo() {

    }
}

export default new AuthService()