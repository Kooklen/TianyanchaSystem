import axios from "@/js/request";

export const login = (data) => {
    return axios.request({
        url: '/api/login',
        method: 'post',
        data
    })
}

export const getUserInfo = (data) => {
    return axios.request({
        url: '/api/userinfo',
        method: 'post',
        data
    })
}

export const logOut = (data) => {
    return axios.request({
        url: '/api/logout',
        method: 'get',
        data
    })
}

export const enterpriseRegistration = (data) => {
    return axios.request({
        url: '/api/enterpriseRegistration',
        method: 'post',
        data
    })
}

export const getmonitorcompany = (data) => {
    return axios.request({
        url: '/api/getmonitorcompany',
        method: 'post',
        data
    })
}



export const addMonitorController = (data) => {
    return axios.request({
        url: '/api/addMonitorController',
        method: 'post',
        data
    })
}

export const editMonitorController = (data) => {
    return axios.request({
        url: '/api/editMonitorController',
        method: 'post',
        data
    })
}

export const triggerrecord = (data) => {
    return axios.request({
        url: '/api/triggerrecord',
        method: 'post',
        data
    })
}

export const addSearchRecord = (data) => {
    return axios.request({
        url: '/api/addSearchRecord',
        method: 'post',
        data
    })
}

// export const searchCompanyData = (data) => {
//     return axios.request({
//         url: '/api/searchCompanyData',
//         methods: 'post',
//         data
//     })
// }

export const searchCompanyData = (data) => {
    return axios.request({
        url: '/api/searchCompanyData',
        method: 'post',
        data
    })
}
