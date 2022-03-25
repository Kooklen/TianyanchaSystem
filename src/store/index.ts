import {defineStore} from "pinia";

export const userMainStore = defineStore('main',{
    state:()=>{
        return {
            count : 100,
            foo :'bar',
            index:1,
            manager:1
        }
    },
    getters:{

    },
    actions:{

    }
})
