<template>
  <div class="hello">
    <PersonalMenu active="Home"></PersonalMenu>
    <div class="main-container">
      <div class="left">
        <div class="img-card">
          <div class="img">
            <img src="../../assets/logo.png">
          </div>
          <div class="content">
            <a>Su Yunpeng</a>
            <div class="words">A Hefei University of Technology student.</div>
          </div>
        </div>
      </div>
      <div class="right">
        <div class="intro">
          <h1>About me</h1>
          <p v-for='item in desc' :key="item">{{item.intro}}</p>
        </div>
      </div>
      <div class="favorites">
        <h1>Favorites</h1>
        <img src="../../assets/django.png">
        <img src="../../assets/spring.jpeg">
        <img src="../../assets/vue.png">
        <img src="../../assets/starcraft.png">
      </div>
    </div>
    Home page
  </div>
</template>

<script>
import PersonalMenu from '../common/PersonalMenu'
export default {
  components: {PersonalMenu},
  name: 'home',
  mounted () {
    this.$axios
      .get('/index', {})
      .then(res => {
        console.log(res)
        if (res.data.code === 200) {
          this.desc = res.data.data
        } else {
          this.$Message.error('内部错误!')
        }
      })
  },
  data () {
    return {
      desc: []
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main-container {
  width: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.left {
  width: 25%!important;
}

.right {
  width: 75%!important;
}

.intro {
  text-align: left;
  line-height: 35px;
  background: #fff;
  box-shadow: 0 1px 2px 0;
  margin: 20px;
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

h1 {
  margin: 10px;
}
.hello {
  max-width: 100%!important;
  width: 100%;
  background: #F0F0F0;
}

.img-card {
  box-shadow: 0 1px 2px 0;
  max-width: 100%;
  width: 290px;
  margin: 20px;
  border: 1px solid rgba(34,36,38,.15);
  border-radius: 5px;
  display: flex;
  position: relative;
  flex-direction: column;
}
.img {
  position: relative;
  display: block;
  flex: 0 0 auto;
  padding: 0;
  margin: 0;
}
.content {
  flex-grow: 1;
  border: none;
  border-top: 1px solid;
  background: white;
  margin: 0;
}

.content > a {
  font-size: 16px;
  color: #464c5b;
  font-weight: bold;
}

.words {
  font-size: 12px;
  color: #657180;
}

.favorites {
  width: 100%;
  text-align: left;
  background: #fff;
  box-shadow: 0 1px 2px 0;
  margin: 20px;
  border: 1px solid rgba(34,36,38,.15);
  border-radius: 5px;
  position: relative;
}

.favorites > img {
  margin: 20px;
  height: 100px;
}
</style>
