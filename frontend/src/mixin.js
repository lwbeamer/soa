import moment from "moment";

export const Mixin = {
    data() {
        return {
            title: 'Mixins are cool',
            copyright: 'All rights reserved. Product of super awesome people'
        };
    },
    methods: {
        sleep(time) {
            return new Promise((resolve) => setTimeout(resolve, time));
        }
    }
};