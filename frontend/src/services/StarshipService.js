import axios from "axios";
import authHeader from "@/services/auth-header";
import moment from "moment";


class StarshipService {
    STARSHIP_FROM_MARINE_API=process.env.VUE_APP_MARINE_API

    getStarships() {
        return axios.get(`${this.STARSHIP_FROM_MARINE_API}/starships`, {headers: authHeader()})
    }

    getStarshipById(id){
        return axios.get(`${this.STARSHIP_FROM_MARINE_API}/starships/${id}`, {headers: authHeader()})
    }
    updateStarship(body){
        return axios.put(`${this.STARSHIP_FROM_MARINE_API}/starships/${body.id}`, body, {headers: authHeader()})
    }
    deleteStarship(id){
        return axios.delete(`${this.STARSHIP_FROM_MARINE_API}/starships/${id}`, {headers: authHeader()})
    }
    createStarship(body){
        return axios.post(`${this.STARSHIP_FROM_MARINE_API}/starships`, body, {headers: authHeader()})

    }

    getDefaultStarship() {
        return {
            "id": null,
            "name": null,
            "width": null,
            "height": null
        };
    }
}

export default new StarshipService()