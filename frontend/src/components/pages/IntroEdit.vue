<template>
  <div class="hello">
    <PersonalMenu active="Education"></PersonalMenu>
    <div class="main-container">
      <div class="intro-edit-card">
        <h1>修改个人简介</h1>
        <div class="intro">
          <div class="line" v-for='item in desc' :key="item" >
            <div class="no-edit" v-if="item.isEdit === false">
              <p>{{item.intro}}</p>
              <Button type="info" @click="handleChange(item)">修改</Button>
              <Button type="error" @click="handleDelete(item)">删除</Button>
            </div>
            <div class="edit" v-else>
              <Input v-model="item.intro" maxlength="200" type="textarea" />
              <Button type="info" @click="handleUpdate(item)">保存</Button>
              <Button type="error" @click="handleDelete(item)">删除</Button>
            </div>
          </div>
        </div>
      </div>
      <MyFooter></MyFooter>
    </div>
  </div>
</template>

<script>
import PersonalMenu from '../common/PersonalMenu'
import MyFooter from '../common/footer'
export default {
  components: {PersonalMenu, MyFooter},
  name: 'introeditor',
  data () {
    return {
      desc: this.$route.query.desc
    }
  },
  methods: {
    handleChange (item) {
      item.isEdit = true
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main-container {
  margin: 20px 0;
  width: 100%;
}

.intro-edit-card {
  margin: 0 auto;
  position: relative;
  width: 80%;
  max-width: 100%;
  display: flex;
  flex-direction: column;
  border-radius: 5px;
  border: 1px solid rgba(34,36,38,.15);
  box-shadow: 0 1px 2px 0;
  background: #fff;
}

.intro-edit-card > h1 {
  margin: 10px;
  text-align: left;
}

.intro {
  text-align: left;
  line-height: 35px;
  background: #fff;
  box-shadow: 0 1px 2px 0;
  margin: 20px 0px 20px 20px;
  border: 1px solid rgba(34,36,38,.15);
  border-radius: 5px;
  position: relative;
  flex-direction: column;
}

.intro > p {
  margin: 10px;
  font-size: 14px;
  color: #657180;
}

#time {
  text-align: right;
  margin: 10px;
  font-size: 14px;
}

.education > p {
  font-size: 16px;
  text-align: left;
  margin: 10px;
  color: #657180;
}

#time {
  text-align: right;
}
</style>
