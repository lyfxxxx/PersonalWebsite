<template>
  <div class="hello">
    <LoginMenu active="Projects"></LoginMenu>
    <div class="main-container">
      <div class="projects-card">
        <h1>Projects</h1>
        <div class ="project" v-for="item in projects" :key="item">
          <h2>
            {{item.prjname}}
            <span id="time">{{item.time}}</span>
          </h2>
          <p>项目职位：{{item.pos}}</p>
          <p id="desc">项目简介: {{item.intro}}</p>
          <p>项目职责：{{item.duty}}</p>
        </div>
      </div>
      <MyFooter></MyFooter>
    </div>
  </div>
</template>

<script>
import LoginMenu from './LoginMenu'
import MyFooter from '../common/footer'
export default {
  components: {LoginMenu, MyFooter},
  name: 'loginprojects',
  mounted () {
    this.$axios
      .get('/projects', {})
      .then(res => {
        console.log(res)
        if (res.data.code === 200) {
          this.projects = res.data.data
        } else {
          this.$Message.error('内部错误！')
        }
      })
  },
  data () {
    return {
      projects: []
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

.projects-card {
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

.projects-card > h1 {
  margin: 10px;
  text-align: left;
}

.project {
  line-height: 35px;
  flex-grow: 1;
  border: none;
  border-top: 1px solid;
}
.project > h2 {
  text-align: left;
  margin: 10px;
  font-size: 16px;
}

#time {
  text-align: right;
  margin: 10px;
  font-size: 14px;
}

.project > p {
  text-align: left;
  margin: 10px;
  color: #657180;
  font-size: 14px;
}

#time {
  text-align: right;
}

</style>
