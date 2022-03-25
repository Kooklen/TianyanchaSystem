import store from 'store'

interface StoreInfo<T> {
    (key: string, t?: any): T
}
let set: StoreInfo<void> = function(key: string, t: any) {
    store.set(key, t)
}
let get: StoreInfo<any> = function(key: string) {
    return store.get(key)
}
let remove: StoreInfo<void> = function(key: string) {
    store.remove(key)
}

export default {
    set,
    get,
    remove,
    clearAll: () => store.clearAll(),
}
