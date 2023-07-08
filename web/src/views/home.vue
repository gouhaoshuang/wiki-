<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">

      <a-menu
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
      >
        <a-menu-item key="welcome">
          <meh-outlined />
          <span>欢迎</span>
        </a-menu-item>
        <a-sub-menu v-for="item in level1" :key="item.id">
          <template v-slot:title>
            <span><user-outlined/>{{ item.name }}</span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <meh-outlined />
            <span>{{ child.name }}</span>
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout style="padding: 0 24px 24px">
      <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
          <span v-show="isShowWelcome">
            欢迎来到jiawa电子书系统
          </span>
        <a-list v-show="!isShowWelcome" item-layout="vertical" size="large" :grid="{ gutter: 20, column: 3 }"
                :data-source="ebooks">
          <template #renderItem="{ item }">
            <a-list-item key="item.name">
              <template #actions>
              <span>
                <component v-bind:is="'FileOutlined'" style="margin-right: 8px" />
                {{ item.docCount }}
              </span>
                <span>
                <component v-bind:is="'UserOutlined'" style="margin-right: 8px" />
                {{ item.viewCount }}
              </span>
                <span>
                <component v-bind:is="'LikeOutlined'" style="margin-right: 8px" />
                {{ item.voteCount }}
              </span>
              </template>
              <a-list-item-meta :description="item.description">
                <template #title>
                  <router-link :to="'/doc?ebookId='+ item.id">
                    {{ item.name }}
                  </router-link>
                </template>
                <template #avatar>
                  <a-avatar :src="item.cover"/>
                </template>
              </a-list-item-meta>
            </a-list-item>
          </template>
        </a-list>
      </a-layout-content>

    </a-layout>
  </a-layout>
</template>


<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from 'axios';
import {Tool} from "@/util/tool";
import {message} from "ant-design-vue";
import {LikeOutlined, StarOutlined} from "@ant-design/icons-vue";



export default defineComponent({
  name: 'Home',
  methods: {LikeOutlined, StarOutlined},

  setup() {
    // console.log("set up")
    const ebooks = ref();

    /**
     * 查询类别
     */
    const level1 = ref();
    let categorys: any;
    const handleQueryCategory = () => {

      axios.get("/category/all",).then((response) => {

        const data = response.data;
        if (data.success) {

          categorys = data.content;
          // console.log("原始数组", categorys);
          level1.value = [];
          level1.value = Tool.array2Tree(categorys, 0);
          // console.log("树形结构", level1);
        } else {
          message.error(data.message);
        }
      });
    };
    /**
     * 调试函数
     */
    const handleQueryEbook = ()=>{
      axios.get("/ebook/list", {
        params: {
          page: 1,
          size: 1000,
          category2Id: categoryId2
        }
      }).then((response) => {
        const data = response.data;
        ebooks.value = data.content.list;
      });
    };

    const isShowWelcome = ref(true);
    let categoryId2 = 0;
    const handleClick = (value: any) => {
      // console.log("menu click",value)

      if (value.key == "welcome") {
        isShowWelcome.value = true;
      } else {
        categoryId2 = value.key;
        isShowWelcome.value = false;
        handleQueryEbook();
      }
    }


    onMounted(() => {
      handleQueryCategory();
      handleQueryEbook();
    })
    return {
      handleClick,
      level1,
      ebooks,
      pagination: {
        onChange: (page: any) => {
          console.log(page);
        },
        pageSize: 6,
      },


      isShowWelcome
    }

  }
});
</script>

<style scoped>
.ant-avatar {
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  margin: 5px 0;
}
</style>

