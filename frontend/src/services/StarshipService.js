import axios from "axios";
import authHeader from "@/services/auth-header";
import moment from "moment";


class StarshipService {


    getStarships() {
        // return axios.get(`/api/starships/`, {headers: authHeader()})
        return [
            {
                "id": 1,
                "name": "Starship Kosmos",
                "width": 5,
                "height": 35
            },
            {
                "id": 2,
                "name": "Starship Meteor",
                "width": 2,
                "height": 100
            }
        ]
    }

    getStarshipById(id){
        return axios.get(`/api/starships/${id}`, {headers: authHeader()})
    }
    updateStarship(body){
        return axios.put(`/api/starships/${body.id}`, body, {headers: authHeader()})
    }
    deleteStarship(id){
        return axios.delete(`/api/starships/${id}`, {headers: authHeader()})
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