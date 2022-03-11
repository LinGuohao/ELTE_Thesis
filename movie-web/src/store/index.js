import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex)


const store = new Vuex.Store({
    state: {
        detailInfo: [],
        detailInfoJson: []
    },

    mutations: {
        updateDetailInfo(newInfo) {
            this.state.detailInfo = newInfo;
        },
        updateDetailInfoJson(newJson) {
            this.state.detailInfoJson = newJson;
        }
    }
})
export default store;