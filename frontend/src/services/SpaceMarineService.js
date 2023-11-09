import axios from "axios";
import authHeader from "@/services/auth-header";
import moment from "moment";


class SpaceMarineService {
    MARINE_API = process.env.VUE_APP_MARINE_API

    getDefaultMarine() {
        return {
            "id": null,
            "name": null,
            "coordinates": {
                "x": null,
                "y": null
            },
            "creationDate": "2023-10-11T17:25:59.248Z",
            "health": null,
            "achievements": null,
            "category": null,
            "weaponType": null,
            "chapter": {
                "name": null,
                "world": null
            }
        }
    }

    createMarine(body){
        return axios.post(`${this.MARINE_API}/space-marines`, body, {headers: authHeader()})
    }
    getMarines(reqParams) {
        return axios.get(`${this.MARINE_API}/space-marines${reqParams}`, {headers: authHeader()})
        // return [
        //     {
        //         "id": 1,
        //         "name": "New1",
        //         "coordinates": {
        //             "x": 234,
        //             "y": 111
        //         },
        //         "creationDate": "2023-10-11T17:25:59.248Z",
        //         "health": 100,
        //         "starshipId": 2,
        //         "achievements": "string",
        //         "category": "SCOUT",
        //         "weaponType": "BOLTGUN",
        //         "chapter": {
        //             "name": "New",
        //             "world": "Finland"
        //         }
        //     },
        //     {
        //         "id": 2,
        //         "name": "New2",
        //         "coordinates": {
        //             "x": 872,
        //             "y": 110
        //         },
        //         "starshipId": 1,
        //         "creationDate": "2023-08-11T17:25:59.248Z",
        //         "health": 1023,
        //         "achievements": "Good",
        //         "category": "SCOUT",
        //         "weaponType": "BOLTGUN",
        //         "chapter": {
        //             "name": "Ptushkina",
        //             "world": "Kolotushkina"
        //         }
        //     }
        // ]
    }

    getStarships() {
        return axios.get(`${this.MARINE_API}/starships/`, {headers: authHeader()})
        // return [
        //     {
        //         "id": 1,
        //         "name": "Starship Kosmos",
        //         "width": 5,
        //         "height": 35
        //     },
        //     {
        //         "id": 2,
        //         "name": "Starship Meteor",
        //         "width": 2,
        //         "height": 100
        //     }
        // ]
    }

    getMarineById(id) {
        return axios.get(`${this.MARINE_API}/space-marines/${id}`, {headers: authHeader()})
    }
    updateMarine(body){
        return axios.put(`${this.MARINE_API}/space-marines/${body.id}`, body, {headers: authHeader()})
    }
    deleteMarine(id){
        return axios.delete(`${this.MARINE_API}/space-marines/${id}`, {headers: authHeader()})
    }
    getStarshipById(id){
        return axios.get(`${this.MARINE_API}/starships/${id}`, {headers: authHeader()})
    }
    updateStarship(body){
        return axios.put(`${this.MARINE_API}/starships/${body.id}`, body, {headers: authHeader()})
    }
    deleteStarship(id){
        return axios.delete(`${this.MARINE_API}/starships/${id}`, {headers: authHeader()})
    }
    getSpaceUtils(params){
        return axios.get(`${this.MARINE_API}/space-utils/${params.field}/${params.operation}`, {headers: authHeader()})
    }

}

export default new SpaceMarineService()