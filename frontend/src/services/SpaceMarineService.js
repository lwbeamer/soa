import axios from "axios";
import authHeader from "@/services/auth-header";
import moment from "moment";


class SpaceMarineService {


    addBankCard(card) {
        card.expireDate = moment(String(card.expireDate)).format('MM/YY')
        return axios.post('/api/v1/users/addCard', card, {headers: authHeader()})
    }

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

    getMarines() {
        return [
            {
                "id": 1,
                "name": "New1",
                "coordinates": {
                    "x": 234,
                    "y": 111
                },
                "creationDate": "2023-10-11T17:25:59.248Z",
                "health": 100,
                "achievements": "string",
                "category": "SCOUT",
                "weaponType": "BOLTGUN",
                "chapter": {
                    "name": "New",
                    "world": "Finland"
                }
            },
            {
                "id": 2,
                "name": "New2",
                "coordinates": {
                    "x": 872,
                    "y": 110
                },
                "creationDate": "2023-08-11T17:25:59.248Z",
                "health": 1023,
                "achievements": "Good",
                "category": "SCOUT",
                "weaponType": "BOLTGUN",
                "chapter": {
                    "name": "Ptushkina",
                    "world": "Kolotushkina"
                }
            }
        ]
    }
}

export default new SpaceMarineService()