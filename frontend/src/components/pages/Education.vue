<template>
  <div class="hello">
    <PersonalMenu active="Education"></PersonalMenu>
    <div class="main-container">
      <div class="educations-card">
        <h1>Educations</h1>
        <div class ="education" v-for="item in educations" :key="item">
          <h2>
            {{item.stage}}
            <span id="time">{{item.time}}</span>
          </h2>
          <p>专业：{{item.major}}</p>
          <p>学校名称: {{item.school}}</p>
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
  name: 'education',
  mounted () {
    this.$axios
      .get('/educations', {})
      .then(res => {
        console.log(res)
        if (res.data.code === 200) {
          this.educations = res.data.data
        } else {
          this.$Message.error('内部错误！')
        }
      })
  },
  data () {
    return {
      educations: []
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

.educations-card {
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

.educations-card > h1 {
  margin: 10px;
  text-align: left;
}

.education {
  line-height: 35px;
  flex-grow: 1;
  border: none;
  border-top: 1px solid;
}
.education > h2 {
  text-align: left;
  margin: 10px;
  font-size: 16px;
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
