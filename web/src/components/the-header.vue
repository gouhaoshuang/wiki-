<template>

  <a-layout-header >
    <div class="logo"/>


    <a-menu
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
        :multiple=false
    >
      <a-menu-item key="/">
        <router-link to="/">首页</router-link>
      </a-menu-item>

      <a-menu-item key="/about">
        <router-link to="/about">关于我们</router-link>
      </a-menu-item>
      <a-menu-item key="logout" v-if="user.id">
        <a-popconfirm
            title="确认退出登录?"
            ok-text="是"
            cancel-text="否"
            @confirm="logout"
        >
          <span>你好：{{user.name}} </span>
        </a-popconfirm>
      </a-menu-item>

      <a-menu-item key="login" @click="showLoginModal" v-else>
        <span>登录</span>
      </a-menu-item>
      <a-menu-item key="/admin/ebook" :style="user.id? {} : {display:'none'}">
        <router-link to="/admin/ebook">管理</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/category" :style="user.id? {} : {display:'none'}">
        <router-link to="/admin/category">分类</router-link>
      </a-menu-item>

      <a-menu-item key="/admin/user"  :style="user.id? {} : {display:'none'}">
        <router-link to="/admin/user">用户管理</router-link>
      </a-menu-item>


    </a-menu>

  </a-layout-header>
  <a-modal
      title="登录"
      v-model:visible="loginModalVisible"
      :confirm-loading="loginModalLoading"
      @ok="login"
  >
    <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="登录名">
        <a-input v-model:value="loginUser.loginName"/>
      </a-form-item>
      <a-form-item label="密码">
        <a-input v-model:value="loginUser.password" type="password"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>


<script lang="ts">
import {computed, defineComponent, ref} from 'vue';
import axios from 'axios';
import {message} from 'ant-design-vue';
import store from '@/store';

declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({
  name: 'the-header',

  setup() {



    /**
     * 登录模态框打开
     */
    // 登录后保存
    // const user_login=ref();
    const user = computed(()=>store.state.user)

    // 用来登录
    const loginUser = ref({
      loginName: "test",
      password: "123"
    });
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    }

    // 登录开始
    const login = () => {
      console.log("开始登录");

      loginModalLoading.value = true;
      loginUser.value.password = hexMd5(loginUser.value.password + KEY);
      axios.post('/user/login', loginUser.value).then((response) => {
        loginModalLoading.value = false;
        const data = response.data;
        if (data.success) {
          loginModalVisible.value = false;
          message.success("登录成功！");
          // user_login.value=data.content;

          store.commit("setUser",data.content );


        } else {
          message.error(data.message);
        }
      });
    };
    // 退出登录
    const logout = () => {
      console.log(user.value.token);

      console.log("退出登录开始");
      axios.get('/user/logout/' + user.value.token).then((response) => {
        const data = response.data;
        if (data.success) {
          message.success("退出登录成功！");
          store.commit("setUser", {});
        } else {
          message.error(data.message);
        }
      });
    };

    return{
      showLoginModal,
      loginModalVisible,
      loginModalLoading,
      login,
      loginUser,
      user,
      logout

    }
  }

});
</script>


<style>
.login-menu {
  float: right  ;
  color: white;
  padding-left: 10px;
}
.logo {
  width: 120px;
  height: 31px;
  /*background: rgba(255, 255, 255, 0.2);*/
  /*margin: 16px 28px 16px 0;*/
  float: left ;
  color: white;
  font-size: 18px;
}
</style>

