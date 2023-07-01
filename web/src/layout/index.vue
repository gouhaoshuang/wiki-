<template>
    <a-layout style="height:100vh">
        <Header />
        <a-layout>
            <a-layout-sider width="200" style="background: #fff">
                <Menu />
            </a-layout-sider>
            <a-layout>
                <Breadcrumb />
                <a-layout-content class="content">
                    <router-view />
                </a-layout-content>
            </a-layout>
        </a-layout>
    </a-layout>
</template>
<script lang="ts" setup>
import { ref } from "vue";
import Header from './Header.vue'
import Menu from './Menu.vue'

</script>
<style scoped>
#components-layout-demo-top-side-2 .logo {
    float: left;
    width: 120px;
    height: 31px;
    margin: 16px 24px 16px 0;
    background: rgba(255, 255, 255, 0.3);
}



.content {
    background: #fff;
    padding: 10px 0 10px 10px;
    margin: 0;
    /* minHeight: '280px' */
    overflow-y: scroll;
}
</style>
