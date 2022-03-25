const LOCAL_STORAGE_KEY = "searchHistory";

class Store { }

//通过Store.xxx写法可以把xxx实现静态方法的效果，无需创建Store的实例即可使用

//将[]存入localStorage
Store.saveHistory = (arr) => {
    localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(arr));
}

//从localSotrage取出
Store.loadHistory = () =>JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY))

//清空全部localStorage历史查询
Store.removeAllHistory=()=>{localStorage.removeItem(LOCAL_STORAGE_KEY)}

module.exports = Store
