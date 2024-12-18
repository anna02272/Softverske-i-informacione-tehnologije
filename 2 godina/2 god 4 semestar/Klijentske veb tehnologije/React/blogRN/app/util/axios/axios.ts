import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://172.18.103.209:8080'
});

// instance.defaults.headers.common['Authorization'] = 'AUTH TOKEN FROM INSTANCE';

export default instance;