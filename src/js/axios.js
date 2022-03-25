import axios from 'axios'

class HttpRequest {

    constructor (baseUrl) {
        this.baseUrl = baseUrl
        this.queue = {}
    }

    getInsideConfig () {
        const config = {
            baseURL : this.baseUrl,
            headers: {
                token: '' //Authorization
            }
        }
        return config
    }

    destroy (url) {
        delete this.queue[url]
        if (!Object.keys(this.queue).length) {
            // Spin.hide()
        }
    }

    interceptors (instance, url) {
        // 请求拦截
        instance.interceptors.request.use(config => {
            const token = localStorage.getItem("access-admin");
            if(token){
                config.headers.token = token; //有放进header
                // console.log(config.headers.token);
            }
            return config
        }, error => {
            return Promise.reject(error)
        })

        // 响应拦截
        instance.interceptors.response.use(res => {
            //判断code码?
            this.destroy(url)
            const { data } = res
            if (typeof data === 'object') {
                // 自己处理
            }
            return data
        }, error => {
            this.destroy(url)
            let errorInfo = error.response
            if (!errorInfo) {
                const { request: { statusText, status }, config } = JSON.parse(JSON.stringify(error))
                errorInfo = {
                    statusText,
                    status,
                    request: { responseURL: config.url }
                }
                console.log(errorInfo)
            }
            return Promise.reject(error)
        })
    }

    request (options) {
        //创建了axios
        const instance = axios.create()
        options = Object.assign(this.getInsideConfig(), options)
        this.interceptors(instance, options.url)  //请求拦截器
        return instance(options)
    }
}
export default HttpRequest
